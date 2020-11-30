package hu.bme.mit.theta.cloud.cfa.service;

import hu.bme.mit.theta.cfa.CFA;
import hu.bme.mit.theta.cfa.analysis.utils.CfaVisualizer;
import hu.bme.mit.theta.cfa.dsl.CfaDslManager;
import hu.bme.mit.theta.cloud.blobstore.LocalBlobStore;
import hu.bme.mit.theta.cloud.cfa.endpoint.generated.contollers.NotFoundException;
import hu.bme.mit.theta.cloud.cfa.endpoint.generated.model.CreateModelResponse;
import hu.bme.mit.theta.cloud.cfa.endpoint.generated.model.GetModelMetricsResponse;
import hu.bme.mit.theta.cloud.repository.ModelRepository;
import hu.bme.mit.theta.cloud.repository.datamodel.ConfigurationEntity;
import hu.bme.mit.theta.cloud.repository.datamodel.ModelEntity;
import hu.bme.mit.theta.common.logging.FileLogger;
import hu.bme.mit.theta.common.logging.Logger;
import hu.bme.mit.theta.common.visualization.Graph;
import hu.bme.mit.theta.common.visualization.writer.GraphvizWriter;
import hu.bme.mit.theta.core.stmt.AssignStmt;
import hu.bme.mit.theta.core.stmt.AssumeStmt;
import hu.bme.mit.theta.core.stmt.HavocStmt;
import hu.bme.mit.theta.core.type.arraytype.ArrayType;
import hu.bme.mit.theta.core.type.booltype.BoolType;
import hu.bme.mit.theta.core.type.bvtype.BvType;
import hu.bme.mit.theta.core.type.inttype.IntType;
import hu.bme.mit.theta.solver.SolverFactory;
import hu.bme.mit.theta.solver.z3.Z3SolverFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Blob;
import java.util.*;

@Service
public class ModelService {

    private final LocalBlobStore localBlobStore;

    @Autowired
    private ModelRepository modelRepository;

    public ModelService() {
        this.localBlobStore = new LocalBlobStore("/tmp/theta");
    }

    public CreateModelResponse createModel(MultipartFile modelFile) {
        String[] splitFileName = StringUtils.split(modelFile.getOriginalFilename(), ".");
        if (splitFileName == null) {
            return null;
        }
        String modelType = splitFileName[splitFileName.length - 1];

        //save model in DB
        ModelEntity modelEntity = new ModelEntity();
        modelEntity.setFileName(modelFile.getOriginalFilename());
        modelEntity.setFileSize(modelFile.getSize());
        modelEntity.setModelType(modelType.toUpperCase());

        // creationDate field (with the CreationTimestamp annotation) is only calculated on flush
        modelEntity = modelRepository.saveAndFlush(modelEntity);

        //save in blob store
        try {
            localBlobStore.saveModelBlob(modelFile.getInputStream(), modelEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }

        CreateModelResponse createModelResponse = new CreateModelResponse();
        createModelResponse.setModelId(modelEntity.getModelId());
        createModelResponse.setFileName(modelEntity.getFileName());
        createModelResponse.setModelType(modelType);
        return createModelResponse;
    }

    public ModelEntity getModelMetadata(UUID modelId) throws NotFoundException {
        return modelRepository.findById(modelId).orElseThrow(this::modelNotFoundException);
    }

    public FileSystemResource getModelFile(UUID modelId) throws NotFoundException, IOException {
        return localBlobStore.getModelBlob(getModelMetadata(modelId));
    }

    public GetModelMetricsResponse getModelMetrics(UUID modelId) {
        GetModelMetricsResponse getModelMetricsResponse = new GetModelMetricsResponse();
        try {
            CFA cfa = loadModel(modelId);
            getModelMetricsResponse.setVars(cfa.getVars().size());
            getModelMetricsResponse.setBoolVars(cfa.getVars().stream().filter(v -> v.getType() instanceof BoolType).count());
            getModelMetricsResponse.setIntVars(cfa.getVars().stream().filter(v -> v.getType() instanceof IntType).count());
            getModelMetricsResponse.setBitvectorVars(cfa.getVars().stream().filter(v -> v.getType() instanceof BvType).count());
            getModelMetricsResponse.setArrayVars(cfa.getVars().stream().filter(v -> v.getType() instanceof ArrayType).count());
            getModelMetricsResponse.setLocs(cfa.getLocs().size());
            getModelMetricsResponse.setEdges(cfa.getEdges().size());
            getModelMetricsResponse.setAssignments(cfa.getEdges().stream().filter(e -> e.getStmt() instanceof AssignStmt).count());
            getModelMetricsResponse.setAssumptions(cfa.getEdges().stream().filter(e -> e.getStmt() instanceof AssumeStmt).count());
            getModelMetricsResponse.setHavocs(cfa.getEdges().stream().filter(e -> e.getStmt() instanceof HavocStmt).count());
            getModelMetricsResponse.setCyclomaticComplexity(cfa.getEdges().size() - cfa.getLocs().size() + 2 * getCfaComponents(cfa));

            return getModelMetricsResponse;
        } catch (Exception e) {
            return null;
        }
    }

    public FileSystemResource visualizeModel(UUID modelId) throws Exception {
        try {
            CFA cfa = loadModel(modelId);
            final Graph graph = CfaVisualizer.visualize(cfa);
            writeFile(graph, modelId.toString(), GraphvizWriter.Format.PNG);
            return localBlobStore.getVisualizedBlob(modelId, "png");
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
    }

    private CFA loadModel(UUID modelId) throws Exception {
        FileSystemResource modelFile = getModelFile(modelId);
        try {
            return CfaDslManager.createCfa(modelFile.getInputStream());
        } catch (final Exception ex) {
            throw new Exception("Could not parse CFA: " + ex.getMessage(), ex);
        }
    }

    private NotFoundException modelNotFoundException() {
        return new NotFoundException(0, "Model not found by id");
    }

    private int getCfaComponents(final CFA cfa) {
        final Set<CFA.Loc> visited = new HashSet<>();
        int components = 0;

        for (final CFA.Loc loc : cfa.getLocs()) {
            if (!visited.contains(loc)) {
                components++;
                visited.add(loc);
                final Queue<CFA.Loc> queue = new LinkedList<>();
                queue.add(loc);
                while (!queue.isEmpty()) {
                    final CFA.Loc next = queue.remove();
                    for (final CFA.Edge edge : next.getOutEdges()) {
                        if (!visited.contains(edge.getTarget())) {
                            visited.add(edge.getTarget());
                            queue.add(edge.getTarget());
                        }
                    }
                }
            }
        }
        return components;
    }

    public void writeFile(final Graph graph, final String fileName, final GraphvizWriter.Format format)
            throws IOException, InterruptedException {
        String basePath = localBlobStore.getBasePath() + "/visualized/";
        final String dotFile =  basePath + fileName + ".dot";
        writeStringToFile(graph, dotFile);
        final String[] cmd = {"dot", format.getOption(), dotFile, "-o", basePath + fileName + "." + format.toString()};
        final Process proc = Runtime.getRuntime().exec(cmd);
        proc.waitFor();
        final boolean deleteSuccessful = new File(dotFile).delete();
        if (!deleteSuccessful) {
            throw new IOException("Could not delete temporary dot file.");
        }
    }

    public void writeStringToFile(final Graph graph, final String dotFileName) throws FileNotFoundException {
        final File file = new File(dotFileName);
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(file);
            final String graphAsString = GraphvizWriter.getInstance().writeString(graph);
            printWriter.write(graphAsString);
        } catch (final FileNotFoundException e) {
            throw e;
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }
}

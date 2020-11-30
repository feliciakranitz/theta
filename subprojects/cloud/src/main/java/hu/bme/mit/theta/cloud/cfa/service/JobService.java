package hu.bme.mit.theta.cloud.cfa.service;

import hu.bme.mit.theta.cloud.blobstore.LocalBlobStore;
import hu.bme.mit.theta.cloud.cfa.endpoint.generated.contollers.NotFoundException;
import hu.bme.mit.theta.cloud.repository.ModelRepository;
import hu.bme.mit.theta.cloud.repository.datamodel.ModelEntity;
import hu.bme.mit.theta.solver.SolverFactory;
import hu.bme.mit.theta.solver.z3.Z3SolverFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class JobService {

    private final SolverFactory solverFactory = Z3SolverFactory.getInstance();

    @Autowired
    private ModelRepository modelRepository;

    public void startAnalysis(UUID modelId) throws NotFoundException {

        ModelEntity modelEntity = modelRepository.findById(modelId).orElseThrow(this::modelNotFoundException);

        switch (modelEntity.getModelType()) {
            case "CFA":
                runCfaAnalysis();
                break;
            case "SYSTEM":
                runStsAnalysis();
                break;
            case "XTA":
                runXtaAnalysis();
                break;
            case "XSTS":
                runXstsAnalysis();
                break;
            default:
                return;
        }
    }

    private void runCfaAnalysis() {
    }

    private void runStsAnalysis() {
    }

    private void runXtaAnalysis() {
    }

    private void runXstsAnalysis() {
    }

    private NotFoundException modelNotFoundException() {
        return new NotFoundException(0, "Model not found by id");
    }

}

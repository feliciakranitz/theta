package hu.bme.mit.inf.ttmc.cegar.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import hu.bme.mit.inf.ttmc.aiger.impl.AIGERLoaderSimple;
import hu.bme.mit.inf.ttmc.cegar.common.CEGARResult;
import hu.bme.mit.inf.ttmc.cegar.common.ICEGARLoop;
import hu.bme.mit.inf.ttmc.cegar.common.data.IAbstractState;
import hu.bme.mit.inf.ttmc.cegar.common.utils.visualization.GraphVizVisualizer;
import hu.bme.mit.inf.ttmc.cegar.common.utils.visualization.IVisualizer;
import hu.bme.mit.inf.ttmc.cegar.interpolatingcegar.InterpolatingCEGARBuilder;
import hu.bme.mit.inf.ttmc.cegar.interpolatingcegar.InterpolatingCEGARBuilder.InterpolationMethod;
import hu.bme.mit.inf.ttmc.cegar.tests.invariantchecker.InvariantChecker;
import hu.bme.mit.inf.ttmc.common.logging.Logger;
import hu.bme.mit.inf.ttmc.common.logging.impl.ConsoleLogger;
import hu.bme.mit.inf.ttmc.constraint.expr.Expr;
import hu.bme.mit.inf.ttmc.constraint.type.BoolType;
import hu.bme.mit.inf.ttmc.constraint.z3.Z3ConstraintManager;
import hu.bme.mit.inf.ttmc.formalism.sts.STS;
import hu.bme.mit.inf.ttmc.formalism.sts.STSManager;
import hu.bme.mit.inf.ttmc.formalism.sts.impl.STSManagerImpl;
import hu.bme.mit.inf.ttmc.system.model.SystemSpecification;
import hu.bme.mit.inf.ttmc.system.ui.SystemModel;
import hu.bme.mit.inf.ttmc.system.ui.SystemModelCreator;
import hu.bme.mit.inf.ttmc.system.ui.SystemModelLoader;

public class SandBox {

	private static final String MODELSPATH = "models/";

	@Test
	public void test() throws IOException {

		//System.in.read();

		final String subPath = "simple/";
		final String modelName = "simple2.system";

		final STSManager manager = new STSManagerImpl(new Z3ConstraintManager());

		final Logger logger = new ConsoleLogger(10);
		final IVisualizer visualizer = null; //new GraphVizVisualizer("models/_output", modelName, 100);
		final IVisualizer debugVisualizer = new GraphVizVisualizer("models/_debug", modelName, 100, true);

		STS problem = null;

		if (modelName.endsWith(".system")) {
			final SystemSpecification sysSpec = SystemModelLoader.getInstance().load(MODELSPATH + subPath + modelName);
			final SystemModel systemModel = SystemModelCreator.create(manager, sysSpec);
			problem = systemModel.getSTSs().iterator().next();
		} else if (modelName.endsWith(".aag")) {
			problem = new AIGERLoaderSimple().load(MODELSPATH + subPath + modelName, manager);
		}

		ICEGARLoop cegar = null;

		//cegar = new ClusteredCEGARBuilder().logger(logger).visualizer(visualizer).build();
		//cegar = new VisibleCEGARBuilder().logger(logger).visualizer(visualizer).useCNFTransformation(false)
		//		.variableCollectionMethod(VariableCollectionMethod.UnsatCore).build();

		cegar = new InterpolatingCEGARBuilder().logger(logger).visualizer(visualizer).useCNFTransformation(false).collectFromSpecification(false)
				.collectFromConditions(false).incrementalModelChecking(true).interpolationMethod(InterpolationMethod.Craig)
				//.explicitVariable("lock_b0").explicitVariable("lock_b1")
				//.explicitVariable("loc")
				.debug(debugVisualizer).build();

		final CEGARResult result = cegar.check(problem);

		if (result.specificationHolds()) {
			final List<Expr<? extends BoolType>> ops = new ArrayList<>();
			for (final IAbstractState as : result.getExploredStates()) {
				ops.add(as.createExpression(manager));
			}

			System.out.println(InvariantChecker.check(result.getAbstractSystem().getUnroller(), result.getSystem(), manager, manager.getExprFactory().Or(ops)));
		}
	}
}

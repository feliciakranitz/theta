package hu.bme.mit.theta.analysis.tcfa.pred;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;

import com.google.common.collect.ImmutableSet;

import hu.bme.mit.theta.analysis.TransferFunction;
import hu.bme.mit.theta.analysis.pred.PredPrecision;
import hu.bme.mit.theta.analysis.pred.PredState;
import hu.bme.mit.theta.analysis.tcfa.TcfaAction;
import hu.bme.mit.theta.core.expr.Expr;
import hu.bme.mit.theta.core.expr.impl.Exprs;
import hu.bme.mit.theta.core.type.BoolType;
import hu.bme.mit.theta.formalism.common.Valuation;
import hu.bme.mit.theta.formalism.utils.PathUtils;
import hu.bme.mit.theta.formalism.utils.StmtUnroller;
import hu.bme.mit.theta.formalism.utils.VarIndexes;
import hu.bme.mit.theta.formalism.utils.StmtUnroller.StmtToExprResult;
import hu.bme.mit.theta.solver.Solver;

final class TcfaPredTransferFunction implements TransferFunction<PredState, TcfaAction, PredPrecision> {

	final Solver solver;

	TcfaPredTransferFunction(final Solver solver) {
		this.solver = checkNotNull(solver);
	}

	@Override
	public Collection<PredState> getSuccStates(final PredState state, final TcfaAction action,
			final PredPrecision precision) {
		checkNotNull(state);
		checkNotNull(precision);

		final ImmutableSet.Builder<PredState> builder = ImmutableSet.builder();
		solver.push();

		solver.add(PathUtils.unfold(state.toExpr(), 0));
		for (final Expr<? extends BoolType> invar : action.getSourceDataInvars()) {
			solver.add(PathUtils.unfold(invar, 0));
		}

		final StmtToExprResult transformResult = StmtUnroller.transform(action.getDataStmts(), VarIndexes.all(0));
		final Collection<? extends Expr<? extends BoolType>> stmtExprs = transformResult.getExprs();
		final VarIndexes indexes = transformResult.getIndexes();

		solver.add(stmtExprs);

		for (final Expr<? extends BoolType> invar : action.getTargetDataInvars()) {
			solver.add(PathUtils.unfold(invar, indexes));
		}

		boolean moreSuccStates;
		do {
			moreSuccStates = solver.check().boolValue();
			if (moreSuccStates) {
				final Valuation nextSuccStateVal = PathUtils.extractValuation(solver.getModel(), indexes);

				final PredState nextSuccState = precision.mapToAbstractState(nextSuccStateVal);
				builder.add(nextSuccState);
				solver.add(PathUtils.unfold(Exprs.Not(nextSuccState.toExpr()), indexes));
			}
		} while (moreSuccStates);
		solver.pop();

		return builder.build();
	}
}
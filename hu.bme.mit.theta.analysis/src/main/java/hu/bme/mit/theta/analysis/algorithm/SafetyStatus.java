package hu.bme.mit.theta.analysis.algorithm;

import static com.google.common.base.Preconditions.checkNotNull;

import hu.bme.mit.theta.analysis.Action;
import hu.bme.mit.theta.analysis.State;
import hu.bme.mit.theta.analysis.Trace;

public abstract class SafetyStatus<S extends State, A extends Action> {

	private SafetyStatus() {
	}

	public static <S extends State, A extends Action> Safe<S, A> safe(final ARG<S, A> proof) {
		return new Safe<>(proof);
	}

	public static <S extends State, A extends Action> Unsafe<S, A> unsafe(final Trace<S, A> cex) {
		return new Unsafe<>(cex);
	}

	public abstract boolean isSafe();

	public abstract boolean isUnsafe();

	public abstract Safe<S, A> asSafe();

	public abstract Unsafe<S, A> asUnsafe();

	////

	public static final class Safe<S extends State, A extends Action> extends SafetyStatus<S, A> {
		private final ARG<S, A> proof;

		private Safe(final ARG<S, A> proof) {
			this.proof = checkNotNull(proof);
		}

		public ARG<S, A> getProof() {
			return proof;
		}

		@Override
		public boolean isSafe() {
			return true;
		}

		@Override
		public boolean isUnsafe() {
			return false;
		}

		@Override
		public Safe<S, A> asSafe() {
			return this;
		}

		@Override
		public Unsafe<S, A> asUnsafe() {
			throw new ClassCastException("Trying to cast a Safe to Unsafe");
		}
	}

	public static final class Unsafe<S extends State, A extends Action> extends SafetyStatus<S, A> {
		private final Trace<S, A> cex;

		private Unsafe(final Trace<S, A> cex) {
			this.cex = checkNotNull(cex);
		}

		public Trace<S, A> getTrace() {
			return cex;
		}

		@Override
		public boolean isSafe() {
			return false;
		}

		@Override
		public boolean isUnsafe() {
			return true;
		}

		@Override
		public Safe<S, A> asSafe() {
			throw new ClassCastException("Trying to cast an Unsafe as Safe");
		}

		@Override
		public Unsafe<S, A> asUnsafe() {
			return this;
		}
	}

}
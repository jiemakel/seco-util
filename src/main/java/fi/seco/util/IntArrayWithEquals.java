package fi.seco.util;

import java.util.Arrays;

public final class IntArrayWithEquals {
	private final int[] ints;
	private final int hashCode;

	public IntArrayWithEquals(int... ints) {
		this.ints = ints;
		this.hashCode = Arrays.hashCode(ints);
	}

	@Override
	public final boolean equals(Object other) {
		return Arrays.equals(((IntArrayWithEquals) other).ints, ints);
	}

	@Override
	public final int hashCode() {
		return hashCode;
	}

	public final int[] get() {
		return ints;
	}
}
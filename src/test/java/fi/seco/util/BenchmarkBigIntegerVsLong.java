/**
 * 
 */
package fi.seco.util;

import java.math.BigInteger;

import com.google.caliper.Benchmark;
import com.google.caliper.runner.CaliperMain;

/**
 * @author jiemakel
 * 
 */
public class BenchmarkBigIntegerVsLong extends Benchmark {

	long retl;
	long addl;

	BigInteger retbi;
	BigInteger addbi;

	@Override
	protected void setUp() {
		retl = Integer.MAX_VALUE;
		addl = 17;
		retbi = new BigInteger("" + Integer.MAX_VALUE);
		addbi = new BigInteger("17");
	}

	public long timeLongAdd(long reps) {
		for (long i = 0; i < reps; i++)
			retl += addl;
		return retl;
	}

	public BigInteger timeBigIntegerAdd(long reps) {
		for (long i = 0; i < reps; i++)
			retbi.add(addbi);
		return retbi;
	}

	public static void main(String[] args) {
		CaliperMain.main(BenchmarkBigIntegerVsLong.class, args);
	}
}

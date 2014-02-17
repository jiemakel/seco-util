/**
 * 
 */
package fi.seco.util;

import java.nio.ByteBuffer;

import com.google.caliper.Benchmark;
import com.google.caliper.runner.CaliperMain;

/**
 * @author jiemakel
 * 
 */
public class BenchmarkByteArrayLongUtil extends Benchmark {

	public long timeByteBuffer(int reps) {
		long ret = 0;
		for (int i = 0; i < reps; i++) {
			ByteBuffer bb = ByteBuffer.allocate(8);
			bb.putLong(i);
			ret += bb.array()[5];
			bb.rewind();
			ret += bb.getLong();
		}
		return ret;
	}

	public long timeByteArrayLongUtil(int reps) {
		long ret = 0;
		for (int i = 0; i < reps; i++) {
			byte[] b = new byte[8];
			ByteArrayLongUtil.longToByteArray(i, b);
			ret += b[5];
			ret += ByteArrayLongUtil.byteArrayToLong(b);
		}
		return ret;
	}

	public static void main(String[] args) {
		CaliperMain.main(BenchmarkByteArrayLongUtil.class, args);
	}
}

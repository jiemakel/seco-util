package fi.seco.util;

import java.nio.ByteBuffer;

public class SevenThreeLongByteBufferUtil {

	private static final int count = 7;
	private static final int longcount = 7 << 3;
	private static final int onepointfivecount = (longcount * 3) >>> 1;

	public static void sort(ByteBuffer b, boolean direction) {
		int lastIndex = b.limit() - longcount;
		long[] buf = new long[count];
		heapify(b, lastIndex, direction, buf);
		while (lastIndex > 0) {
			swap(b, lastIndex, buf);
			lastIndex -= longcount;
			downHeap(b, 0, lastIndex, direction, buf);
		}
	}

	private static final void swap(ByteBuffer b, int index1, long[] buf) {
		for (int i = 0; i < count; i++) {
			int ic = i << 3;
			buf[i] = b.getLong(index1 + ic);
			b.putLong(index1 + ic, b.getLong(ic));
		}
		for (int i = 0; i < count; i++)
			b.putLong(i << 3, buf[i]);
	}

	private static final void heapify(ByteBuffer b, int lastIndex, boolean direction, long[] buf) {
		for (int i = lastIndex / onepointfivecount * longcount; i >= 0; i -= longcount)
			downHeap(b, i, lastIndex, direction, buf);
	}

	public static final boolean lessThan(ByteBuffer b1, int index1, ByteBuffer b2, int index2, boolean direction) {
		if (direction) {
			long t1 = b1.getLong(index1);
			long t2 = b2.getLong(index2);
			if (t1 < t2) return true;
			if (t2 < t1) return false;
			t1 = b1.getLong(index1 + 8);
			t2 = b2.getLong(index2 + 8);
			if (t1 < t2) return true;
			if (t2 < t1) return false;
			t1 = b1.getLong(index1 + 16);
			t2 = b2.getLong(index2 + 16);
			if (t1 < t2) return true;
		} else {
			long t1 = b1.getLong(index1 + 16);
			long t2 = b2.getLong(index2 + 16);
			if (t1 < t2) return true;
			if (t2 < t1) return false;
			t1 = b1.getLong(index1 + 8);
			t2 = b2.getLong(index2 + 8);
			if (t1 < t2) return true;
			if (t2 < t1) return false;
			t1 = b1.getLong(index1 + 0);
			t2 = b2.getLong(index2 + 0);
			if (t1 < t2) return true;
		}
		return false;
	}

	private static final boolean lessThan(ByteBuffer b, int index1, long s, long p, long o, boolean direction) {
		if (direction) {
			long t1 = b.getLong(index1);
			if (t1 < s) return true;
			if (s < t1) return false;
			t1 = b.getLong(index1 + 8);
			if (t1 < p) return true;
			if (p < t1) return false;
			t1 = b.getLong(index1 + 16);
			if (t1 < o) return true;
		} else {
			long t1 = b.getLong(index1 + 16);
			if (t1 < o) return true;
			if (o < t1) return false;
			t1 = b.getLong(index1 + 8);
			if (t1 < p) return true;
			if (p < t1) return false;
			t1 = b.getLong(index1);
			if (t1 < s) return true;

		}
		return false;
	}

	private static final void downHeap(ByteBuffer b, int sindex, int lastIndex, boolean direction, long[] buf) {
		for (int i = 0; i < count; i++)
			buf[i] = b.getLong(sindex + (i << 3));
		int csindex = (sindex << 1) + longcount;
		int ocsindex = csindex + longcount;
		if (ocsindex <= lastIndex && lessThan(b, ocsindex, b, csindex, direction)) csindex = ocsindex;
		while (csindex <= lastIndex && lessThan(b, csindex, buf[0], buf[1], buf[2], direction)) {
			for (int i = 0; i < count; i++) {
				int ic = i << 3;
				b.putLong(sindex + ic, b.getLong(csindex + ic));
			}
			sindex = csindex;
			csindex = (sindex << 1) + longcount;
			ocsindex = csindex + longcount;
			if (ocsindex <= lastIndex && lessThan(b, ocsindex, b, csindex, direction)) csindex = ocsindex;
		}
		for (int i = 0; i < count; i++)
			b.putLong(sindex + (i << 3), buf[i]);
	}

}

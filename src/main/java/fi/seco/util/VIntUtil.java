package fi.seco.util;

import java.nio.ByteBuffer;

public class VIntUtil {
	public static final int getVInt(ByteBuffer bf, int index) {
		byte b = bf.get(index++);
		int i = b & 0x7F;
		for (int shift = 7; (b & 0x80) != 0; shift += 7) {
			b = bf.get(index++);
			i |= (b & 0x7F) << shift;
		}
		return i;
	}

	public static final int getVInt(ByteBuffer bf) {
		byte b = bf.get();
		int i = b & 0x7F;
		for (int shift = 7; (b & 0x80) != 0; shift += 7) {
			b = bf.get();
			i |= (b & 0x7F) << shift;
		}
		return i;
	}

	public static final int getVInt(byte[] b, int[] index) {
		int i = b[index[0]] & 0x7F;
		for (int shift = 7; (b[index[0]++] & 0x80) != 0; shift += 7)
			i |= (b[index[0]] & 0x7F) << shift;
		return i;
	}

	public static final int putVInt(ByteBuffer b, int i) {
		int j = 0;
		while ((i & ~0x7F) != 0) {
			b.put((byte) ((i & 0x7f) | 0x80));
			i >>>= 7;
			j++;
		}
		b.put((byte) i);
		return ++j;
	}

	public static final int putVInt(byte[] b, int index, int i) {
		while ((i & ~0x7F) != 0) {
			b[index++] = (byte) ((i & 0x7f) | 0x80);
			i >>>= 7;
		}
		b[index++] = (byte) i;
		return index;
	}

	public static final int getVInt(byte[] b) {
		int i = b[0] & 0x7F;
		int j = 1;
		for (int shift = 7; j < b.length; shift += 7)
			i |= (b[j++] & 0x7F) << shift;
		return i;
	}

	public static final byte[] getVInt(int i) {
		byte[] tmp = new byte[5];
		int j = 0;
		while ((i & ~0x7F) != 0) {
			tmp[j++] = (byte) ((i & 0x7f) | 0x80);
			i >>>= 7;
		}
		tmp[j++] = (byte) i;
		byte[] ret = new byte[j];
		System.arraycopy(tmp, 0, ret, 0, j);
		return ret;
	}
}

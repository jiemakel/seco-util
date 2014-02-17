/**
 * 
 */
package fi.seco.util;

/**
 * @author jiemakel
 * 
 */
public class ByteArrayLongUtil {

	public static final long byteArrayToLong(byte[] in, int offset) {
		return ((((((((long) (in[offset] & 0xff) << 8) | in[offset + 1] & 0xff) << 8 | in[offset + 2] & 0xff) << 8 | in[offset + 3] & 0xff) << 8 | in[offset + 4] & 0xff) << 8 | in[offset + 5] & 0xff) << 8 | in[offset + 6] & 0xff) << 8 | in[offset + 7] & 0xff;
	}

	public static final long byteArrayToLong(byte[] in) {
		return ((((((((long) (in[0] & 0xff) << 8) | in[1] & 0xff) << 8 | in[2] & 0xff) << 8 | in[3] & 0xff) << 8 | in[4] & 0xff) << 8 | in[5] & 0xff) << 8 | in[6] & 0xff) << 8 | in[7] & 0xff;
	}

	public static final void longToByteArray(long value, byte[] byteArray) {
		byteArray[7] = (byte) (value & 0xFF);
		byteArray[6] = (byte) ((value >>> 8) & 0xFF);
		byteArray[5] = (byte) ((value >>> 16) & 0xFF);
		byteArray[4] = (byte) ((value >>> 24) & 0xFF);
		byteArray[3] = (byte) ((value >>> 32) & 0xFF);
		byteArray[2] = (byte) ((value >>> 40) & 0xFF);
		byteArray[1] = (byte) ((value >>> 48) & 0xFF);
		byteArray[0] = (byte) (value >>> 56);
	}

	public static final void longToByteArray(long value, byte[] byteArray, int offset) {
		byteArray[7 + offset] = (byte) (value & 0xFF);
		byteArray[6 + offset] = (byte) ((value >>> 8) & 0xFF);
		byteArray[5 + offset] = (byte) ((value >>> 16) & 0xFF);
		byteArray[4 + offset] = (byte) ((value >>> 24) & 0xFF);
		byteArray[3 + offset] = (byte) ((value >>> 32) & 0xFF);
		byteArray[2 + offset] = (byte) ((value >>> 40) & 0xFF);
		byteArray[1 + offset] = (byte) ((value >>> 48) & 0xFF);
		byteArray[offset] = (byte) (value >>> 56);
	}

}

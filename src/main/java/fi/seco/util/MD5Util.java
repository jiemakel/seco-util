package fi.seco.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	public static String md5sum(String url) {
		if (url == null) return "";
		StringBuilder sb = new StringBuilder();
		try {
			for (byte b : MessageDigest.getInstance("MD5").digest(url.getBytes("UTF-8")))
				sb.append(Integer.toHexString(0x100 | (b & 0xff)).substring(1));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		return sb.toString();
	}

}

package fi.seco.util;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class URLEncoder {

	private static final Logger log = LoggerFactory.getLogger(URLEncoder.class);

	public static String encode(String text, String encoding) {
		try {
			return java.net.URLEncoder.encode(text, encoding);
		} catch (UnsupportedEncodingException e) {
			log.error("", e);
			return null;
		}
	}

	public static String encode(String text) {
		return encode(text, "UTF-8");
	}

	public static String decode(String text, String encoding) {
		try {
			return java.net.URLDecoder.decode(text, encoding);
		} catch (UnsupportedEncodingException e) {
			log.error("", e);
			return null;
		}
	}

	public static String decode(String text) {
		return decode(text, "UTF-8");
	}
}

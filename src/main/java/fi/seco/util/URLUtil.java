package fi.seco.util;

import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class URLUtil {

	private static final Logger log = LoggerFactory.getLogger(URLUtil.class);

	public static String buildPathFromURL(String urlString) {
		try {
			URL url = new URL(urlString);
			StringBuilder sb = new StringBuilder();
			sb.append(url.getHost());
			if (!url.getPath().endsWith("/"))
				sb.append(url.getPath());
			else sb.append(url.getPath().substring(0, url.getPath().length() - 1));
			if (url.getQuery() != null && !"".equals(url.getQuery())) {
				sb.append('/');
				if (url.getQuery().length() < 40)
					sb.append(URLEncoder.encode(url.getQuery()));
				else sb.append(MD5Util.md5sum(url.getQuery()));
			}
			return sb.toString();
		} catch (MalformedURLException e) {
			log.error("Malformed uri: " + urlString);
			return null;
		}
	}

}

/**
 * 
 */
package fi.seco.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jiemakel
 * 
 */
public class MediaTypeUtil {

	public static class MediaType implements Comparable<MediaType> {
		private final String mimeType;
		private final float quality;
		private final Map<String, String> parameters;

		public MediaType(String mimeType, Map<String, String> parameters) {
			this.mimeType = mimeType;
			this.parameters = parameters;
			float mq = 1.0f;
			if (parameters.containsKey("q")) try {
				mq = Float.parseFloat(parameters.get("q"));
			} catch (NumberFormatException e) {}
			quality = mq;
		}

		public String getMimeType() {
			return mimeType;
		}

		public Map<String, String> getParameters() {
			return parameters;
		}

		public float getQuality() {
			return quality;
		}

		@Override
		public int compareTo(MediaType o) {
			if (this.quality < o.quality) return 1;
			if (this.quality > o.quality) return -1;
			return 0;
		}
	}

	public static MediaType[] getMediaTypesForAcceptHeaderSortedByPreference(String acceptHeader) {
		String[] mediaTypesPlusParameters = acceptHeader.split(",\\s*");
		MediaType[] ret = new MediaType[mediaTypesPlusParameters.length];
		for (int i = 0; i < mediaTypesPlusParameters.length; i++) {
			String[] tmp = mediaTypesPlusParameters[i].split(";");
			Map<String, String> parameters = new HashMap<String, String>();
			for (int j = 1; j < tmp.length; j++) {
				int pi = tmp[j].indexOf('=');
				parameters.put(tmp[j].substring(0, pi), tmp[j].substring(pi + 1));
			}
			ret[i] = new MediaType(tmp[0], parameters);
		}
		Arrays.sort(ret);
		return ret;
	}
}

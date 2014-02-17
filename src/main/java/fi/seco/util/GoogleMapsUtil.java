package fi.seco.util;

public class GoogleMapsUtil {

	public static String encodeCoordinate(String lat, String lng, String llat, String llng) {
		return encodeCoordinate(Float.parseFloat(lat), Float.parseFloat(lng), Float.parseFloat(llat), Float.parseFloat(llng));
	}

	public static String encodeCoordinate(float lat, float lng, float llat, float llng) {
		StringBuilder sb = new StringBuilder();
		encodeNumber(sb, lat, llat);
		encodeNumber(sb, lng, llng);
		return sb.toString();
	}

	private static void encodeNumber(StringBuilder encodeString, float coordinate, float lcoordinate) {
		int num = Math.round(coordinate * 1e5f) - Math.round(lcoordinate * 1e5f);
		num <<= 1;
		if (num < 0) num = ~num;
		while (num >= 0x20) {
			int nextValue = (0x20 | (num & 0x1f)) + 63;
			if (nextValue == 92) encodeString.append((char) (nextValue));
			encodeString.append((char) (nextValue));
			num >>= 5;
		}

		num += 63;
		if (num == 92) encodeString.append((char) (num));

		encodeString.append((char) (num));
	}

	private static final float r2d = (float) (180 / Math.PI);
	private static final float d2r = (float) (Math.PI / 180);

	private static final float earthRadiusMeters = 6371.04f * 1000;

	public static float[] getBoundingBox(float lat, float lon, long distanceMeters) {
		float[] ret = new float[4];
		float latd = r2d * distanceMeters / earthRadiusMeters;
		float lond = (float) (r2d * distanceMeters / earthRadiusMeters / Math.cos(d2r * lat));
		ret[0] = lat - latd;
		ret[1] = lon - lond;
		ret[2] = lat + latd;
		ret[3] = lon + lond;
		return ret;
	}

	public static float getDistanceInMeters(float lat1, float lon1, float lat2, float lon2) {
		float dlatR = (lat2 - lat1) * d2r / 2;
		float dlonR = (lon2 - lon1) * d2r / 2;
		double a = Math.sin(dlatR) * Math.sin(dlatR) + Math.cos(d2r * lat1) * Math.cos(d2r * lat2) * Math.sin(dlonR) * Math.sin(dlonR);
		return (float) (2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a)) * earthRadiusMeters);

	}
}

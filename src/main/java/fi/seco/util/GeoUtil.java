package fi.seco.util;

public class GeoUtil {

	private static final double a = 6378137;
	private static final double b = 6356752.3142;
	private static final double f = 1 / 298.257223563;

	private static final double earth_radius_in_meters = 6372797.560856;

	public static float distHaversine(float lat1, float lon1, float lat2, float lon2) {
		double dLat = Math.toRadians(lat2 - lat1);
		double dLon = Math.toRadians(lon2 - lon1);
		double ma = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
		double c = 2 * Math.atan2(Math.sqrt(ma), Math.sqrt(1 - ma));
		return (float) (earth_radius_in_meters * c);
	}

	public static float distVincenty(float lat1, float lon1, float lat2, float lon2) {
		double l = Math.toRadians(lon2 - lon1);
		double u1 = Math.atan((1 - f) * Math.tan(Math.toRadians(lat1)));
		double u2 = Math.atan((1 - f) * Math.tan(Math.toRadians(lat2)));
		double sinU1 = Math.sin(u1);
		double cosU1 = Math.cos(u1);
		double sinU2 = Math.sin(u2);
		double cosU2 = Math.cos(u2);

		double lambda = l;
		double lambdaP;
		double cosSqAlpha;
		double sinSigma;
		double cosSigma;
		double cos2SigmaM;
		double sigma;
		int iterLimit = 100;
		do {
			double sinLambda = Math.sin(lambda);
			double cosLambda = Math.cos(lambda);
			sinSigma = Math.sqrt((cosU2 * sinLambda) * (cosU2 * sinLambda) + (cosU1 * sinU2 - sinU1 * cosU2 * cosLambda) * (cosU1 * sinU2 - sinU1 * cosU2 * cosLambda));
			if (sinSigma == 0) return 0; // co-incident points
			cosSigma = sinU1 * sinU2 + cosU1 * cosU2 * cosLambda;
			sigma = Math.atan2(sinSigma, cosSigma);
			double sinAlpha = cosU1 * cosU2 * sinLambda / sinSigma;
			cosSqAlpha = 1 - sinAlpha * sinAlpha;
			cos2SigmaM = cosSigma - 2 * sinU1 * sinU2 / cosSqAlpha;
			if (Double.isNaN(cos2SigmaM)) cos2SigmaM = 0; // equatorial line: cosSqAlpha=0 (§6)
			double c = f / 16 * cosSqAlpha * (4 + f * (4 - 3 * cosSqAlpha));
			lambdaP = lambda;
			lambda = l + (1 - c) * f * sinAlpha * (sigma + c * sinSigma * (cos2SigmaM + c * cosSigma * (-1 + 2 * cos2SigmaM * cos2SigmaM)));
		} while (Math.abs(lambda - lambdaP) > 1e-12 && --iterLimit > 0);

		if (iterLimit == 0) return Float.NaN;
		double uSq = cosSqAlpha * (a * a - b * b) / (b * b);
		double ma = 1 + uSq / 16384 * (4096 + uSq * (-768 + uSq * (320 - 175 * uSq)));
		double mb = uSq / 1024 * (256 + uSq * (-128 + uSq * (74 - 47 * uSq)));
		double deltaSigma = mb * sinSigma * (cos2SigmaM + mb / 4 * (cosSigma * (-1 + 2 * cos2SigmaM * cos2SigmaM) - mb / 6 * cos2SigmaM * (-3 + 4 * sinSigma * sinSigma) * (-3 + 4 * cos2SigmaM * cos2SigmaM)));
		float s = (float) (b * ma * (sigma - deltaSigma));
		return s;
	}

	public static void main(String[] args) {
		float diff = 0.146942138671876f;
		for (float j = -170; j <= 170; j += 10)
			for (float k = -170; k <= 170; k += 10)
				System.out.println(j + "/" + k + ": " + distHaversine(j, k, j + diff, k) + "/" + distHaversine(j, k, j, k + diff) + ":" + distHaversine(j, k, j + diff, k + diff));
	}

}

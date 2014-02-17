package fi.seco.util;

public class ObjectIntCountMin {

	private final int[][] hashes;
	private static final long prime = 2147483659L;
	private final int width;

	private static final int[] primes = { 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97 };

	private final int hash(int id, long a, long b) {
		int r = (int) ((a * id + b) % prime) % width;
		return (r < 0) ? -r : r;
	}

	private final int hash(Object id, int hashnum) {
		return hash(id.hashCode(), primes[hashnum], hashnum); // 1<=a<=p-1, 0<=b<=p
	}

	public ObjectIntCountMin(float accuracy, float certainty) {
		int hashcount = (int) (Math.log(1 / certainty) + 1);
		width = (int) (Math.E / accuracy + 1);
		hashes = new int[hashcount][];
		for (int i = 0; i < hashcount; i++)
			hashes[i] = new int[width];
	}

	public static final void printSizes(float accuracy, float certainty) {
		int s1 = (int) (Math.log(1 / certainty) + 1);
		int s2 = (int) (Math.E / accuracy + 1);
		System.out.println(s1 + " hashes of width " + s2 + "=" + s1 * s2);
	}

	public int inc(Object id, int count) {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < hashes.length; i++) {
			int loc = hash(id, i);
			hashes[i][loc] += count;
			int cur = hashes[i][loc];
			if (cur < min) min = cur;
		}
		return min;
	}

	public int get(Object id) {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < hashes.length; i++) {
			int cur = hashes[i][hash(id, i)];
			if (cur < min) min = cur;
		}
		return min;
	}

}

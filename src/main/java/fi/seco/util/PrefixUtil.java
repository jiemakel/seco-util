package fi.seco.util;


public class PrefixUtil {
	public static final String[] splitDomain(String uri) {
		int pl = uri.lastIndexOf('#');
		if (pl == -1) pl = uri.lastIndexOf('/');
		if (pl == -1) return new String[] { null, uri };
		return new String[] { uri.substring(0, pl + 1), uri.substring(pl + 1) };
	}

	public static final String getDomain(String uri) {
		int pl = uri.lastIndexOf('#');
		if (pl == -1) pl = uri.lastIndexOf('/');
		if (pl == -1) return null;
		return uri.substring(0, pl + 1);
	}

	public static final String[] splitPrefix(String uri) {
		int pl = uri.indexOf(':');
		if (pl == -1) return new String[] { null, uri };
		return new String[] { uri.substring(0, pl), uri.substring(pl + 1) };
	}

	public static final String getPrefix(String uri) {
		int pl = uri.indexOf(':');
		if (pl == -1) return null;
		return uri.substring(0, pl);
	}

	public static String getLocalName(String uri) {
		int pl = uri.lastIndexOf('#');
		if (pl == -1) pl = uri.lastIndexOf('/');
		if (pl == -1) return null;
		return uri.substring(pl + 1);
	}
}

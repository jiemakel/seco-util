package fi.seco.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;

import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SerializationHelper {
	private static final Logger log = LoggerFactory.getLogger(SerializationHelper.class);

	public static void serialize(Serializable s, String filepath) {
		try {
			File p = new File(filepath).getParentFile();
			if (!p.exists() && !p.mkdirs()) log.error("Couldn't create directories for serialization: " + p);
			SerializationUtils.serialize(s, new FileOutputStream(filepath));
		} catch (Exception e) {
			log.error("", e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T deserialize(String filepath) {
		try {
			return (T) SerializationUtils.deserialize(new FileInputStream(filepath));
		} catch (FileNotFoundException e) {
			return null;
		} catch (StackOverflowError e) {
			log.error("", e);
			return null;
		}
	}
}

package fi.seco.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {
	private static final Logger log = LoggerFactory.getLogger(FileUtils.class);

	public static void recursivelyDelete(File f) {
		if (f.isDirectory()) for (File f2 : f.listFiles())
			recursivelyDelete(f2);
		f.delete();
	}

	public static void recursivelyDelete(String loc) {
		recursivelyDelete(new File(loc));
	}

	public static void copy(String src, String dst) {
		copy(new File(src), new File(dst));
	}

	public static void copy(File src, File dst) {
		FileChannel in = null, out = null;
		try {
			in = new FileInputStream(src).getChannel();
			out = new FileOutputStream(dst).getChannel();
			long size = in.size();
			MappedByteBuffer buf = in.map(FileChannel.MapMode.READ_ONLY, 0, size);
			out.write(buf);
		} catch (IOException e) {
			log.error("", e);
		} finally {
			if (in != null) try {
				in.close();
			} catch (IOException e) {}
			if (out != null) try {
				out.close();
			} catch (IOException e) {}
		}
	}

	public static void append(String src, String dst) {
		append(new File(src), new File(dst));
	}

	public static void append(File src, File dst) {
		FileChannel in = null, out = null;
		try {
			in = new FileInputStream(src).getChannel();
			out = new FileOutputStream(dst, true).getChannel();
			long size = in.size();
			MappedByteBuffer buf = in.map(FileChannel.MapMode.READ_ONLY, 0, size);
			out.write(buf);
		} catch (IOException e) {
			log.error("", e);
		} finally {
			if (in != null) try {
				in.close();
			} catch (IOException e) {}
			if (out != null) try {
				out.close();
			} catch (IOException e) {}
		}
	}
}

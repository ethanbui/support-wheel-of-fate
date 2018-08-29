package com.x.jwof.foundation.util;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUtil {
	public static String getResourceFile(String filename) {
		return FileUtil.class.getClassLoader().getResource(filename).getFile();
	}
	
	public static String readFile(String fileName) {
		if (!fileExists(fileName)) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		try {
			FileInputStream in = new FileInputStream(fileName);
			FileChannel fc = in.getChannel();

			ByteBuffer bb = ByteBuffer.allocate((int) fc.size());
			fc.read(bb);

			byte[] buffer = bb.array();
			sb.append(new String(buffer));

			bb.clear();
			fc.close();
			in.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return sb.toString();
	}

	public static boolean fileExists(String fileName) {
		try {
			File file = new File(fileName);
			return file.exists();
		} catch (NullPointerException e) {
			log.error(e.getMessage(), e);
			return false;
		}

	}
}

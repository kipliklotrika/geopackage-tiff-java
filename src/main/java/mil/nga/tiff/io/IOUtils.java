package mil.nga.tiff.io;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Input / Output utility methods
 * 
 * @author osbornb
 */
public class IOUtils {

	/**
	 * Copy a file to a file location
	 * 
	 * @param copyFrom
	 * @param copyTo
	 * @throws IOException
	 */
	public static void copyFile(File copyFrom, File copyTo) throws IOException {

		InputStream from = new FileInputStream(copyFrom);
		OutputStream to = new FileOutputStream(copyTo);

		copyStream(from, to);
	}

	/**
	 * Copy an input stream to a file location
	 * 
	 * @param copyFrom
	 * @param copyTo
	 * @throws IOException
	 */
	public static void copyStream(InputStream copyFrom, File copyTo)
			throws IOException {

		OutputStream to = new FileOutputStream(copyTo);

		copyStream(copyFrom, to);
	}

	/**
	 * Get the file bytes
	 * 
	 * @param file
	 * @throws IOException
	 */
	public static byte[] fileBytes(File file) throws IOException {

		FileInputStream fis = new FileInputStream(file);

		return streamBytes(fis);
	}

	/**
	 * Get the stream bytes
	 * 
	 * @param stream
	 * @throws IOException
	 */
	public static byte[] streamBytes(InputStream stream) throws IOException {

		ByteArrayOutputStream bytes = new ByteArrayOutputStream();

		copyStream(stream, bytes);

		return bytes.toByteArray();
	}

	/**
	 * Copy an input stream to an output stream
	 * 
	 * @param copyFrom
	 * @param copyTo
	 * @throws IOException
	 */
	public static void copyStream(InputStream copyFrom, OutputStream copyTo)
			throws IOException {

		byte[] buffer = new byte[1024];
		int length;
		while ((length = copyFrom.read(buffer)) > 0) {
			copyTo.write(buffer, 0, length);
		}

		copyTo.flush();
		copyTo.close();
		copyFrom.close();
	}

}

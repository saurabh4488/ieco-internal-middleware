package ieco.internal.middleware.util;

import org.jboss.resteasy.reactive.multipart.FileUpload;

import java.io.*;
import java.nio.file.Path;


/**
 * The type Ieco custom multipart file.
 * @author AlokeT
 */
public class IecoCustomMultipartFile implements FileUpload, Serializable {

	private static final long serialVersionUID = 1L;
	private final byte[] bytesContent;
	private final String name;
	private final String originalName;
	private final String contentType;

	/**
	 * Instantiates a new Ieco custom multipart file.
	 *
	 * @param bytesContent the bytes content
	 * @param name         the name
	 * @param originalName the original name
	 * @param contentType  the content type
	 */
	public IecoCustomMultipartFile(byte[] bytesContent, String name, String originalName, String contentType) {
		this.bytesContent = bytesContent;
		this.name = name;
		this.originalName = originalName;
		this.contentType = contentType;
	}



	@Override
	public String name() {
		return name;
	}

	@Override
	public Path filePath() {
		return null;
	}

	@Override
	public String fileName() {
		return originalName;
	}

	@Override
	public long size() {
		return bytesContent.length;
	}

	@Override
	public String contentType() {
		return contentType;
	}

	@Override
	public String charSet() {
		return null;
	}




	public String getName() {
		return name;
	}


	public String getOriginalFilename() {
		return originalName;
	}


	public String getContentType() {
		return contentType;
	}


	public boolean isEmpty() {
		return bytesContent == null || bytesContent.length == 0;
	}


	public long getSize() {
		return bytesContent.length;
	}


	public byte[] getBytes() throws IOException {
		return bytesContent;
	}


	public InputStream getInputStream() throws IOException {
		return new ByteArrayInputStream(bytesContent);
	}


	public void transferTo(File dest) throws IOException {
		try (FileOutputStream outputStream = new FileOutputStream(dest)) {
			outputStream.write(bytesContent);
		}
	}


}

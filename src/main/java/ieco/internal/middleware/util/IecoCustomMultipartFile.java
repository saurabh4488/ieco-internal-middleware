package ieco.internal.middleware.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;


/**
 * The type Ieco custom multipart file.
 * @author AlokeT
 */
public class IecoCustomMultipartFile implements MultipartFile, Serializable {

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
	public String getName() {
		return name;
	}

	@Override
	public String getOriginalFilename() {
		return originalName;
	}

	@Override
	public String getContentType() {
		return contentType;
	}

	@Override
	public boolean isEmpty() {
		return bytesContent == null || bytesContent.length == 0;
	}

	@Override
	public long getSize() {
		return bytesContent.length;
	}

	@Override
	public byte[] getBytes() throws IOException {
		return bytesContent;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return new ByteArrayInputStream(bytesContent);
	}

	@Override
	public void transferTo(File dest) throws IOException {
		try (FileOutputStream outputStream = new FileOutputStream(dest)) {
			outputStream.write(bytesContent);
		}
	}

}

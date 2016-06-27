/**
 * 
 */
package com.mt.sniffer.file.handler;

/**
 * @author mohitkumar
 *
 */
public class TokenLocation {
	
	private String fileName;
	private String lineNo;
	private String filePath;
	public TokenLocation(String fileName, String lineNo, String filePath) {
		super();
		this.fileName = fileName;
		this.lineNo = lineNo;
		this.filePath = filePath;
	}
	/**
	 * @return the fileName
	 */
	public final String getFileName() {
		return fileName;
	}
	/**
	 * @return the lineNo
	 */
	public final String getLineNo() {
		return lineNo;
	}
	/**
	 * @return the filePath
	 */
	public final String getFilePath() {
		return filePath;
	}
	
	
}

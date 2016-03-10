/**
 * 
 */
package com.mt.sniffer.file.handler;

import java.io.File;
import java.util.UUID;

/**
 * @author mohitkumar
 *
 */
public class PoisonPill extends File {
	
	private final boolean isPill=true;
	
	private long pillId;

	public PoisonPill(String pathname) {
		super(pathname);
		
		// TODO Auto-generated constructor stub
	}

	public final long getPillId() {
		return pillId;
	}

	public final void setPillId(long pillId) {
		this.pillId = pillId;
	}

	public final boolean isPill() {
		return isPill;
	}

	
	

}

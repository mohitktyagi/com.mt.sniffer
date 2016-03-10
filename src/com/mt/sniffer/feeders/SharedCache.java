/**
 * 
 */
package com.mt.sniffer.feeders;

import com.mt.sniffer.runner.FileBlockingQueue;

/**
 * @author mohitkumar
 *
 */
public class SharedCache {
	
	private FeederQueue feederCache;
	
	private FileBlockingQueue fileCache;
	
	public SharedCache(){
		feederCache = new FeederQueue();
		fileCache = new FileBlockingQueue();
	}
	
	public FeederQueue feederCache(){
		return feederCache;
	}

	public FileBlockingQueue fileCache(){
		return fileCache;
	}
}

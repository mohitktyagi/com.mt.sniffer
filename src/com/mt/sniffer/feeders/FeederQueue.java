/**
 * 
 */
package com.mt.sniffer.feeders;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author mohitkumar
 *
 */
public class FeederQueue {
	
	private BlockingQueue<File> queue;
	
	public FeederQueue(){
		this.queue =new LinkedBlockingQueue<File>();
	}
	
	public void add(File file){
		try {
			queue.put(file);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public File getFile(){
		try {
			return queue.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}

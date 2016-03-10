/**
 * 
 */
package com.mt.sniffer.runner;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author mohitkumar
 *
 */
public class FileBlockingQueue {
	private BlockingQueue<File> queue;
	
	public FileBlockingQueue() {
		queue = new LinkedBlockingQueue<File>(4023);
	}
	
	public  void  add(File file){
		try {
			//System.out.println(Thread.currentThread().getName()+"Adding "+file.getAbsolutePath());
			queue.put(file);
			
			//System.out.println(Thread.currentThread().getName()+"queue size"+queue.size());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public  File getFile(){
		try {
			//System.out.println(Thread.currentThread().getName()+"Returning "+queue.peek());
			return queue.poll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String toString() {
		return "FileBlockingQueue [queue=" + queue + "]";
	}
}

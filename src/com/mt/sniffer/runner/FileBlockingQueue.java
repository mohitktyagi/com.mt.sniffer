/**
 * 
 */
package com.mt.sniffer.runner;

import java.io.File;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author mohitkumar
 *
 */
public class FileBlockingQueue {
	private Queue<File> queue;
	public AtomicInteger fileCount = new AtomicInteger();
	public FileBlockingQueue() {
		queue = new ConcurrentLinkedQueue<>();
	}
	
	public  void  add(File file){
		//fileCount.incrementAndGet();
			//System.out.println(Thread.currentThread().getName()+"Adding "+file.getAbsolutePath());
			queue.offer(file);
			
			//System.out.println(Thread.currentThread().getName()+"queue size"+queue.size());
		
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

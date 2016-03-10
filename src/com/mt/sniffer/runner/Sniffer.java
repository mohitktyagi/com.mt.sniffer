/**
 * 
 */
package com.mt.sniffer.runner;

import java.io.File;
import java.util.concurrent.Callable;

import com.mt.sniffer.feeders.SharedCache;
import com.mt.sniffer.file.handler.FileHandler;
import com.mt.sniffer.signal.InterruptSignal;

/**
 * @author mohitkumar
 *
 */
public class Sniffer implements Callable<String> {
	
	private SharedCache cache;
	
	private String token;
	
	public Sniffer(SharedCache cache,String token) {
		this.cache=cache;
		this.token=token;
	}

	@Override
	public String call() throws Exception {
		while(true){
			//System.out.println(queue.toString());
			File file=cache.fileCache().getFile();
			if(file!=null){
				//System.out.println(Thread.currentThread().getName()+"sniffing for "+file.getAbsolutePath());
				
					FileHandler handler = new FileHandler();
					handler.searchInFile(file, token);
							}
			if(InterruptSignal.getInstance().interrupt.get()){
				break;
			}
			//return "";
		}
		System.out.println(Thread.currentThread().getName()+" exiting");
		return "";
	}

}

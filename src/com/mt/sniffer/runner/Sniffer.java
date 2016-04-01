/**
 * 
 */
package com.mt.sniffer.runner;

import java.io.File;
import java.util.concurrent.Callable;

import com.mt.sniffer.file.handler.FileHandler;
import com.mt.sniffer.file.handler.PoisonPill;


/**
 * @author mohitkumar
 *
 */
public class Sniffer implements Callable<String> {
	
	private FileBlockingQueue queue;
	
	private String token;
	
	public Sniffer(FileBlockingQueue queue,String token) {
		this.queue=queue;
		this.token=token;
	}

	@Override
	public String call() throws Exception {
		while(true){
			
			File file=queue.getFile();
			if(file!=null){
				
				if(file instanceof PoisonPill){
					PoisonPill pill= (PoisonPill)file;
					if(pill.getPillId()==Thread.currentThread().getId()){
						break;
					}else{
						continue;
					}
				}
				if(file.isDirectory()){
				File[] files =	file.listFiles();
				for(File f:files){
					
						queue.add(f);	
					
					
				}
				}else{
					FileHandler handler = new FileHandler();
					handler.searchInFile(file, token);
				}
			}else{
				PoisonPill pill = new PoisonPill("");
				pill.setPillId(Thread.currentThread().getId());
				queue.add(pill);
			}
			
		}
		System.out.println(Thread.currentThread().getName()+" exiting");
		return "";
	}

}

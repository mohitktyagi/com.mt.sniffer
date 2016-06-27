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
	
	private final FileBlockingQueue queue;
	
	private FileHandler handler = new FileHandler();
	
	private TokenLocationList locationList ;
	
	private String token;
	
	public Sniffer(FileBlockingQueue queue,String token , TokenLocationList list) {
		this.queue=queue;
		this.token=token;
		this.locationList = list;
	}

	@Override
	public String call() throws Exception {
		while(true){
			
			File file=queue.getFile();
			if(file!=null){
				
				if(file instanceof PoisonPill){
					PoisonPill pill= (PoisonPill)file;
					if(pill.getPillId()==Thread.currentThread().getId()){
						if(pill.getMarkCount()<=3){
							pill.setMarkCount(pill.getMarkCount()+1);
							queue.add(file);
							continue;
						}else{
							break;
						}
						
					}else{
						if(!file.isHidden()){
							queue.add(file);
							continue;
						}
						
					}
				}
				if(file.isDirectory()){
				File[] files =	file.listFiles();
				for(File f:files){
					
						queue.add(f);	
					
					
				}
				}else{
					
					handler.searchInFile(file, token,locationList);
				}
			}else{
				PoisonPill pill = new PoisonPill("");
				pill.setPillId(Thread.currentThread().getId());
				queue.add(pill);
			}
			
		}
		
		return "";
	}

}

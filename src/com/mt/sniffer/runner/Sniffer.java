/**
 * 
 */
package com.mt.sniffer.runner;

import java.io.File;
import java.util.concurrent.Callable;

import com.mt.sniffer.controler.ThreadAttribute;
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
						Boolean isLastPoisonPill = ThreadAttribute.getValue(ThreadAttribute.IS_LAST_PIOSON_PILL);
						if(isLastPoisonPill !=null || isLastPoisonPill){
							if(pill.getMarkCount()<=3){
								pill.setMarkCount(pill.getMarkCount()+1);
								queue.add(file);
								ThreadAttribute.setValue(ThreadAttribute.IS_LAST_PIOSON_PILL, true);
								continue;
							}else{
								break;
							}
						}else{
							pill.setMarkCount(1);
							queue.add(file);
							ThreadAttribute.setValue(ThreadAttribute.IS_LAST_PIOSON_PILL, true);
							continue;
						}
						
						
					}else{
						
							queue.add(file);
							continue;
						
						
					}
				}
				ThreadAttribute.setValue(ThreadAttribute.IS_LAST_PIOSON_PILL, false);
				if(file.isDirectory()){
				File[] files =	file.listFiles();
				for(File f:files){
					
						queue.add(f);	
					
					
				}
				}else{
					
					handler.searchInFile(file, token,locationList);
				}
			}else{
				Boolean isLastPoisonPill = ThreadAttribute.getValue(ThreadAttribute.POISON_PILL_PUSH);
				if(isLastPoisonPill == null || !isLastPoisonPill){
					PoisonPill pill = new PoisonPill("");
					pill.setPillId(Thread.currentThread().getId());
					queue.add(pill);
					ThreadAttribute.setValue(ThreadAttribute.POISON_PILL_PUSH, true);
					ThreadAttribute.setValue(ThreadAttribute.IS_LAST_PIOSON_PILL, true);
				}
				
			}
			
		}
		//System.out.println("exting thread "+Thread.currentThread().getName());
		return "";
	}

}

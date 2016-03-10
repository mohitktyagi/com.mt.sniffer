/**
 * 
 */
package com.mt.sniffer.controler;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.mt.sniffer.runner.FileBlockingQueue;
import com.mt.sniffer.runner.Sniffer;

/**
 * @author mohitkumar
 *
 */
public class SearchControler {
 public static void main(String[] args) {
	ExecutorService executorService = Executors.newFixedThreadPool(10);
	String token=args[0];
	String dir=args[1];
	File file = new File(dir);
	FileBlockingQueue blockingQueue = new FileBlockingQueue();
	blockingQueue.add(file);
	long startTime = System.currentTimeMillis();
	executorService.submit(new Sniffer(blockingQueue, token));
	executorService.submit(new Sniffer(blockingQueue, token));
	executorService.submit(new Sniffer(blockingQueue, token));
	executorService.submit(new Sniffer(blockingQueue, token));
	executorService.submit(new Sniffer(blockingQueue, token));
	executorService.submit(new Sniffer(blockingQueue, token));
	executorService.shutdown();
	try {
		executorService.awaitTermination(5, TimeUnit.MINUTES);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	long endTime =System.currentTimeMillis();
	System.out.println("exiting main , total time took "+(endTime-startTime));
	
	
	
}
}

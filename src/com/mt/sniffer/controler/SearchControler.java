/**
 * 
 */
package com.mt.sniffer.controler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.mt.sniffer.file.handler.TokenLocation;
import com.mt.sniffer.runner.FileBlockingQueue;
import com.mt.sniffer.runner.ResultSet;
import com.mt.sniffer.runner.Sniffer;
import com.mt.sniffer.runner.TokenLocationList;

/**
 * @author mohitkumar
 *
 */
public class SearchControler {
 public static void main(String[] args) {
	long startTime = System.currentTimeMillis();
	commandLineSearch(args);
	long endTime = System.currentTimeMillis();
	double totalTimeInSec = (endTime-startTime)/1000.00;
	System.out.println("Total time took "+totalTimeInSec +" Second(s)");
	
}
 
 private static void commandLineSearch(String[] args){
	 verifyInput(args);
	 search(args[1], args[0]);
 }
 
 private static void search(String dir ,String token){
	verifyInput(dir,token);
	ExecutorService executorService = Executors.newFixedThreadPool(6);
	
	
	File file = new File(dir);
	FileBlockingQueue blockingQueue = new FileBlockingQueue();
	blockingQueue.add(file);
	
	
	List<TokenLocationList> locLists = new ArrayList<TokenLocationList>();
	TokenLocationList  lst;
	List<Callable<String>> sniffers = new ArrayList<Callable<String>>();
	
	for(int i=0;i<6;i++){
		lst = new TokenLocationList();
		locLists.add(lst);
		sniffers.add(new Sniffer(blockingQueue, token,lst));
		//executorService.submit(new Sniffer(blockingQueue, token,lst));
	}
	List<Future<String>> fList;
	ResultSet   resultSet =new ResultSet(new TokenLocationList[0]);
	try {
		fList = executorService.invokeAll(sniffers);
		executorService.shutdown();
		checkAllSniffersExisted(fList);
		
		   resultSet= new ResultSet(locLists.toArray(new TokenLocationList[0]));
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	if(resultSet.hasNext()){
		TokenLocation tlok=null;
		while(resultSet.hasNext()){
			tlok = resultSet.next();
			System.out.println(tlok.getFilePath());
		}
		
	}else{
		System.out.println("Given token : "+token+" not found in "+dir);
	}
	//System.err.println("file scanned "+blockingQueue.fileCount.get());
	//long endTime =System.currentTimeMillis();
	//System.out.println("exiting main , total time took "+(endTime-startTime)+" milli Seconds");

 }
 
 private static void checkAllSniffersExisted(List<Future<String>> fList){
	 for(Future<String> f : fList){
		 try {
			f.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
 }
 
 private static void verifyInput(String args[]){
	 if(args == null || args.length<2){
		displayHelp();
		
	 }else{
		 String filePath =args[1];
		 File file = new File(filePath);
		 if(!file.exists()){
			 System.out.println("Not a valid file or directory!");
			 displayHelp();
			 
		 }
	 }
	 
 }
 
 private static void verifyInput(String dir , String token){
	 if(dir == null || token == null){
		displayHelp();
		
	 }else{
		 String filePath =dir;
		 File file = new File(filePath);
		 if(!file.exists()){
			 System.out.println("Not a valid file or directory!");
			 displayHelp();
			 
		 }
	 }
	 
 }
 
 private static void displayHelp(){
	  System.out
      .println("java -jar com.mt.sniffer.jar token file/directory");
	  System.out.println("token : token to search.");
	  System.out.println("file/directory : File or Directory to search in.");
	  System.exit(0);
 }
}

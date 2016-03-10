/**
 * 
 */
package com.mt.sniffer.file.handler;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.mt.sniffer.signal.InterruptSignal;

/**
 * @author mohitkumar
 *
 */
public class FileHandler {
	
	public String searchInFile(File file,String token){
		try {
			//System.out.println("Searching in file "+file.getName());
			FileInputStream fis= new FileInputStream(file);
			Scanner scanner = new Scanner(fis);
			while(scanner.hasNext()){
				if(scanner.nextLine().contains(token)){
					System.out.println(Thread.currentThread().getName()+" : found in file "+file.getAbsolutePath());
					InterruptSignal.getInstance().interrupt.set(true);
					return file.getAbsolutePath();
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}

}

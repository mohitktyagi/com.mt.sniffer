/**
 * 
 */
package com.mt.sniffer.file.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.mt.sniffer.runner.TokenLocationList;



/**
 * @author mohitkumar
 *
 */
public class FileHandler {
	
	public String searchInFile(File file,String token,TokenLocationList list){
		Scanner scanner=null;
		try {
			
			FileInputStream fis= new FileInputStream(file);
			 scanner = new Scanner(fis);
			String str= scanner.findWithinHorizon(token, 0);
			if(str != null){
				//System.out.println(file.getAbsolutePath());
				list.add(new TokenLocation(file.getName() ,token,file.getAbsolutePath()));
			}
			 
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			scanner.close();
		}
		
		return "";
	}

}

/**
 * 
 */
package com.mt.sniffer.runner;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.mt.sniffer.file.handler.TokenLocation;

/**
 * @author mohitkumar
 *
 */
public class TokenLocationList {
	private List<TokenLocation>  list = new LinkedList<TokenLocation>();
	
	public void add(TokenLocation tokenL){
		list.add(tokenL);
	}
	
	public Iterator<TokenLocation> iterator(){
		return list.iterator();
	}
}

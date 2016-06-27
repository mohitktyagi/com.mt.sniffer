/**
 * 
 */
package com.mt.sniffer.runner;

import java.util.Iterator;
import java.util.List;

import com.mt.sniffer.file.handler.TokenLocation;

/**
 * @author mohitkumar
 *
 */
public class ResultSet {
	
	private TokenLocationList[] tokenLists;
	
	private int listIndex = 0;
	
	private Iterator<TokenLocation>  currentIterator;
	
	public ResultSet(TokenLocationList[] tokenLists) {
		this.tokenLists = tokenLists;
	}
	
	public boolean hasNext(){
		if(listIndex>tokenLists.length-1){
			return false;
		}
		if(currentIterator == null){
			currentIterator = tokenLists[listIndex].iterator();
		}
		boolean hasNext =  currentIterator.hasNext();
		if(hasNext){
			return hasNext;
		}else{
			currentIterator = null;
			listIndex++;
			return this.hasNext();
		}
		
	}
	
	public TokenLocation next(){
		return currentIterator.next();
	}

}

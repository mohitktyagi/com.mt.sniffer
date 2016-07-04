/**
 * 
 */
package com.mt.sniffer.controler;

import java.util.HashMap;
import java.util.Map;

import javax.swing.text.StyledEditorKit.BoldAction;

/**
 * @author mohitkumar
 *
 */
public class ThreadAttribute {
	private static final ThreadLocal threadLocal = new ThreadLocal(){
		@Override
		protected Object initialValue() {

			// TODO: is this used anywhere?
			return new HashMap<String,Boolean>();
		}
	};
	
	public static void  setValue(String key , Boolean value){
		((Map<String, Boolean>)threadLocal.get()).put(key, value);
	}
	
	public static Boolean  getValue(String key){
		return ((Map<String, Boolean>)threadLocal.get()).get(key);
	}
	
	public static void clearValues(){
		((Map<String, Boolean>)threadLocal.get()).clear();
	}
	
	
	public static String POISON_PILL_PUSH="Poison_Pill_PUSH";
	public static String IS_LAST_PIOSON_PILL="IS_LAST_PIOSON_PILL";
}

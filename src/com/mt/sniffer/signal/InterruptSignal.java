package com.mt.sniffer.signal;

import java.util.concurrent.atomic.AtomicBoolean;

public class InterruptSignal {
	
	public final AtomicBoolean interrupt=new AtomicBoolean();
	
	private static final InterruptSignal instance = new InterruptSignal();
	
	private InterruptSignal(){
		
	}
	
	public static InterruptSignal getInstance(){
		return instance;
	}

}

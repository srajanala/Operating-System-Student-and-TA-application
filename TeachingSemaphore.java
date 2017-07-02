package com.os.TeachingAssistant;

public class TeachingSemaphore {

	private boolean signal = false;
	
	public synchronized void wakeup(){		
		this.signal = true;
		this.notify();
	}
	
	public synchronized void changeSignal() throws InterruptedException{
		while(!this.signal)
			wait();
		this.signal= false;
	}
}

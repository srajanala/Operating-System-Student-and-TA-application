package com.os.TeachingAssistant;

import java.util.concurrent.Semaphore;

public class TA implements Runnable {
	Semaphore availableChairs;
	TeachingSemaphore awake;
	Thread taThread;
	
	TA( Semaphore availableChairs, TeachingSemaphore awake){
		this.availableChairs = availableChairs;
		this.awake = awake;
		this.taThread = Thread.currentThread();
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			
			try {
				System.out.println("No Students.. TA Taking Nap");
				awake.changeSignal();
				System.out.println("TA was notified by a student.");
				taThread.sleep(5000);
				
				if (availableChairs.availablePermits() != 3)
                {
                    do
                    {
                    	taThread.sleep(5000);
                        availableChairs.release();
                    }
                    while (availableChairs.availablePermits() != 3);                   
                }
            }
            catch (InterruptedException e)
            {
                
               e.printStackTrace();
            }
			
			
			
		}
		
	}

}

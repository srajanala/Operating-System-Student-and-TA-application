package com.os.TeachingAssistant;

import java.util.concurrent.Semaphore;

public class Student implements Runnable {
	
	int studentNumber;	
	Semaphore availableChairs;
	Semaphore availabe;
	TeachingSemaphore awake;
	Thread studentThread;
	Student( int studentNumber, Semaphore availableChairs, Semaphore availabe, TeachingSemaphore awake){
		this.studentNumber = studentNumber;
		this.availableChairs = availableChairs;
		this.availabe = availabe;
		this.awake = awake;
		this.studentThread = Thread.currentThread();
		
		}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		
		try {
			
			System.out.println("Student: "+this.studentNumber +" has started");
			studentThread.sleep(5000);
			System.out.println("Student: "+this.studentNumber+" is checking to see if TA is available");
			if(availabe.tryAcquire()){
				
				try{
					
					awake.wakeup();
					System.out.println("Student: "+this.studentNumber+ " has WokeUP TA");
					System.out.println("Student: "+this.studentNumber+ " Getting Help From TA" );
					studentThread.sleep(5000);
					System.out.println("Student: "+this.studentNumber+ " Has Completed Discussion with TA" );
					availabe.release();
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}else{
				System.out.println("Student: "+studentNumber+" TA is busy in assistanting other students. Student Checking For available Chairs");
				if(availableChairs.tryAcquire()){
					try{
					System.out.println("Student: "+this.studentNumber+" Occupied Chair #"+(3 - availableChairs.availablePermits())+" In line");
					availabe.acquire();					
					System.out.println("Student: "+this.studentNumber+ " Getting Help From TA" );
					studentThread.sleep(4000);
					System.out.println("Student : "+this.studentNumber+ " Has Completed Discussion with TA" );
					availabe.release();
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				}else{
					System.out.println("Student " + this.studentNumber + " TA is busy in assistanting other students and all chairs were taken, Decided to come back later");
				}
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}

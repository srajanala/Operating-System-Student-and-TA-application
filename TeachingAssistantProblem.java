package com.os.TeachingAssistant;


import java.util.concurrent.Semaphore;

 class TeachingAssistantProblem {
	 static int numberOfStudents = 6;
	public static void main(String[] args) {
		
		Semaphore availableChairs = new Semaphore(3);
		Semaphore available = new Semaphore(1);
		TeachingSemaphore awake = new TeachingSemaphore();
		
		for(int i =0; i< numberOfStudents ; i++){
			Thread studentThread = new Thread(new Student( i+1, availableChairs, available, awake ));
			studentThread.start();
		}
		
		Thread taThread = new Thread(new TA( availableChairs,awake ));
		taThread.start();
	}

}

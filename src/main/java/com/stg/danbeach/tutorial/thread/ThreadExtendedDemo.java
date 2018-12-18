package com.stg.danbeach.tutorial.thread;

class ThreadDemo extends Thread{
	private Thread t;
	private String threadName;
	
	public ThreadDemo(String name) {
		this.threadName = name;
		System.out.println("Creating " + threadName);
	}
	
	public void run() {
		System.out.println("Running " + threadName);
		
		try {
			for(int x = 10; x > 0; x--) {
				System.out.println("Thread: " + threadName + ", " + x);
				
				// let the thread sleep for a while
				Thread.sleep(100);
			}
		}catch(InterruptedException e) {
			System.out.println("Thread " + threadName + " interrupted");
			e.printStackTrace();
		}
		System.out.println("Thread " + threadName + " exiting");
	}
	
	public void start() {
		System.out.println("Starting " + threadName);
		
		if(t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}
}


public class ThreadExtendedDemo {
	public static void main(String[] args) {
		ThreadDemo t1 = new ThreadDemo("Thread-1");
		t1.start();
		
		ThreadDemo t2 = new ThreadDemo("Thread-2");
		t2.start();
		
		ThreadDemo t3 = new ThreadDemo("Thread-3");
		t3.start();
		
		ThreadDemo t4 = new ThreadDemo("Thread-4");
		t4.start();
		
		ThreadDemo t5 = new ThreadDemo("Thread-5");
		t5.start();
	}
}

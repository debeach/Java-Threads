package com.stg.danbeach.tutorial.thread;

class RunnableDemo implements Runnable{

	private Thread t;
	private String threadName;
	
	public RunnableDemo(String name) {
		this.threadName = name;
		System.out.println("Creating " + this.threadName);
	}
	
	public void run() {
		System.out.println("Running " + threadName);
		
		try {
			for(int i = 10; i > 0; i--) {
				System.out.println("Thread " + threadName + ", " + i);
				
				// Let the thread sleep for a while
				Thread.sleep(20);
			}
		}catch(InterruptedException e) {
			System.out.println("Thread " + threadName + " Interrupted");
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

public class RunnableInterfaceDemo {
	public static void main(String[] args) {
		RunnableDemo r1 = new RunnableDemo("Thread-1");
		r1.start();
		
		RunnableDemo r2 = new RunnableDemo("Thread-2");
		r2.start();
		
		RunnableDemo r3 = new RunnableDemo("Thread-3");
		r3.start();
		
		RunnableDemo r4 = new RunnableDemo("Thread-4");
		r4.start();
		
		RunnableDemo r5 = new RunnableDemo("Thread-5");
	}
}

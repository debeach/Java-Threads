package com.stg.danbeach.tutorial.thread;


class RunnableDemo1 implements Runnable{
	public Thread t;
	private String threadName;
	private boolean suspended = false;
	
	public RunnableDemo1(String name) {
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
				
				synchronized(this) {
					while(suspended) {
						wait();
					}
				}
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
	
	public void suspend() {
		suspended = true;
	}
	
	public synchronized void resume() {
		suspended = false;
		notify();
	}
}


public class ThreadControlDemo {

	public static void main(String[] args) {
		RunnableDemo1 r1 = new RunnableDemo1("Thread-1");
		r1.start();
		
		RunnableDemo1 r2 = new RunnableDemo1("Thread-2");
		r2.start();
		
		RunnableDemo1 r3 = new RunnableDemo1("Thread-3");
		r3.start();
		
		RunnableDemo1 r4 = new RunnableDemo1("Thread-4");
		r4.start();
		
		RunnableDemo1 r5 = new RunnableDemo1("Thread-5");
		r5.start();
		
		try {
			Thread.sleep(1000);
			r1.suspend();
			System.out.println("Suspending First Thread...");
			
			Thread.sleep(1000);
			r1.resume();
			System.out.println("Resuming First Thread...");
			
			Thread.sleep(1000);
			r2.suspend();
			System.out.println("Suspending Second Thread...");
			
			Thread.sleep(1000);
			r2.resume();
			System.out.println("Resuming Second Thread...");
			
			Thread.sleep(1000);
			r3.suspend();
			System.out.println("Suspending Third Thread...");
			
			Thread.sleep(1000);
			r3.resume();
			System.out.println("Resuming Third Thread...");
			
			Thread.sleep(1000);
			r4.suspend();
			System.out.println("Suspending Forth Thread...");
			
			Thread.sleep(1000);
			r4.resume();
			System.out.println("Resuming Fourth Thread...");
			
			Thread.sleep(1000);
			r5.suspend();
			System.out.println("Suspending Fifth Thread...");
			
			Thread.sleep(1000);
			r5.resume();
			System.out.println("Resuming Fifth Thread...");
			
		}catch(InterruptedException e) {
			System.out.println("Main Thread interrupted");
			e.printStackTrace();
		}
		
		try {
			System.out.println("Waiting for threads to finish...");
			r1.t.join();
			r2.t.join();
			r3.t.join();
			r4.t.join();
			r5.t.join();
			
			
		}catch(InterruptedException e) {
			System.out.println("Main thread interrupted again");
		}finally{
			System.out.println("DONE");
		}
		
		System.out.println("Main thread exiting...");
	}

}

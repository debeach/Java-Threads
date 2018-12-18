package com.stg.danbeach.tutorial.thread;

class PrintDemo {
	public void printCounter() {
		try {
			for (int i = 5; i > 0; i--) {
				System.out.println("Counter --- " + i);
			}
		} catch (Exception e) {
			System.out.println("Tread interrupted");
			e.printStackTrace();
		}
	}
}

class ThreadDemo2 extends Thread {
	private Thread t;
	private String threadName;
	PrintDemo pd;

	ThreadDemo2(String name, PrintDemo pd) {
		this.threadName = name;
		this.pd = pd;
	}

	// does the work
	public void run() {
		// this is where the threads are synchronized and allows numbers to be run in order.
		synchronized (pd) {
			pd.printCounter();
		}
		System.out.println("Thread " + threadName + " exiting");
	}

	// starts the thread
	public void start() {
		System.out.println("Starting " + threadName);

		if (t == null) {
			t = new Thread(this, this.threadName);
			t.start();
		}
	}
}

public class ThreadSynchronizationDemo {

	public static void main(String[] args) {
		PrintDemo pd = new PrintDemo();

		ThreadDemo2 t1 = new ThreadDemo2("Thread - 1", pd);
		ThreadDemo2 t2 = new ThreadDemo2("Thread - 2", pd);

		t1.start();
		t2.start();

		// wait for threads to end
		try {
			t1.join();
			t2.join();
		} catch (Exception e) {
			System.out.print("Interrupted");
			e.printStackTrace();
		}
	}

}

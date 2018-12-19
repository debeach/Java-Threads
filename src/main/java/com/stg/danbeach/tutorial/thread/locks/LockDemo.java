package com.stg.danbeach.tutorial.thread.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class LockPrintDemo{
	private  Lock queueLock = new ReentrantLock();
	
	public void print() {
		queueLock.lock();
		
		try {
			Long duration = (long)(Math.random() * 10000);
			System.out.println(Thread.currentThread().getName() + " Time Taken " + (duration / 1000) + " seconds");
			Thread.sleep(duration);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}finally {
			System.out.printf("%s printed the document successfully \n", Thread.currentThread().getName());
			queueLock.unlock();
		}
	}
}

class LockThreadDemo extends Thread{
	LockPrintDemo printDemo;
	
	public LockThreadDemo(String name, LockPrintDemo printDemo){
		super(name);
		this.printDemo = printDemo;
	}
	
	@Override
	public void run() {
		System.out.printf("%s starts printing a document\n", Thread.currentThread().getName());
		printDemo.print();
	}
}


public class LockDemo {

	
	public static void main(String[] args) {
		LockPrintDemo pd = new LockPrintDemo();
		
		LockThreadDemo t1 = new LockThreadDemo("Thread - 1", pd);
		LockThreadDemo t2 = new LockThreadDemo("Thread - 2", pd);
		LockThreadDemo t3 = new LockThreadDemo("Thread - 3", pd);
		LockThreadDemo t4 = new LockThreadDemo("Thread - 4", pd);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		
	}

}

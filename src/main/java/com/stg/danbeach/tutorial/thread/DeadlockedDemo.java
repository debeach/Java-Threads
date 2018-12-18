package com.stg.danbeach.tutorial.thread;

public class DeadlockedDemo {
	public static Object lock1 = new Object();
	public static Object lock2 = new Object();
	
	public static void main(String[] args) {
		ThreadDemo3 t1 = new ThreadDemo3();
		ThreadDemo4 t2 = new ThreadDemo4();
		t1.start();
		t2.start();
	}
	
	
	private static class ThreadDemo3 extends Thread{
		public void run() {
			synchronized(lock1) {
				System.out.println("Thread 1: Holding lock 1...");
				
				try {
					Thread.sleep(10);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Thread 1: Waiting for lock 1 & 2...");
				
				synchronized(lock2) {
					System.out.println("Thread 1: Holding lock 1 & 2...");
				}
			}
		}
	}
	
	private static class ThreadDemo4 extends Thread{
		public void run() {
			synchronized(lock2) {
				System.out.println("Thread 2: Holding lock 2...");
				
				try {
					Thread.sleep(10);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Thread 2: Waiting for lock 1 & 2...");
				
				synchronized(lock1) {
					System.out.println("Thread 2: Holding lock 1 & 2...");
				}
			}
		}
	}
}

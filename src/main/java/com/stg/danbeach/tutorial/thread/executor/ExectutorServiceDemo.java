package com.stg.danbeach.tutorial.thread.executor;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExectutorServiceDemo {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		
		try {
			executor.submit(new Task());
			System.out.println("Shutdown Executor");
			executor.shutdown();
			executor.awaitTermination(5, TimeUnit.SECONDS);
			
		}catch(InterruptedException e) {
			System.err.println("interrupted tasks");
		}finally {
			if(!executor.isTerminated()) {
				System.err.println("Cancel non-finished tasks");
			}
			executor.shutdownNow();
			System.out.println("shutdown finished");
		}
	}
	
	static class Task implements Runnable{
		public void run() {
			try {
				Long duration = (long)(Math.random() * 20);
				System.out.println("Running task");
				TimeUnit.SECONDS.sleep(duration);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

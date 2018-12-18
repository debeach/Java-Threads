package com.stg.danbeach.tutorial.thread;

class Chat {
	private boolean flag = false;

	public synchronized void question(String message) {
		if (flag) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(message);
		flag = true;
		notify();
	}

	public synchronized void answer(String message) {
		if (!flag) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(message);
		flag = false;
		notify();
	}
}

class T1 implements Runnable {
	Chat m;
	String[] s1 = { "hi", "how are you", "I am also doing fine" };

	public T1(Chat m1) {
		this.m = m1;
		new Thread(this, "Question").start();
	}

	public void run() {
		for (int i = 0; i < s1.length; i++) {
			m.question(s1[i]);
		}
	}
}

class T2 implements Runnable {
	Chat m;
	String[] s2 = { "Hello", "I am good", "Great" };

	public T2(Chat m2) {
		this.m = m2;
		new Thread(this, "Answer").start();
	}

	public void run() {
		for (int x = 0; x < s2.length; x++) {
			m.answer(s2[x]);
		}
	}
}

public class ThreadCommunicationDemo {
	public static void main(String[] args) {
		Chat m = new Chat();
		new T1(m);
		new T2(m);
	}

}

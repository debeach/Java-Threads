package com.stg.danbeach.tutorial.threadlocal;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TestThreadLocalRandom {
	public static void main(String[] args) {
		System.out.println("Random Integer: " + new Random().nextInt() );
		System.out.println("Seeded Random Integer: " + new Random(15).nextInt());
		System.out.println("Thread Local Random Integer: " + ThreadLocalRandom.current().nextInt(1,21));
		
		final ThreadLocalRandom random = ThreadLocalRandom.current();
		random.setSeed(15); // exception will come as seeding is not allowed in ThreadLocalRandom.
		System.out.println("Seeded Thread Local Random Integer " + random.nextInt());
	}
}

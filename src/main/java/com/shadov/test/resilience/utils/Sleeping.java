package com.shadov.test.resilience.utils;

public class Sleeping {
	public static void ms(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException ignored) {
		}
	}
}

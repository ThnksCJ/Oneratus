package com.cj.bootstrap.util;

import java.util.function.BooleanSupplier;


public class ThreadUtil {

    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception ignored) {
        }
    }

    public static void sleepUntil(BooleanSupplier condition, int timeout) {
        long startTime = System.currentTimeMillis();
        while (true) {
            if (condition.getAsBoolean()) {
                break;
            } else if (timeout != -1 && System.currentTimeMillis() - startTime >= timeout) {
                break;
            }
            sleep(10);
        }
    }

    public static void sleepUntil(BooleanSupplier condition, int timeout, int amountToSleep) {
        long startTime = System.currentTimeMillis();
        while (true) {
            if (condition.getAsBoolean()) {
                break;
            } else if (timeout != -1 && System.currentTimeMillis() - startTime >= timeout) {
                break;
            }

            sleep(amountToSleep);
        }
    }

    @SuppressWarnings("deprecation")
    public static void suspend(Thread thread) {
        if (thread != null) thread.suspend();
    }
}

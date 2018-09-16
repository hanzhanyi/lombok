package com.iqiyi.lombok.annotations;

import lombok.Synchronized;

/**
 * Created by hanzhanyi on 2018/9/16.
 */
public class SynchronizedExample {
    private final Object readLock = new Object();

    @Synchronized
    public static void hello() {
        System.out.println("world");
    }

    @Synchronized
    public int answerToLife() {
        return 42;
    }

    @Synchronized()
    public void foo() {
        System.out.println("bar");
    }
}
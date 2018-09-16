package com.iqiyi.lombok.annotations;

import lombok.SneakyThrows;

import java.io.UnsupportedEncodingException;

/**
 * Created by hanzhanyi on 2018/9/16.
 */

public class SneakyThrowsExample implements Runnable {
    @SneakyThrows(UnsupportedEncodingException.class)
    public String utf8ToString(byte[] bytes) {
        return new String(bytes, "UTF-8");
    }

    @SneakyThrows
    public void run() {
        throw new Throwable();
    }
}
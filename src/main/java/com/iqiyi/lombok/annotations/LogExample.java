package com.iqiyi.lombok.annotations;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by hanzhanyi on 2018/9/15.
 */
@Slf4j(topic = "hanzhanyi")
public class LogExample {

    public static void main(String... args) {
        log.error("Something else is wrong here");
    }
}


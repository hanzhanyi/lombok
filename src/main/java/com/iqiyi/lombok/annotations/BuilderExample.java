package com.iqiyi.lombok.annotations;

import lombok.Builder;
import lombok.Singular;
import lombok.ToString;

import java.util.HashMap;
import java.util.SortedMap;

/**
 * Created by hanzhanyi on 2018/8/20.
 */
@Builder
@ToString
public class BuilderExample {
    private String name;

    @Singular("iqiyi")
    private SortedMap<String, Object> iqiyis;

    public static void main(String[] args) {
        System.out.println(BuilderExample.builder()
                .iqiyi("key", "value")
                .iqiyi("boss", "bossValue")
                .build().toString());
    }
}
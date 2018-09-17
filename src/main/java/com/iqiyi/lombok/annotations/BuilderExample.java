package com.iqiyi.lombok.annotations;

import lombok.Builder;
import lombok.ToString;

/**
 * Created by hanzhanyi on 2018/8/20.
 */
@Builder(toBuilder = true)
@ToString
public class BuilderExample {
    private String name;

    private String iqiyi;

    private static String boss;

    private final String zq;

    public static void main(String[] args) {
        System.out.println(BuilderExample.builder().name("name").zq("zq").build());
    }
}
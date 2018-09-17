package com.iqiyi.lombok.annotations;

import lombok.Builder;
import lombok.Singular;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by hanzhanyi on 2018/8/20.
 */
@Builder(toBuilder = true)
@ToString
public class BuilderExample {
    private String name;
    private int age;

    private final String hanzhanyi = null;

    private static String boss;

    public String student;

    @Singular
    private Set<String> occupations;

    public static void main(String[] args) {

        BuilderExample example = BuilderExample.builder().occupation("hanzhanyi").occupation("hanzhanyi1").age(2).build();
        System.out.println(example.toBuilder().occupation("linzhongbailang").build());

    }
}
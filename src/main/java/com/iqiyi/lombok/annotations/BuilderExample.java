package com.iqiyi.lombok.annotations;

import lombok.Builder;
import lombok.Singular;

import java.util.Set;

/**
 * Created by hanzhanyi on 2018/8/20.
 */
@Builder
public class BuilderExample {
    private String name;
    private int age;
    @Singular
    private Set<String> occupations;
}
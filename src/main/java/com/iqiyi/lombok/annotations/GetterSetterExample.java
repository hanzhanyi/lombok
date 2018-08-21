package com.iqiyi.lombok.annotations;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by hanzhanyi on 2018/8/20.
 */
public class GetterSetterExample {

    @Getter
    @Setter
    private int age = 10;

    @Setter(AccessLevel.PROTECTED)
    private String name;

    @Override
    public String toString() {
        return String.format("%s (age: %d)", name, age);
    }
}
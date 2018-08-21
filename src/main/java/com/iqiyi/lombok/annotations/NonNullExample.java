package com.iqiyi.lombok.annotations;

import com.iqiyi.lombok.entity.Person;
import com.iqiyi.lombok.entity.Something;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * Created by hanzhanyi on 2018/8/20.
 */
public class NonNullExample extends Something {
    @Getter
    @Setter
    @NonNull
    private String name;

    public NonNullExample() {
    }

    public NonNullExample(@NonNull Person person) {
        super("Hello");
        System.out.println("--1--");
        this.name = person.getName();
    }

    public static void main(String[] args) {
        NonNullExample nonNullExample = new NonNullExample();
        nonNullExample.setName(null);
    }
}
package com.iqiyi.lombok.annotations;

import com.iqiyi.lombok.entity.Shape;
import com.sun.jdi.PathSearchingVirtualMachine;
import lombok.ToString;

/**
 * Created by hanzhanyi on 2018/8/20.
 */

import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@ToString(includeFieldNames = false)
public class ToStringExample {
    private static final int STATIC_VAR = 10;
    private String name;
    private Square shape = new Square(5, 10);
    private String[] tags ;
    private Shape[] shapes;
    private ToStringExample [] toStringExamples;
    @ToString.Exclude
    private int id;

    public String getName() {
        return this.name;
    }

    @ToString(callSuper = true, includeFieldNames = true)
    public static class Square extends Shape {
        private final int width, height;

        Square [] squares ;

        public Square(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }

    public static void main(String[] args) {

        ArrayList collection = new ArrayList();
        collection.add("123");
        collection.add("456");
        System.out.println(collection.toString());
    }
}
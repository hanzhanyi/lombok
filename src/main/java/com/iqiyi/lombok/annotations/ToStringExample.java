package com.iqiyi.lombok.annotations;

import com.iqiyi.lombok.entity.Shape;
import lombok.ToString;

/**
 * Created by hanzhanyi on 2018/8/20.
 */
@ToString(includeFieldNames = true)
public class ToStringExample {
    private static final int STATIC_VAR = 10;
    @ToString.Include(rank = -1)
    private String name;
    @ToString.Include(rank = 1)
    private Shape shape = new Square(5, 10);
    private String[] tags;
    @ToString.Exclude
    private int id;

    public String getName() {
        return this.name;
    }

    @ToString(callSuper = true, includeFieldNames = true)
    public static class Square extends Shape {
        private final int width, height;

        public Square(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }

    public static void main(String[] args) {
        ToStringExample toStringExample = new ToStringExample();
        System.out.println(toStringExample.toString());
    }
}

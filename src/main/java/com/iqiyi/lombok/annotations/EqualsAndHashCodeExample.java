package com.iqiyi.lombok.annotations;

import com.iqiyi.lombok.entity.Shape;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by hanzhanyi on 2018/8/20.
 */
@EqualsAndHashCode
public class EqualsAndHashCodeExample {
    private transient int transientVar = 10;
    private static String boss;
    private String name;
    private String[] tags;

    @EqualsAndHashCode.Exclude
    private int id;

    @EqualsAndHashCode(callSuper = true)
    public static class Square extends Shape {
        private final int width, height;

        public Square(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }
}
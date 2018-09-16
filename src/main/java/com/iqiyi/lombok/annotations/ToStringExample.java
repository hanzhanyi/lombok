package com.iqiyi.lombok.annotations;

import com.iqiyi.lombok.entity.Shape;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by hanzhanyi on 2018/8/20.
 */
@Getter
@ToString(doNotUseGetters = true)
public class ToStringExample {

    @ToString.Include(rank = -1)
    private String name;

    @ToString.Include(rank = 0)
    private String[] tags;

    @ToString.Include(rank = 1, name = "boss")
    private String iqiyi;

    @Getter
    @ToString
    public static class ToStringGetterExample{
        private String student;
    }
}
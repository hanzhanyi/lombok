package com.iqiyi.lombok.annotations;

import com.iqiyi.lombok.entity.Shape;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by hanzhanyi on 2018/8/20.
 */
@ToString()
public class ToStringExample {
    private ToStringExample[] tse;

    public static void main(String[] args) {
        ToStringExample toStringExample = new ToStringExample();

        toStringExample.tse = new ToStringExample[1];

        toStringExample.tse[0] = toStringExample;

        toStringExample.toString();
    }
}
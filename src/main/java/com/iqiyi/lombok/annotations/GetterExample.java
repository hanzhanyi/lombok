package com.iqiyi.lombok.annotations;


import lombok.AccessLevel;
import lombok.Getter;


@lombok.Getter
public class GetterExample {
    @Getter(AccessLevel.NONE)
    private Integer id_1;

    private int id_2;

    @Getter(AccessLevel.PROTECTED)
    private String user;

    @Getter(AccessLevel.PRIVATE)
    private String user_2;

    @Getter(AccessLevel.PACKAGE)
    private String user_3;
}

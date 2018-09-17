package com.iqiyi.lombok.annotations;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of", access = AccessLevel.PROTECTED)
public class RequiredArgsConstructorExample {
    private String name;

    @NonNull
    private String forestWolf;

    private final String iqiyi = "iqiyi";
    
    @NonNull
    private final String boss;
}

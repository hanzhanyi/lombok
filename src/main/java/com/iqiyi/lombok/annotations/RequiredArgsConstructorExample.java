package com.iqiyi.lombok.annotations;

import lombok.*;

@AllArgsConstructor
@Builder
@ToString
public class RequiredArgsConstructorExample {

    private String name;

    @NonNull
    private String forestWolf;

    private final String iqiyi = "iqiyi";

    @NonNull
    private final String boss;

    public static void main(String[] args) {
        System.out.println(RequiredArgsConstructorExample.builder().forestWolf("").name("hanzhanyi").boss("boss").build());
    }
}

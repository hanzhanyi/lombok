package com.iqiyi.lombok.annotations;

/**
 * Created by hanzhanyi on 2018/8/20.
 */

import lombok.*;

@RequiredArgsConstructor(staticName = "of")
//@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ConstructorExample<T> {

    private final String finalString;

    private int x, y;
    @NonNull
    private T description;
    @NonNull
    private String a;

    @NoArgsConstructor
    @Setter
    public static class NoArgsExample {
        @NonNull
        private String field;

    }
}
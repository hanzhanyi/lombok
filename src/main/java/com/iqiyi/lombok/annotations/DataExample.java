package com.iqiyi.lombok.annotations;

import lombok.NonNull;

/**
 * Created by hanzhanyi on 2018/8/20.
 */
@lombok.Data
public class DataExample {
    private String a;

    @NonNull
    private String b;
}

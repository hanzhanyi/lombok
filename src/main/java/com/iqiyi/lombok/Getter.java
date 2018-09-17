package com.iqiyi.lombok;

import lombok.AccessLevel;
import lombok.Setter;

/**
 * Created by hanzhanyi on 2018/8/21.
 */
@Setter
@lombok.Getter
public class Getter {

    @Setter(AccessLevel.PROTECTED)
    private String iqiyi;

    @Setter(AccessLevel.NONE)
    private boolean boss;

    public String getIqiyi() {
        return "getIqiyi";
    }
}

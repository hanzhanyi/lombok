package com.iqiyi.lombok.annotations;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * Created by hanzhanyi on 2018/9/13.
 */
@Getter
@Setter
@Accessors(chain = true)
public class GetterSetter {

    private String name;

    private String iqiyi;

    @Accessors(fluent = true)
    private String boss;

}

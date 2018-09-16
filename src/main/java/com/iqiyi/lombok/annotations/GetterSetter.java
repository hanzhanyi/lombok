package com.iqiyi.lombok.annotations;

import com.iqiyi.lombok.entity.StudentEnum;
import lombok.*;

/**
 * Created by hanzhanyi on 2018/9/13.
 */
@Getter(AccessLevel.PACKAGE)
@ToString
@Setter(AccessLevel.PACKAGE)
public class GetterSetter {
    /**
     * Age of the person. Water is wet.
     *
     * @param name New value for this person's age. Sky is blue.
     * @return The current value of this person's age. Circles are round.
     */
    private String name;

    private boolean isFere;

    private boolean Fere;

    private StudentEnum studentEnum;

    @Setter(AccessLevel.PRIVATE)
    private String hanzhanyi;

    void setName(Integer name) {
        this.name = name.toString();
    }

    String getName(String name) {
        return this.name;
    }

    public String toString(String a) {
        return "name";
    }


    private final GetterSetter getterSetter = new GetterSetter();
}

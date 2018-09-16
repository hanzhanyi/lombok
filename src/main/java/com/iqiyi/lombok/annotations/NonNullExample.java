package com.iqiyi.lombok.annotations;

import com.iqiyi.lombok.entity.Person;
import com.iqiyi.lombok.entity.Something;
import lombok.*;

/**
 * Created by hanzhanyi on 2018/8/20.
 */
@Setter
@AllArgsConstructor
public class NonNullExample extends Something {
    @NonNull
    private String name;

    public NonNullExample(@NonNull Person person) {
        super("HelloWorld");
        this.name = person.getName();
    }

}
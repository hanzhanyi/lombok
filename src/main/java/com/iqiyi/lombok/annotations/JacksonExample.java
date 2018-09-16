package com.iqiyi.lombok.annotations;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.List;

/**
 * Created by hanzhanyi on 2018/9/16.
 */
@Value
@Builder
@JsonDeserialize(builder = JacksonExample.JacksonExampleBuilder.class)
public class JacksonExample {
    @Singular
    private List<Foo> foos;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonExampleBuilder implements JacksonExampleBuilderMeta {
        @Override
        public JacksonExampleBuilder foos(List<? extends Foo> foos) {
            return null;
        }
    }

    private interface JacksonExampleBuilderMeta {
        @JsonDeserialize(contentAs = FooImpl.class) JacksonExampleBuilder foos(List<? extends Foo> foos);
    }
}
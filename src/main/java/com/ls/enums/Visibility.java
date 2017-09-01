package com.ls.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum Visibility {

    @JsonProperty("Public")
    PUBLIC,

    @JsonProperty("Private")
    PRIVATE;

    @JsonCreator
    public static Visibility fromText(final String s) {
        return Visibility.valueOf(s);
    }
}

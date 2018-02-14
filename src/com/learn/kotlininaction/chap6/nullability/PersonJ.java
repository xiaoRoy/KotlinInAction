package com.learn.kotlininaction.chap6.nullability;

import org.jetbrains.annotations.Nullable;

public class PersonJ {

    private final String name;

    public PersonJ(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

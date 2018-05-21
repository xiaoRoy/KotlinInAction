package com.learn.kotlininaction.chap6.collections;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class CollectionUtilsJ {

    public static List<String> uppercaseAll(List<String> texts){
        for(int index = 0, size = texts.size(); index < size; index ++){
            texts.set(index, texts.get(index).toUpperCase());
        }
        return texts;
    }
}

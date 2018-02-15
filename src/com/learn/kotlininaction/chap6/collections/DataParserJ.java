package com.learn.kotlininaction.chap6.collections;

import java.util.List;

public interface DataParserJ <T>{
    void parseData(String input, List<T> output, List<String> errors);
}

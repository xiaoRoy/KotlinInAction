package com.learn.kotlininaction.chap5.lambdaexpression;

import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class UseJavaFunctionalInterface {
    public static void runTask(Runnable task){
        task.run();
    }
}

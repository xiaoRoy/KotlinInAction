package com.learn.kotlininaction.chap5.lambdaexpression;


import java.util.function.Predicate;

public class UseJavaFunctionalInterface {
    public static void runTask(Runnable task){
        task.run();
    }

    public static <T> void filer(T t, Predicate<T> predicate) {
        if(predicate.test(t)){
            System.out.println("matched");
        }
    }
}

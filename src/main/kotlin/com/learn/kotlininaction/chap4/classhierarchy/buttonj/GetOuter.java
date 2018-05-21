package com.learn.kotlininaction.chap4.classhierarchy.buttonj;

public class GetOuter {
    private class Inner{
        GetOuter getOuter(){
            return GetOuter.this;
        }
    }
}

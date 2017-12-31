package com.learn.kotlininaction.chap4.classhierarchy.buttonj;

public interface View {
    State getCurrentState();
    void restoreState(State state);
}

package com.learn.kotlininaction.chap4.classhierarchy.buttonj;

import java.io.*;

public class AppCompatButton implements View{

    @Override
    public State getCurrentState() {
        return new AppCompatButtonState();
    }

    @Override
    public void restoreState(State state) {

    }

    public class AppCompatButtonState implements State{

    }

    public AppCompatButton() {
    }

    public static void main(String[] args) {
        ObjectOutputStream objectOutputStream = null;
        try {
            AppCompatButton appCompatButton = new AppCompatButton();
            objectOutputStream = new ObjectOutputStream(new ByteArrayOutputStream());
            //throw java.io.NotSerializableException
            objectOutputStream.writeObject(appCompatButton.getCurrentState());
        } catch (IOException exception) {
            exception.printStackTrace();
        }finally {
            try {
                if(objectOutputStream != null){
                    objectOutputStream.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }
}

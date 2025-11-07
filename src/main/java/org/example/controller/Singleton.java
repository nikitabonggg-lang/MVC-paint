package org.example.controller;

public class Singleton {
    private int data = 0;
    private static Singleton instance = null;

    private Singleton(){}

    public static Singleton getInstance(){
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
    public int getData() {
        return data;
    }
    public void setData(int data){
        this.data = data;
    }
}

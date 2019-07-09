package com.test;

public interface interfaceClass {
    public void interfacedHello(String str);

    default void defaultHello(String str) {
        System.out.println("default Hi from interface" + str);
    }

    static void defaultstatic(){
        System.out.println("static hi from interface");
    }
}
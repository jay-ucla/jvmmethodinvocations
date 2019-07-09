package com.test;

public class CustomClassLoaderTarget {

    public CustomClassLoaderTarget(){
        System.out.println("Created instance of class");
    }
    private void customClassPrint(){
        System.out.println("I'm custom loaded");
    }
    public void accessMethod(){
        this.customClassPrint();
    }
}

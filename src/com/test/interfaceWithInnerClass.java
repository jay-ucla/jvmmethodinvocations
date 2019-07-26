package com.test;

public interface interfaceWithInnerClass {
    void interfaceInnerTarget();

    class innerClassToInterface{
        static String innerClassString = "inner string";
        void sayHello(){
            System.out.println("Hi this is "+innerClassToInterface.innerClassString);
        }
    }
}

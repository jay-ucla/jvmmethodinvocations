package com.test;

import java.lang.reflect.*;
import java.util.Map;

public class OtherClass {
    private Map proxyInstance;
    private void privateHello(String str){
        System.out.println("Hello from the other world "+str);
    }
    public OtherClass(){
        proxyInstance = (Map) Proxy.newProxyInstance(
                Main.class.getClassLoader(),
                new Class[] { Map.class },
                new DynamicProxy());
    }

    public void instanceHello(){
        privateHello("privately");
    }
    public void instanceHello(String str){
        privateHello(str);
    }
    public void unusedHello(){
        privateHello(". You should not be here.");
    }

    public void refelctOnMe(){
        try {
            Method h = Main.class.getMethod("instanceHello");
            h.invoke(new Main());
        }
        catch(Exception e){
            System.out.println("Caught! You're out!"+e.getMessage());
        }
    }
}

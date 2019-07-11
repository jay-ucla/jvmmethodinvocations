package com.test;

import java.lang.reflect.*;

public class OtherClass {
    private ProxiedClass proxy;
    private void privateHello(String str){
        System.out.println("Hello from the other world "+str);
    }
    public OtherClass(){
        InvocationHandler handler = new DynamicProxy();
        proxy = (ProxiedClass) Proxy.newProxyInstance(
                ProxiedClass.class.getClassLoader(),
                new Class[] { ProxiedClass.class },
                handler);
    }

    public void instanceHello(){
        privateHello("privately");
    }
    public void instanceHello(String str){
        privateHello(str +"privately");
    }
    public void unusedHello(){
        privateHello(". You should not be here.");
    }

    public static void reflectOnMe(Main m){
        try {
            Method h = Main.class.getMethod("reflectiveHello", String.class);
            h.invoke(m, ". This call came from the OtherClass.");
        }
        catch(Exception e){
            System.out.println("Caught! You're out!"+e.getMessage());
        }
    }

    public void dynamicProxyRun(){
        proxy.sayHello();
    }
}

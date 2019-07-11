package com.test;

import java.lang.reflect.Method;
import java.util.function.Predicate;

public class Main {

    public void sayHello(String str){
        for(StackTraceElement e: Thread.currentThread().getStackTrace()){
            if(!e.isNativeMethod())
                System.out.println(e.getClassName()+":"+e.getMethodName());
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Hello world "+str);
    }

    public static void staticHello(){
        System.out.println("Hello static world");
    }
    public static void staticHello(String str){ System.out.println(str);}
    public static boolean lambdaPrint(String str){System.out.println(str); return true;};
    public void instanceHello(String str){
        System.out.println("Hello instance world "+str);
    }
    public void instanceHello(){
        System.out.println("Hello instance world");
    }
    public void innerHello(){
        this.instanceHello();
    }
    public void reflectiveHello(String str){
        System.out.println("Hello reflective world "+str);
    }


    public static void main(String[] args) {
//        for(int i=0;;i++){
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            m.sayHello("Jay"+i);
//        }
        //tests for compiler messages
        Main.staticHello("Performing static invocation");

        Main.staticHello();
        Main.staticHello("Created Main class instance");
        Main m = new Main();
        Predicate<String> lambdaPrint = Main::lambdaPrint;
        lambdaPrint.test("lambdaprint");


        Main.staticHello("Invoked Main instance method");
        m.instanceHello("J");
        Main.staticHello("Invoked other Main instance method");
        m.instanceHello();
        Main.staticHello("Invoking Main inner method");
        m.innerHello();
        Main.staticHello("Creating instance of Other class");
        OtherClass oc = new OtherClass();
        Main.staticHello("Invoked OC instance method - should call private hello");
        oc.instanceHello("J");
        Main.staticHello("Invoked other OC instance method - should call private hello");
        oc.instanceHello();
        Main.staticHello("Invoking main class reflective hello from OC");
        OtherClass.reflectOnMe(m);
        Main.staticHello("Invoking dynamic proxy");
        oc.dynamicProxyRun();

        Main.staticHello("Invoking on object of Concrete A");
        interfaceClass ic = new concreteA();
        ic.interfacedHello("J");
        ic.defaultHello("JJ");
        interfaceClass.defaultstatic();

        Main.staticHello("Creating anonymous class");
        interfaceClass ac = new interfaceClass() {
            @Override
            public void interfacedHello(String str) {
                System.out.println("Hi from the anonymous class");
            }
        } ;
        ac.interfacedHello("");
        ac.defaultHello("JJJ");
        m.sayHello("");
        CustomClassLoader customClassLoader = new CustomClassLoader();
        Class<?> c = null;
        try {
            c = customClassLoader.findClass(CustomClassLoaderTarget.class.getName());
            Object ob = c.newInstance();

            Method md = c.getMethod("accessMethod");
            md.invoke(ob);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

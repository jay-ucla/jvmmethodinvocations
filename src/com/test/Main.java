package com.test;
public class Main {

    public void sayHello(String str){
        for(StackTraceElement e: Thread.currentThread().getStackTrace()){
            if(!e.isNativeMethod())
                System.out.println(e.getClassName()+":"+e.getMethodName());
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Hello world "+str);
    }

    public static void staticHello(){
        System.out.println("Hello world");
    }
    public void instanceHello(String str){
        System.out.println("Hello world "+str);
    }
    public void instanceHello(){
        System.out.println("Hello world");
    }
    public void innerHello(){
        this.instanceHello();
    }
    public void reflectiveHello(String str){
        System.out.println("Hello world "+str);
    }
    public static void main(String[] args) {
//        for(int i=0;;i++){
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            m.sayHello("Jay"+i);
//        }

        //tests for compiler messages

        Main.staticHello();
        Main m = new Main();
        m.instanceHello("J");
    }
}

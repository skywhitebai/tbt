package com.sky.tbt.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThreadTest2 {
    public static class ThreadDemo extends Thread{
        public ThreadDemo(String testName) {
            super.setName(testName);
        }

        public  ThreadDemo(){

        }
        @Override
        public void run() {
            System.out.println("Thread run start");
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+ "This is a Thread test"+i);
            }
            System.out.println("Thread run end");
        }
    }

    @Test
    public  void testStart1(){
        Thread thread=new ThreadDemo("testName");
        thread.start();
        System.out.println("thread is start");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (thread.isAlive()){
            System.out.println("thread is run");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("main thread is over");
    }
}

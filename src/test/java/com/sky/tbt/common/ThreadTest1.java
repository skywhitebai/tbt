package com.sky.tbt.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThreadTest1 {
    public static class ThreadDemo extends Thread{
        @Override
        public void run() {
            System.out.println("Thread run start");
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("This is a Thread test"+i);
            }
            System.out.println("Thread run end");
        }
    }

    @Test
    public  void testStart1(){
        Thread thread=new ThreadDemo();
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
    @Test
    public  void testStart2(){
        Thread thread=new ThreadDemo();
        thread.start();
        System.out.println("thread is start");

        System.out.println("main thread is over");
    }
    @Test
    public void testRun(){
        Thread thread=new ThreadDemo();
        thread.run();
        System.out.println("thread is run");
    }
    @Test
    public  void testNewStart(){
        Thread t = new Thread(new Runnable(){
            public void run(){
                int dealNum=1;
                while(dealNum<3){
                    System.out.println("dealNum:"+dealNum);
                    try {
                        Thread.sleep(dealNum*dealNum*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    dealNum++;
                }

                System.out.println("end:");
            }});
        System.out.println("start1:");
        t.start();
        System.out.println("start2:");
    }
}

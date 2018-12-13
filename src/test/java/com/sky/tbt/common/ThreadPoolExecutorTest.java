package com.sky.tbt.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThreadPoolExecutorTest {
    @Test
    public void testLinkedBlockingQueue1(){
        List<Integer> waitDealList = new ArrayList<>(10);
        for (int i = 0; i < 100; i++) {
            waitDealList.add(i);
        }
        // 创建一个同时允许线程并发执行的线程池,用于创建订单
        ExecutorService executor = new ThreadPoolExecutor(1, 10, 10L,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(10));
        for (Integer num : waitDealList) {
            Runnable runnable = () -> {
                System.out.println( Thread.currentThread().getName()+new Date()+":"+num);
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(new Date()+":Thread.sleep exception:"+e.getMessage());
                    e.printStackTrace();
                }
            };
            executor.execute(runnable);
        }
        executor.shutdown();
        try {
            while (!executor.awaitTermination(3000, TimeUnit.MILLISECONDS)) {
                System.out.println(new Date()+":批量创建中台订单-线程未关闭");
            }
            System.out.println(new Date()+":批量创建中台订单-线程已关闭");
        } catch (InterruptedException e) {
            System.out.println(new Date()+":批量创建中台订单-线程异常"+ e.getMessage());
        }
    }


    @Test
    public void testSynchronousQueue(){
        List<Integer> waitDealList = new ArrayList<>(10);
        for (int i = 0; i < 100; i++) {
            waitDealList.add(i);
        }
        // 创建一个同时允许线程并发执行的线程池,用于创建订单
        ExecutorService executor = new ThreadPoolExecutor(1,20, 10L,
                TimeUnit.SECONDS, new SynchronousQueue<Runnable>(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        for (Integer num : waitDealList) {
            Runnable runnable = () -> {
                System.out.println( Thread.currentThread().getName()+new Date()+":"+num);
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(new Date()+":Thread.sleep exception:"+e.getMessage());
                    e.printStackTrace();
                }
            };
            executor.execute(runnable);
        }
        executor.shutdown();
        try {
            while (!executor.awaitTermination(3000, TimeUnit.MILLISECONDS)) {
                System.out.println(new Date()+":批量创建中台订单-线程未关闭");
            }
            System.out.println(new Date()+":批量创建中台订单-线程已关闭");
        } catch (InterruptedException e) {
            System.out.println(new Date()+":批量创建中台订单-线程异常"+ e.getMessage());
        }
    }

    @Test
    public void testSynchronousQueue2(){
        List<Integer> waitDealList = new ArrayList<>(10);
        AtomicInteger a = new AtomicInteger();
        for (int i = 0; i < 10000; i++) {
            waitDealList.add(i);
        }
        // 创建一个同时允许线程并发执行的线程池,用于创建订单
        ExecutorService executor = new ThreadPoolExecutor(1,20, 10L,
                TimeUnit.SECONDS, new SynchronousQueue<Runnable>(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        for (Integer num : waitDealList) {
            Runnable runnable = () -> {
                System.out.println( Thread.currentThread().getName()+new Date()+":"+num);
                a.getAndAdd(1);
            };
            executor.execute(runnable);
        }
        executor.shutdown();
        try {
            while (!executor.awaitTermination(3000, TimeUnit.MILLISECONDS)) {
                System.out.println(new Date()+":批量创建中台订单-线程未关闭");
            }
            System.out.println(new Date()+":批量创建中台订单-线程已关闭");
        } catch (InterruptedException e) {
            System.out.println(new Date()+":批量创建中台订单-线程异常"+ e.getMessage());
        }
        System.out.println("a:"+a);
    }


}

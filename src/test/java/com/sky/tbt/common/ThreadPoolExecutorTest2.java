package com.sky.tbt.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

import static java.lang.Thread.sleep;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThreadPoolExecutorTest2 {
    Runnable myRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    };
    @Test
    public  void testSynchronousQueue1(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(6, 10, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        doExecutor(executor);
    }
    @Test
    public  void testSynchronousQueue2(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 6, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        doExecutor(executor);
    }
    @Test
    public  void testSynchronousQueue3(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 5, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        doExecutor(executor);
    }
    @Test
    public  void testLinkedBlockingDeque1(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 6, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
        doExecutor(executor);
    }
    @Test
    public  void testLinkedBlockingDeque2(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 4, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
        doExecutor(executor);
    }
    @Test
    public  void testLinkedBlockingDeque3(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 4, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(2));
        doExecutor(executor);
    }
    @Test
    public  void testLinkedBlockingDeque4(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 4, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(1));
        doExecutor(executor);
    }
    private void doExecutor(ThreadPoolExecutor executor) {
        executor.execute(myRunnable);
        executor.execute(myRunnable);
        executor.execute(myRunnable);
        System.out.println("---先开三个---");
        System.out.println("核心线程数" + executor.getCorePoolSize());
        System.out.println("线程池数" + executor.getPoolSize());
        System.out.println("队列任务数" + executor.getQueue().size());
        executor.execute(myRunnable);
        executor.execute(myRunnable);
        executor.execute(myRunnable);
        System.out.println("---再开三个---");
        System.out.println("核心线程数" + executor.getCorePoolSize());
        System.out.println("线程池数" + executor.getPoolSize());
        System.out.println("队列任务数" + executor.getQueue().size());
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("----8秒之后----");
        System.out.println("核心线程数" + executor.getCorePoolSize());
        System.out.println("线程池数" + executor.getPoolSize());
        System.out.println("队列任务数" + executor.getQueue().size());
    }


}

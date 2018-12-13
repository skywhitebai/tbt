package com.sky.tbt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }
    @RequestMapping("/hello2")
    public String index2() {
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
        return "end";
    }
}

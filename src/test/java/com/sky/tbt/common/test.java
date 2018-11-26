package com.sky.tbt.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class test {

    @Test
    public void testAdd(){
        System.out.println("1+1="+(1+1));
    }
}

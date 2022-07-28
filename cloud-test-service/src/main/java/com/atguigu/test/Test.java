package com.atguigu.test;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author lihw
 * @className Test
 * @date 2022-06-24 09:55
 * @description
 */

public class Test {
    private int numA;
    private static int staticNumA;

    {
        System.out.println("numA = " + numA);
        int normalA = 0;
        System.out.println("normalA = " + normalA);
    }

    static {
        System.out.println("staticNumA = " + staticNumA);
        //局部比
        int staticA = 0;
        System.out.println("staticA = " + staticA);
    }


    public static void testA() {
        System.out.println("staticNumA：" + staticNumA);
    }

    public static void main(String[] args) {
        testA();
    }
}

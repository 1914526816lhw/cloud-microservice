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

    public static void main(String[] args) {
        StringBuffer policyErrorMsg = new StringBuffer();
        Integer callType = -1;
        Integer signType = 2;
        boolean policyFlag = checkCallType(callType, signType, policyErrorMsg);

        System.out.println("policyFlag = " + policyFlag);
        System.out.println("policyErrorMsg = " + policyErrorMsg);
    }

    private static boolean checkCallType(Integer callType, Integer signType, StringBuffer policyErrorMsg) {
        boolean policyFlag = false;
        switch (callType) {
            //全部
            case -1: {
                policyFlag = true;
                break;
            }
            //已接
            case 0: {
                //正常通话
                if (signType == 1) {
                    policyFlag = true;
                } else if (signType == 2) {
                    policyErrorMsg.append("通话类型不匹配，策略设置的通话类型为：已接").append("；信令类型为：漏话");
                    policyFlag = false;
                }
                break;
            }
            //未接
            case 1: {
                //漏话
                if (signType == 2) {
                    policyFlag = true;
                } else if (signType == 1) {
                    policyErrorMsg.append("通话类型不匹配，策略设置的通话类型为：未接").append("；信令类型为：正常通话");
                    policyFlag = false;
                }
                break;
            }
        }
        return policyFlag;
    }

}

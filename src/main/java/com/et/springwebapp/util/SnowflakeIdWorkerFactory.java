package com.et.springwebapp.util;

import org.springframework.util.ObjectUtils;

import java.util.HashMap;

/**
 * id生成器静态实现
 * @黄治华
 */
public class SnowflakeIdWorkerFactory {

    //workerId-datacenterId，如：0-1
    private static HashMap<String, SnowflakeIdWorker> idWorkers = new HashMap<>();

    public static long nextId() {
        //通过公共方式获取
        long workerId = 0;
        long datacenterId = 0;
        String idWorkerKey = String.valueOf(workerId) + "-" + String.valueOf(datacenterId);
        SnowflakeIdWorker idWorker = idWorkers.get(idWorkerKey);
        if (ObjectUtils.isEmpty(idWorker)) {
            idWorker = new SnowflakeIdWorker(workerId, datacenterId);
            idWorkers.put(idWorkerKey, idWorker);
        }
        return idWorker.nextId();
    }

    //==============================Test=============================================
    /** 测试 */
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            System.out.println(SnowflakeIdWorkerFactory.nextId());
        }
    }
}
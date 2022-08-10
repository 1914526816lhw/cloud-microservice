package com.atguigu.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.shyiko.mysql.binlog.BinaryLogFileReader;
import com.github.shyiko.mysql.binlog.event.*;
import com.github.shyiko.mysql.binlog.event.deserialization.EventDeserializer;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;

public class Application {

    public static void main(String[] args) throws IOException {
        File file = new File("D:/binlog.000002");
        EventDeserializer eventDeserializer = new EventDeserializer();
        eventDeserializer.setCompatibilityMode(
                EventDeserializer.CompatibilityMode.DATE_AND_TIME_AS_LONG,
                EventDeserializer.CompatibilityMode.CHAR_AND_BINARY_AS_BYTE_ARRAY
        );
        BinaryLogFileReader reader = new BinaryLogFileReader(file, eventDeserializer);
        try {
            for (Event event; (event = reader.readEvent()) != null; ) {
//                System.out.println("===================");
                EventData data = event.getData();
                if (data != null && data.getClass().isAssignableFrom(WriteRowsEventData.class)) {
                    WriteRowsEventData dmlData = (WriteRowsEventData) data;
                    Iterator<Serializable[]> iterator = dmlData.getRows().iterator();
                    while (iterator.hasNext()) {
                        Serializable[] next = iterator.next();
                        Serializable serializable = next[1];
                        Object o = JSON.toJSON(serializable);
                    }
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            reader.close();
        }
    }
}
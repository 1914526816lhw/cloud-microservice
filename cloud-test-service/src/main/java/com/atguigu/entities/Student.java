package com.atguigu.entities;

import cn.hutool.core.date.DateTime;
import lombok.Data;

/**
 * @author lihw
 * @className Student
 * @date 2022-05-19 18:02
 * @description
 */
@Data
public class Student {
    private Integer id;
    private String name;
    private DateTime updateTime;
}

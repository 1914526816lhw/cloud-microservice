package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName：Payment
 * Description：
 *
 * @author lihw
 * CreateTime: 2020/12/14 23:07
 * @version 1.0.0
 */
/*引入lombok*/
/*省掉 get set */
@Data
/*全参数构造器*/
@AllArgsConstructor
/*无参构造器（空参构造器）*/
@NoArgsConstructor
public class Payment implements Serializable {

    private Long id;
    private String serial;


}

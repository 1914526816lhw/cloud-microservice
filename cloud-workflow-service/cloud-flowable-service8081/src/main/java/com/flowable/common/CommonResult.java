package com.flowable.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName CommonResult
 * @Description
 * @date 2022年05月03日 12:35
 */

/*省掉 get set */
@Data
/*全参数构造器*/
@AllArgsConstructor
/*无参构造器（空参构造器）*/
@NoArgsConstructor
/*toString*/
@ToString
public class CommonResult<T> {
    /*404 not found*/
    private Integer code;
    /*消息：success failure*/
    private String message;
    /*数据*/
    private T data;

    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }

}

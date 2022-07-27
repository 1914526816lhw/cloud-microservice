package redis;

import com.atguigu.redis.RedisServiceApplication;
import com.atguigu.redis.service.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author lihw
 * @className Test_redis
 * @date 2022-07-27 16:52
 * @description
 */
@SpringBootTest(classes = RedisServiceApplication.class)
public class Test_redis {

    @Autowired
    private RedisService redisService;

    @Test
    public void test(){
        redisService.setCacheObject("testkey","Hello world");
        Object value = redisService.getCacheObject("testkey");
        System.out.println("value = " + value);

    }
}

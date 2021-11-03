package springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName StreamRabbitMQConsumerMain8802
 * @Description
 * @date 2021年10月30日 23:52
 */
@SpringBootApplication
@EnableEurekaClient
public class StreamRabbitMQConsumerMain8803 {
    public static void main(String[] args) {
        SpringApplication.run(StreamRabbitMQConsumerMain8803.class, args);
    }
}

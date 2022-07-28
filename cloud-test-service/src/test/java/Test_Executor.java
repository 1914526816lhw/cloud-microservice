import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.atguigu.TestApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lihw
 * @className Test
 * @date 2022-07-11 09:01
 * @description
 */

@SpringBootTest(classes = TestApplication.class)
public class Test_Executor {

//    @Autowired
//    private ThreadPoolExecutor threadPoolExecutor;

    private final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,
            30, 30, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(500));


    @Test
    public void test() {



    }

    private void testExecutor(String phone) {
        threadPoolExecutor.execute(() -> {
            try {
//                Thread.sleep(1000);
                Object o = null;
                System.out.println(o.toString());
                System.out.println("phone = " + phone);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

import com.dubbo.api.entity.User;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CountDownLatch;

/**
 * @author kanghaijun
 * @create 2019/6/15
 * @describe
 */
@SpringBootTest(classes={com.dubbo.consume.Application.class })
@WebAppConfiguration
@RunWith(SpringRunner.class)
public class Test {

    RestTemplate rest = new RestTemplate();

    String url = "http://www.dubbo.com/get";

    private static final int THAERDS =200;

    private CountDownLatch cdl = new CountDownLatch(THAERDS);

    @org.junit.Test
    public void test01() {
        User body = rest.getForEntity(url, User.class).getBody();
        System.out.println("第一次"+body);
        for (int i = 0; i < THAERDS; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    cdl.countDown();
                    try {
                        cdl.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    User body = rest.getForEntity(url, User.class).getBody();
                    System.out.println(body);
                }
            }).start();
        }
    }


}

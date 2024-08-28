package club.snow.ihome.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IHomeDemoServiceTest {

    @Autowired
    private IHomeDemoService homeDemoService;

    @Test
    void transactionTest() {

        Thread thread = new Thread(() -> {
            String result = homeDemoService.transactionTest("11");
            System.out.println(result);
        });
        Thread thread1 = new Thread(() -> {
            String result = homeDemoService.transactionTest2("22");
            System.out.println(result);
        });
        thread.setName("test 1");
        thread1.setName("test 2");
        thread.start();
        thread1.start();

        int i = 1;
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(i++);
            }
            System.out.println(i++);
        }
    }
}
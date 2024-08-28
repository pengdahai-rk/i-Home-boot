package club.snow.ihome.service.impl;

import club.snow.ihome.bean.domain.entity.HomeDemoDO;
import club.snow.ihome.dao.HomeDemoDAO;
import club.snow.ihome.service.IHomeDemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;

/**
 * The type IHomeDemoServiceImpl.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.4.18
 */
@Slf4j
@Service
public class IHomeDemoServiceImpl implements IHomeDemoService {

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private HomeDemoDAO homeDemoDAO;

    @Override
    public String getString(String words) {
        return "Hello World!" + words;
    }

    @Override
    public String transactionTest(String words) {

        transactionTemplate.executeWithoutResult(transactionStatus -> {
            HomeDemoDO homeDemoDO = homeDemoDAO.selectByPrimaryKey(1L);
            log.info("home demo 0:{}", homeDemoDO.toString());
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            BigDecimal demoDecimal = homeDemoDO.getDemoDecimal();
            BigDecimal add = demoDecimal.add(new BigDecimal(words));
            homeDemoDO.setDemoDecimal(add);
            int i = homeDemoDAO.updateByPrimaryKey(homeDemoDO);
            log.info("return demo 1 {}", i);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            HomeDemoDO homeDemoDO1 = homeDemoDAO.selectByPrimaryKey(1L);
            log.info("home demo 1:{}", homeDemoDO1.toString());
        });

        return "";
    }

    @Override
    public String transactionTest2(String words) {
        transactionTemplate.executeWithoutResult(transactionStatus -> {
            HomeDemoDO homeDemoDO = homeDemoDAO.selectByPrimaryKey(1L);
            log.info("home demo 2:{}", homeDemoDO.toString());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            HomeDemoDO homeDemoDO2 = homeDemoDAO.selectByPrimaryKey(1L);
            log.info("home demo 3:{}", homeDemoDO2.toString());
            BigDecimal demoDecimal = homeDemoDO.getDemoDecimal();
            BigDecimal add = demoDecimal.add(new BigDecimal(words));
            homeDemoDO.setDemoDecimal(add);
            System.out.println(homeDemoDO);
            int i = homeDemoDAO.updateByPrimaryKey(homeDemoDO);
            log.info("return demo 2 {}", i);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            HomeDemoDO homeDemoDO1 = homeDemoDAO.selectByPrimaryKey(1L);
            log.info("home demo 4:{}", homeDemoDO1.toString());
        });
        return "";
    }
}

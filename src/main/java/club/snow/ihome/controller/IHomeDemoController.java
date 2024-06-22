package club.snow.ihome.controller;

import club.snow.ihome.bean.BaseResult;
import club.snow.ihome.common.enums.BusinessInfoEnum;
import club.snow.ihome.common.exception.BusinessException;
import club.snow.ihome.common.utils.SpringUtil;
import club.snow.ihome.service.IHomeDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * The type IHomeDemoController.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.4.17
 */
@RestController
@RequestMapping("/api/demo")
public class IHomeDemoController {

    @Autowired
    private IHomeDemoService demoService;

    @GetMapping("/get-string")
    public String getString(@RequestParam("words") String words) {
        if (Objects.equals(words, "123")) {
            throw new BusinessException(BusinessInfoEnum.DEMO_TEST_ERROR, List.of("12", "21"));
        }
        return demoService.getString(words);
    }

    @GetMapping("/get-bean")
    public BaseResult getBean(@RequestParam("beanName") String beanName) {
        Object bean = SpringUtil.getBean(beanName);
        return BaseResult.ok();
    }
}

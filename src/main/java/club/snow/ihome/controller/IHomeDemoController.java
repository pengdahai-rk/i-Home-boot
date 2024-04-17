package club.snow.ihome.controller;

import club.snow.ihome.service.IHomeDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type IHomeDemoController.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @since 2024/4/17
 */
@RestController
@RequestMapping("/demo")
public class IHomeDemoController {

    @Autowired
    private IHomeDemoService demoService;

    @GetMapping("/get-string")
    public String getString(@RequestParam("words") String words){
        return demoService.getString(words);
    }
}

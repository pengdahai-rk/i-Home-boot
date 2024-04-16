package club.snow.ihome.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/get-string")
    public String getString(){
        return "Hello World!";
    }
}

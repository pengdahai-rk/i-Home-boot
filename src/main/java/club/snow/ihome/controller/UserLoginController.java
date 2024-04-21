package club.snow.ihome.controller;

import club.snow.ihome.bean.BaseResult;
import club.snow.ihome.bean.LoginUser;
import club.snow.ihome.bean.req.LoginReq;
import club.snow.ihome.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * The type UserLoginController.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @since 2024/4/21
 */
@RestController
public class UserLoginController {

    @Autowired
    private LoginUserService loginUserService;

    @PostMapping("/login")
    public BaseResult<Map<String, Object>> login(@RequestBody LoginReq loginReq) {
        LoginUser loginUser = loginUserService.login(loginReq);
        return BaseResult.ok();
    }

    @PostMapping("/sign")
    public void sign() {

    }

    @PostMapping("/login-out")
    public void loginOut() {

    }
}

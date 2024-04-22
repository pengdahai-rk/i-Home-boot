package club.snow.ihome.controller;

import club.snow.ihome.bean.BaseResult;
import club.snow.ihome.bean.dto.UserLoginDTO;
import club.snow.ihome.bean.req.SignInReq;
import club.snow.ihome.core.TokenService;
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
    @Autowired
    private TokenService tokenService;


    @PostMapping("/sign-in")
    public BaseResult<Map<String, Object>> signIn(@RequestBody SignInReq signInReq) {

        UserLoginDTO userLoginDTO = loginUserService.signIn(signInReq);
        return BaseResult.ok(tokenService.createToken(userLoginDTO));
    }

    @PostMapping("/sign-up")
    public void singUp() {

    }

    @PostMapping("/sign-out")
    public void singOut() {

    }
}

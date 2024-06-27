package club.snow.ihome.service;

import club.snow.ihome.bean.dto.UserLoginDTO;
import club.snow.ihome.bean.req.SignInReq;
import club.snow.ihome.bean.req.SignUpReq;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;


/**
 * The type Login service test.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.06.27
 */
@SpringBootTest
class LoginServiceTest {

    @Autowired
    private LoginService loginService;

    @Test
    void signIn() {
        SignInReq signInReq = new SignInReq();

        signInReq.setUsername("register1");
        signInReq.setEmail("");
        signInReq.setPassword("123456");
        signInReq.setSignInType(0);

        UserLoginDTO userLoginDTO = loginService.signIn(signInReq);
        System.out.println(userLoginDTO);
    }

    @Test
    void signUp() {
        SignUpReq signUpReq = new SignUpReq();
        signUpReq.setUsername("register1");
        signUpReq.setEmail("pengdahai216@126.com1");
        signUpReq.setPhone("18702715850");
        signUpReq.setPassword("123456");
        loginService.signUp(signUpReq);
    }

    @Test
    void getCaptcha() {
        Map<String, Object> captcha = loginService.getCaptcha();
        System.out.println(captcha.get("image"));
    }
}
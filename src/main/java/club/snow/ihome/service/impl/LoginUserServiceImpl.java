package club.snow.ihome.service.impl;

import club.snow.ihome.bean.LoginUser;
import club.snow.ihome.bean.req.LoginReq;
import club.snow.ihome.dao.UserLoginDAO;
import club.snow.ihome.service.LoginUserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type LoginUserServiceImpl.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @since 2024/4/21
 */
@Log4j
@Service
public class LoginUserServiceImpl implements LoginUserService {

    @Autowired
    private UserLoginDAO userLoginDAO;

    @Override
    public LoginUser login(LoginReq loginReq) {

        return null;
    }
}

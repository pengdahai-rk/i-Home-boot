package club.snow.ihome.service.impl;

import club.snow.ihome.bean.domain.entity.UserLoginDO;
import club.snow.ihome.dao.UserLoginDAO;
import club.snow.ihome.service.LoginUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type LoginUserServiceImpl.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @since 2024/4/21
 */
@Slf4j
@Service
public class LoginUserServiceImpl implements LoginUserService {

    @Autowired
    private UserLoginDAO userLoginDAO;

    @Override
    public UserLoginDO getByUsername(String username) {
        return null;
    }
}

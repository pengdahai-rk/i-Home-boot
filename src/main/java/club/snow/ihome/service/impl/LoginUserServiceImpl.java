package club.snow.ihome.service.impl;

import club.snow.ihome.bean.domain.entity.UserLoginDO;
import club.snow.ihome.dao.UserLoginDAO;
import club.snow.ihome.service.LoginUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type LoginUserServiceImpl.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.4.21
 */
@Slf4j
@Service
public class LoginUserServiceImpl implements LoginUserService {

    @Autowired
    private UserLoginDAO userLoginDAO;

    @Override
    public UserLoginDO getByUsername(String username) {
        if (StringUtils.isBlank(username)) {
            return null;
        }
        return userLoginDAO.getByUsername(username);
    }

    @Override
    public UserLoginDO getByEmail(String email) {
        if (StringUtils.isBlank(email)) {
            return null;
        }
        return userLoginDAO.getByEmail(email);
    }
}

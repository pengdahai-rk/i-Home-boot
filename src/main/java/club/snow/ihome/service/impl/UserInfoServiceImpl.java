package club.snow.ihome.service.impl;

import club.snow.ihome.bean.domain.entity.UserInfoDO;
import club.snow.ihome.dao.UserInfoDAO;
import club.snow.ihome.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type User info service.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.06.26
 */
@Slf4j
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDAO userInfoDAO;

    @Override
    public long addUserInfo(UserInfoDO userInfoDO) {

        return userInfoDAO.insertSelective(userInfoDO);
    }
}

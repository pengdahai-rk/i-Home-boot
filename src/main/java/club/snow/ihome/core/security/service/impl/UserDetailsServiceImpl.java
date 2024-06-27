package club.snow.ihome.core.security.service.impl;

import club.snow.ihome.bean.domain.entity.UserLoginDO;
import club.snow.ihome.bean.dto.UserLoginDTO;
import club.snow.ihome.common.enums.BusinessInfoEnum;
import club.snow.ihome.common.enums.YesOrNoEnum;
import club.snow.ihome.common.exception.BusinessException;
import club.snow.ihome.service.LoginUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * The type UserDetailsServiceImpl.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.6.12
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private LoginUserService loginUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserLoginDO userLoginDO = loginUserService.getByUsername(username);
        if (Objects.isNull(userLoginDO)) {
            log.info("登录用户：{} 不存在.", username);
            throw new BusinessException(BusinessInfoEnum.USER_SING_NOT_EXIST);
        } else if (Objects.equals(userLoginDO.getIsActive(), YesOrNoEnum.YES.getCode())) {
            log.info("登录用户：{} 已注销", username);
            throw new BusinessException(BusinessInfoEnum.USER_SING_NOT_EXIST);
        }
        return UserLoginDTO.builder().userId(userLoginDO.getId()).username(userLoginDO.getUsername())
                .email(userLoginDO.getEmail()).password(userLoginDO.getPassword()).phone(userLoginDO.getPhone())
                .build();
    }
}

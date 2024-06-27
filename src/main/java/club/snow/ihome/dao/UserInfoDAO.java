package club.snow.ihome.dao;

import club.snow.ihome.bean.domain.entity.UserInfoDO;
import org.springframework.stereotype.Repository;

/**
 * The interface User info dao.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024 /04/21
 */
@Repository
public interface UserInfoDAO {

    /**
     * Insert selective int.
     *
     * @param record the record
     * @return the int
     */
    int insertSelective(UserInfoDO record);

    /**
     * Select by primary key user info do.
     *
     * @param id the id
     * @return the user info do
     */
    UserInfoDO selectByPrimaryKey(Long id);

    /**
     * Update by primary key selective int.
     *
     * @param record the record
     * @return the int
     */
    int updateByPrimaryKeySelective(UserInfoDO record);
}
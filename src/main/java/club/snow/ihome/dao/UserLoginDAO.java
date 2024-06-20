package club.snow.ihome.dao;

import club.snow.ihome.bean.domain.entity.UserLoginDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * The interface User login dao.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024 /04/18
 */
@Repository
public interface UserLoginDAO {

    /**
     * Delete by primary key int.
     *
     * @param id the id
     * @return the int
     */
    int deleteByPrimaryKey(Long id);

    /**
     * Insert int.
     *
     * @param record the record
     * @return the int
     */
    int insert(UserLoginDO record);

    /**
     * Insert selective int.
     *
     * @param record the record
     * @return the int
     */
    int insertSelective(UserLoginDO record);

    /**
     * Select by primary key user login do.
     *
     * @param id the id
     * @return the user login do
     */
    UserLoginDO selectByPrimaryKey(Long id);

    /**
     * Update by primary key selective int.
     *
     * @param record the record
     * @return the int
     */
    int updateByPrimaryKeySelective(UserLoginDO record);

    /**
     * Update by primary key int.
     *
     * @param record the record
     * @return the int
     */
    int updateByPrimaryKey(UserLoginDO record);
    
    /**
     * Gets by username.
     *
     * @param username the username
     * @return the by username
     */
    UserLoginDO getByUsername(@Param("username") String username);

    /**
     * Gets by email.
     *
     * @param email the email
     * @return the by email
     */
    UserLoginDO getByEmail(@Param("email") String email);
}
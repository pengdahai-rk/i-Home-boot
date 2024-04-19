package club.snow.ihome.dao;

import club.snow.ihome.bean.domain.UserInfoDO;

/**
 * The interface User info dao.
 */
public interface UserInfoDAO {
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
    int insert(UserInfoDO record);

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

    /**
     * Update by primary key int.
     *
     * @param record the record
     * @return the int
     */
    int updateByPrimaryKey(UserInfoDO record);
}
package club.snow.ihome.dao;

import club.snow.ihome.bean.domain.HomeDemoDO;

/**
 * The interface Home demo dao.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @since 2024 /04/19
 */
public interface HomeDemoDao {
    int deleteByPrimaryKey(Long id);

    int insert(HomeDemoDO record);

    int insertSelective(HomeDemoDO record);

    HomeDemoDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HomeDemoDO record);

    int updateByPrimaryKeyWithBLOBs(HomeDemoDO record);

    int updateByPrimaryKey(HomeDemoDO record);
}
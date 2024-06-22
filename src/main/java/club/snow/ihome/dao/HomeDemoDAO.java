package club.snow.ihome.dao;

import club.snow.ihome.bean.domain.entity.HomeDemoDO;
import org.springframework.stereotype.Repository;

/**
 * The interface Home demo dao.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024 /04/19
 */
@Repository
public interface HomeDemoDAO {
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
    int insert(HomeDemoDO record);

    /**
     * Insert selective int.
     *
     * @param record the record
     * @return the int
     */
    int insertSelective(HomeDemoDO record);

    /**
     * Select by primary key home demo do.
     *
     * @param id the id
     * @return the home demo do
     */
    HomeDemoDO selectByPrimaryKey(Long id);

    /**
     * Update by primary key selective int.
     *
     * @param record the record
     * @return the int
     */
    int updateByPrimaryKeySelective(HomeDemoDO record);

    /**
     * Update by primary key with blo bs int.
     *
     * @param record the record
     * @return the int
     */
    int updateByPrimaryKeyWithBLOBs(HomeDemoDO record);

    /**
     * Update by primary key int.
     *
     * @param record the record
     * @return the int
     */
    int updateByPrimaryKey(HomeDemoDO record);
}
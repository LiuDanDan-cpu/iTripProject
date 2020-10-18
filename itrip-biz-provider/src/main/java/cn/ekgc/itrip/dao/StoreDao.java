package cn.ekgc.itrip.dao;

import cn.ekgc.itrip.pojo.entity.Store;
import org.springframework.stereotype.Repository;


/**
 * <b>商品库存</b>
 */
@Repository
public interface StoreDao {
    /**
     * <b>根据所给对象查询最新的库存</b>
     * @param query
     * @return
     * @throws Exception
     */
    Store getListByQuery(Store query)throws Exception;
    /**
     * <b>根据所给对象添加新的库存信息</b>
     * @param store
     * @throws Exception
     */
    void addStore(Store store)throws Exception;
}

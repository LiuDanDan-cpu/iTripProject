package cn.ekgc.itrip.dao;

import cn.ekgc.itrip.pojo.entity.LabelDic;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabelDicDao {
    /**
     * <b>查询酒店特色列表</b>
     * @param query
     * @return
     */
    List<LabelDic> findListByQuery(LabelDic query)throws Exception;

}

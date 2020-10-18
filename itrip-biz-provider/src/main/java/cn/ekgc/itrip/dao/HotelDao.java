package cn.ekgc.itrip.dao;

import cn.ekgc.itrip.pojo.entity.Hotel;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * <b>酒店数据持久层接口</b>
 * @author LiuDanDan
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public interface HotelDao {
    List<Hotel> getListByQuery(Hotel query)throws Exception;
}

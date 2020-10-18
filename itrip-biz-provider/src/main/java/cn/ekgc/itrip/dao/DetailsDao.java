package cn.ekgc.itrip.dao;

import cn.ekgc.itrip.pojo.entity.Hotel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailsDao {
    List<Hotel> getListByHotel(Hotel hotel)throws Exception;
    List<Hotel> getListByDetails(Hotel hotel)throws Exception;
    List<Hotel> getListByArea(Hotel hotel)throws Exception;
    /**
     查询酒店名称
     查询特色
     查询商业圈
     */

}

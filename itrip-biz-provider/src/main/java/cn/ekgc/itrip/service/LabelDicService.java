package cn.ekgc.itrip.service;

import cn.ekgc.itrip.pojo.entity.Hotel;
import cn.ekgc.itrip.pojo.entity.LabelDic;

import java.util.List;

public interface LabelDicService {
    /**
     * <b>查询酒店特色列表</b>
     * @param query
     * @return
     */
    List<LabelDic> getLabelDicListByQuery(LabelDic query)throws Exception;


    /**
     * <b>根据酒店id查询酒店名称</b>
     * @param hotel
     * @return
     */
    String getHotelName(Hotel hotel)throws Exception;
    /**
     * <b>根据酒店查询特色名称</b>
     * @param hotel
     * @return
     */
    List<Hotel> getTradingAreaNameList(Hotel hotel)throws Exception;
    /**
     * <b>根据酒店查询商圈</b>
     * @param hotel
     * @return
     */
    List<Hotel> getListByArea(Hotel hotel)throws Exception;
}

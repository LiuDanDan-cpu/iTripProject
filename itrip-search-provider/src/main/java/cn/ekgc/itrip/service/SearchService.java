package cn.ekgc.itrip.service;

import cn.ekgc.itrip.base.pojo.vo.ResultVO;
import cn.ekgc.itrip.pojo.vo.HotelVO;
import cn.ekgc.itrip.pojo.vo.SearchHotCityVO;
import cn.ekgc.itrip.pojo.vo.SearchHotelVO;

import java.util.List;

/**
 * <b>搜索业务层接口</b>
 * @author LiuDanDan
 * @version 1.0.0
 * @since 1.0.0
 */
public interface SearchService {
    /**
     * <b></b>
     * @param searchHotCityVO
     * @return
     */
    List<HotelVO> getHotelListByHotCity(SearchHotCityVO searchHotCityVO)throws Exception;
    /**
     * <b>查询酒店分页</b>
     * @param query
     * @return
     * @throws Exception
     */
    ResultVO getHotelList(SearchHotelVO query)throws Exception;
}

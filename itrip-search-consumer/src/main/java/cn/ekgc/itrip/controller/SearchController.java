package cn.ekgc.itrip.controller;


import cn.ekgc.itrip.base.pojo.vo.ResultVO;
import cn.ekgc.itrip.pojo.vo.SearchHotCityVO;

import cn.ekgc.itrip.pojo.vo.SearchHotelVO;
import cn.ekgc.itrip.transport.search.SearchTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <b>搜索功能控制层</b>
 * @author LiuDanDan
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController("searchController")
@RequestMapping("/search/api/hotellist")
public class SearchController {
    @Autowired
    private SearchTransport searchTransport;

    @PostMapping("/searchItripHotelListByHotCity")
    public ResultVO HotelListByHotCity(@RequestBody SearchHotCityVO searchHotCityVO)throws Exception{

        return ResultVO.success(searchTransport.getHotelListByHotCity(searchHotCityVO));
    }

    /**
     * <b>查询酒店分页</b>
     * @param searchHotelVO
     * @return
     * @throws Exception
     */
    @PostMapping("/searchItripHotelPage")
    public ResultVO searchItripHotelPage(@RequestBody SearchHotelVO searchHotelVO)throws Exception{

        return searchTransport.getHotelList(searchHotelVO);
    }
}

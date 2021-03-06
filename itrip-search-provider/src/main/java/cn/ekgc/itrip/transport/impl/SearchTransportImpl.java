package cn.ekgc.itrip.transport.impl;

import cn.ekgc.itrip.base.pojo.vo.ResultVO;
import cn.ekgc.itrip.pojo.vo.HotelVO;
import cn.ekgc.itrip.pojo.vo.SearchHotCityVO;
import cn.ekgc.itrip.pojo.vo.SearchHotelVO;
import cn.ekgc.itrip.service.SearchService;
import cn.ekgc.itrip.transport.search.SearchTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("searchTransport")
@RequestMapping("/search/api")
public class SearchTransportImpl implements SearchTransport {
    @Autowired
    private SearchService searchService;
    /**
     * <b></b>
     * @param searchHotCityVO
     * @return
     */
    @PostMapping("/getHotelListByHotCity")
    @Override
    public List<HotelVO> getHotelListByHotCity(@RequestBody SearchHotCityVO searchHotCityVO) throws Exception{
        return searchService.getHotelListByHotCity(searchHotCityVO);
    }
    /**
     * <b>查询酒店分页</b>
     * @param searchHotelVO
     * @return
     * @throws Exception
     */
    @PostMapping("/getHotellist")
    @Override
    public ResultVO getHotelList(@RequestBody SearchHotelVO searchHotelVO) throws Exception {
        return searchService.getHotelList(searchHotelVO);
    }
}

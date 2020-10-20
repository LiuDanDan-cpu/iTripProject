package cn.ekgc.itrip.transport.search;


import cn.ekgc.itrip.base.pojo.vo.ResultVO;
import cn.ekgc.itrip.pojo.vo.HotelVO;
import cn.ekgc.itrip.pojo.vo.SearchHotCityVO;
import cn.ekgc.itrip.pojo.vo.SearchHotelVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <b>搜索功能传输层接口</b>
 *
 * @author LiuDanDan
 * @version 1.0.0
 * @since 1.0.0
 */
@FeignClient(name = "itrip-search-provide")
@RequestMapping("/search/api")
public interface SearchTransport {
    /**
     * <b></b>
     *
     * @param searchHotCityVO
     * @return
     */
    @PostMapping("/getHotelListByHotCity")
    List<HotelVO> getHotelListByHotCity(@RequestBody SearchHotCityVO searchHotCityVO) throws Exception;

    /**
     * <b>查询酒店分页</b>
     * @param searchHotelVO
     * @return
     * @throws Exception
     */
    @PostMapping("/getHotellist")
    ResultVO getHotelList(SearchHotelVO searchHotelVO) throws Exception;
}

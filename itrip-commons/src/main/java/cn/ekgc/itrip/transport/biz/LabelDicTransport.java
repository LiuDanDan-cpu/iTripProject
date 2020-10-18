package cn.ekgc.itrip.transport.biz;

import cn.ekgc.itrip.pojo.entity.Hotel;
import cn.ekgc.itrip.pojo.entity.LabelDic;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "itrip-biz-provider")
@RequestMapping("/labe")
public interface LabelDicTransport {
    /**
     * <b>查询酒店特色列表</b>
     * @param query
     * @return
     */
    @PostMapping("/getlist")
    List<LabelDic> getLabelDicListByQuery(@RequestBody LabelDic query)throws Exception;

    /**
     * <b>根据酒店id查询酒店名称</b>
     * @param hotel
     * @return
     */
    @PostMapping("/getName")
    String getHotelName(@RequestBody Hotel hotel)throws Exception;
    /**
     * <b>根据酒店查询特色名称</b>
     * @param hotel
     * @return
     */
    @PostMapping("/getTradingAreaName")
    List<Hotel> getTradingAreaNameList(@RequestBody Hotel hotel)throws Exception;
    /**
     * <b>根据酒店查询商圈</b>
     * @param hotel
     * @return
     */
    @PostMapping("/getHotelFeature")
    List<Hotel> getHotelFeatureList(@RequestBody Hotel hotel)throws Exception;
}

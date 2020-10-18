package cn.ekgc.itrip.transport.impl;

import cn.ekgc.itrip.pojo.entity.Hotel;
import cn.ekgc.itrip.pojo.entity.LabelDic;
import cn.ekgc.itrip.service.LabelDicService;
import cn.ekgc.itrip.transport.biz.LabelDicTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("labelDicTransport")
@RequestMapping("/labe")
public class LabelDicTransportImpl implements LabelDicTransport {
    @Autowired
    private LabelDicService service;
    /**
     * <b>查询酒店特色列表</b>
     * @param query
     * @return
     */
    @PostMapping("/getlist")
    @Override
    public List<LabelDic> getLabelDicListByQuery(@RequestBody LabelDic query) throws Exception {

        return service.getLabelDicListByQuery(query);
    }

    /**
     * <b>根据酒店id查询酒店名称</b>
     * @param hotel
     * @return
     */
    @PostMapping("/getName")
    @Override
    public String getHotelName(@RequestBody Hotel hotel) throws Exception {
        return service.getHotelName(hotel);
    }
    /**
     * <b>根据酒店查询特色名称</b>
     * @param hotel
     * @return
     */
    @PostMapping("/getTradingAreaName")
    @Override
    public List<Hotel> getTradingAreaNameList(@RequestBody Hotel hotel) throws Exception {
        return service.getTradingAreaNameList(hotel);
    }
    /**
     * <b>根据酒店查询商圈</b>
     * @param hotel
     * @return
     */
    @PostMapping("/getHotelFeature")
    @Override
    public List<Hotel> getHotelFeatureList(@RequestBody Hotel hotel) throws Exception {
        return service.getListByArea(hotel);
    }
}

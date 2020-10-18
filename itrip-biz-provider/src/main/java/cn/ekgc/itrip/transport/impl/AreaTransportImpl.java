package cn.ekgc.itrip.transport.impl;

import cn.ekgc.itrip.pojo.entity.Area;
import cn.ekgc.itrip.service.AreaService;
import cn.ekgc.itrip.transport.biz.AreaTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("areaTransport")
@RequestMapping("/area/transport")
public class AreaTransportImpl implements AreaTransport  {
    @Autowired
    private AreaService areaService;
    /**
     * <b>根据是否是国内信息，查询热门城市</b>
     * @param area
     * @return
     */
    @PostMapping("/queryhotcity")
    @Override
    public List<Area> getAreaByQuery(@RequestBody Area area) throws Exception {
        return areaService.getAreaByQuery(area);
    }
}

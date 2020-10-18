package cn.ekgc.itrip.transport.biz;

import cn.ekgc.itrip.pojo.entity.Area;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "itrip-biz-provider")
@RequestMapping("/area/transport")
public interface AreaTransport {
    /**
     * <b>根据是否是国内信息，查询热门城市</b>
     * @param area
     * @return
     */
    @PostMapping("/queryhotcity")
    List<Area> getAreaByQuery(@RequestBody Area area)throws Exception;
}

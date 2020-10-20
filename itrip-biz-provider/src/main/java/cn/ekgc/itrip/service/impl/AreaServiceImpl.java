package cn.ekgc.itrip.service.impl;

import cn.ekgc.itrip.dao.AreaDao;
import cn.ekgc.itrip.pojo.entity.Area;
import cn.ekgc.itrip.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service("areaService")
@Transactional
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaDao areaDao;
    /**
     * <b>根据是否是国内信息，查询热门城市</b>
     * @param area
     * @return
     */
    @Override
    public List<Area> getAreaByQuery(Area area) throws Exception {
        List<Area> areaList=areaDao.findListByQuery(area);
        return areaList;
    }

}

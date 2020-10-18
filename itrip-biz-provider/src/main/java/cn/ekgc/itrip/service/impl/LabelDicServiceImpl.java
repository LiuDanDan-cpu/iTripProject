package cn.ekgc.itrip.service.impl;

import cn.ekgc.itrip.dao.DetailsDao;
import cn.ekgc.itrip.dao.LabelDicDao;
import cn.ekgc.itrip.pojo.entity.Hotel;
import cn.ekgc.itrip.pojo.entity.LabelDic;
import cn.ekgc.itrip.service.LabelDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("labelDicService")
@Transactional
public class LabelDicServiceImpl implements LabelDicService {
    @Autowired
    private LabelDicDao dao;
    @Autowired
    private DetailsDao detailsDao;
    /**
     * <b>查询酒店特色列表</b>
     * @param query
     * @return
     */
    @Override
    public List<LabelDic> getLabelDicListByQuery(LabelDic query) throws Exception{
        return dao.findListByQuery(query);
    }

    /**
     * <b>根据酒店id查询酒店名称</b>
     * @param hotel
     * @return
     */
    @Override
    public String getHotelName(Hotel hotel) throws Exception {
        List<Hotel> hotelList=detailsDao.getListByHotel(hotel);
        if (hotelList!=null&&hotelList.isEmpty()){
            System.out.println("业务层"+hotelList.get(0).getHotelName());
            return null;
        }
        return hotelList.get(0).getHotelName();
    }

    @Override
    public List<Hotel> getTradingAreaNameList(Hotel hotel) throws Exception {
        return detailsDao.getListByDetails(hotel);
    }

    @Override
    public List<Hotel> getListByArea(Hotel hotel) throws Exception {
        return detailsDao.getListByArea(hotel);
    }
}

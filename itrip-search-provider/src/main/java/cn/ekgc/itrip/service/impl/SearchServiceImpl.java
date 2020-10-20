package cn.ekgc.itrip.service.impl;


import cn.ekgc.itrip.base.pojo.vo.ResultVO;
import cn.ekgc.itrip.pojo.entity.Hotel;
import cn.ekgc.itrip.pojo.vo.HotelVO;
import cn.ekgc.itrip.pojo.vo.Page;
import cn.ekgc.itrip.pojo.vo.SearchHotCityVO;
import cn.ekgc.itrip.pojo.vo.SearchHotelVO;
import cn.ekgc.itrip.service.SearchService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <b>搜索业务层接口实现类</b>
 * @author LiuDanDan
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("searchService")
@Transactional
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SolrClient solrClient;
//    @Autowired
//    private SearchDao searchDao;
    @Override
    public List<HotelVO> getHotelListByHotCity(SearchHotCityVO searchHotCityVO)throws Exception {
        // 在真正运行时，所给定的对象时 HttpSolrClient，因此需要进行强制类型转换
        HttpSolrClient httpSolrClient=(HttpSolrClient)solrClient;
        // 对于核心查询来说，配置到 db-data-config.xml 中，因此使用 XML 解析
        httpSolrClient.setParser(new XMLResponseParser());
        // 创建 Solr 核心查询对象 SolrQuery
        SolrQuery solrQuery=new SolrQuery();
        // 使用城市主键进行查询
        solrQuery.setQuery("cityId:"+searchHotCityVO.getCityId());
        // 设置查询数量
        solrQuery.setRows(searchHotCityVO.getCount());
        // 进行查询，获得 QueryResponse 对象
        QueryResponse queryResponse=solrClient.query(solrQuery);
        // 从 QueryResponse 中提取数据
        List<HotelVO> list=queryResponse.getBeans(HotelVO.class);
        return list;
    }
    /**
     * <b>查询酒店分页</b>
     * @param query
     * @return
     * @throws Exception
     */
    @Override
    public ResultVO getHotelList(SearchHotelVO query) throws Exception {
//        Page<Hotel> page=new Page<>();
//        page.setPageSize(query.getPageSize());
//        page.setCurPage(query.getPageNo());
//        Hotel hotel=new Hotel();
//        hotel.setAddress(query.getDestination());
//        hotel.setHotelLevel(query.getHotelLevel());
//        PageHelper.startPage(page.getCurPage(),page.getPageSize());
//        List<Hotel> list=searchDao.getListByQuery()
//        PageInfo<Hotel> pageInfo=new PageInfo<>()
        return null;
    }
}

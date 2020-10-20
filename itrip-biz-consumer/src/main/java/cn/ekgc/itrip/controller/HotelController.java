package cn.ekgc.itrip.controller;

import cn.ekgc.itrip.base.BaseController;
import cn.ekgc.itrip.base.pojo.vo.ResultVO;
import cn.ekgc.itrip.enums.HotEnum;
import cn.ekgc.itrip.enums.TradingAreaEnum;
import cn.ekgc.itrip.pojo.entity.Area;
import cn.ekgc.itrip.pojo.entity.Hotel;
import cn.ekgc.itrip.pojo.entity.Img;
import cn.ekgc.itrip.pojo.entity.LabelDic;
import cn.ekgc.itrip.pojo.vo.DetailsHotelVO;
import cn.ekgc.itrip.pojo.vo.HotelVideoDescVO;
import cn.ekgc.itrip.transport.biz.AreaTransport;
import cn.ekgc.itrip.transport.biz.HotelTransport;
import cn.ekgc.itrip.transport.biz.LabelDicTransport;
import cn.ekgc.itrip.transport.comment.ImgTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController("bizController")
@RequestMapping("/biz/api/hotel")
public class HotelController extends BaseController {
    @Autowired
    private AreaTransport areaTransport;
    @Autowired
    private LabelDicTransport labelDicTransport;
    @Autowired
    private HotelTransport hotelTransport;
    @Autowired
    private ImgTransport imgTransport;

    /**
     * <b>根据是否是国内信息，查询热门城市</b>
     *
     * @param isChina
     * @return
     * @throws Exception
     */
    @GetMapping("/queryhotcity/{isChina}")
    public ResultVO queryHotCity(@PathVariable Integer isChina) throws Exception {
        //创建查询对象
        Area area = new Area();
        //给对象里边赋值 查询条件
        //设置是否国内城市
        area.setIsChina(isChina);
        //设置是否为热城市
        area.setIsHot(HotEnum.HOT_YES.getCode());
        //进行列表查询
        List<Area> areaList = areaTransport.getAreaByQuery(area);
        return ResultVO.success(areaList);
    }

    /**
     * <b>查询酒店特色列表</b>
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/queryhotelfeature")
    public ResultVO queryHotelFeature() throws Exception {
        // 创建查询对象
        LabelDic query = new LabelDic();
        // 酒店特色信息的 parentId 为 16
        LabelDic parent = new LabelDic();
        parent.setId(16L);
        query.setParent(parent);
        // 进行列表查询
        List<LabelDic> list = labelDicTransport.getLabelDicListByQuery(query);
        return ResultVO.success(list);
    }

    /**
     * <b>根据酒店id查询酒店特色、商圈、酒店名称</b>
     *
     * @param hotelId
     * @return
     * @throws Exception biz/api/hotel/getvideodesc/4
     */
    @GetMapping("/getvideodesc/{hotelId}")
    public ResultVO getVideoDesc(@PathVariable Long hotelId) throws Exception {
        HotelVideoDescVO vo = new HotelVideoDescVO();
        //查询酒店名称
        Hotel query = new Hotel();
        query.setId(hotelId);
        List<Hotel> hotelList = hotelTransport.getListByQuery(query);
        vo.setHotelName(hotelList.get(0).getHotelName());
        //商圈
        LabelDic labelDic = new LabelDic();
        labelDic.setHotelId(hotelId);
        List<LabelDic> labelDicList = labelDicTransport.getLabelDicListByQuery(labelDic);
        //循环集合 添加
        List<String> stringList = new ArrayList<>();
        for (LabelDic label : labelDicList) {
            stringList.add(label.getName());
        }
        vo.setHotelFeatureList(stringList);
        //查询酒店特色
        Area area = new Area();
        area.setHotelId(hotelId);
        List<Area> areaList = areaTransport.getAreaByQuery(area);
        List<String> list = new ArrayList<>();
        for (Area areas : areaList) {
            list.add(areas.getName());
        }
        vo.setTradingAreaNameList(list);
        return ResultVO.success(vo);
    }

    /**
     * <b>查询酒店详情</b>
     *
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("/queryhoteldetails/{id}")
    public ResultVO queryHotelDetails(@PathVariable("id") Long id) throws Exception {
        Hotel query = new Hotel();
        query.setId(id);
        List<Hotel> hotelList = hotelTransport.getListByQuery(query);
        DetailsHotelVO vo = new DetailsHotelVO();
        List<DetailsHotelVO> list = new ArrayList<>();
        vo.setName("酒店详情");
        vo.setDescription(hotelList.get(0).getDetails());
        list.add(vo);
        LabelDic label = new LabelDic();
        label.setHotelId(id);
        List<LabelDic> labelDicList = labelDicTransport.getLabelDicListByQuery(label);
        for (LabelDic labelDic : labelDicList) {
            DetailsHotelVO vo2 = new DetailsHotelVO();
            ;
            vo2.setName(labelDic.getName());
            vo2.setDescription(labelDic.getDescription());
            list.add(vo2);
        }
        return ResultVO.success(list);
    }

    /**
     * <b>查询酒店设施</b>
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("/queryhotelfacilities/{id}")
    public ResultVO getHotelFacilities(@PathVariable("id") Long id)throws Exception{
        Hotel query=new Hotel();
        query.setId(id);
        List<Hotel> labelDicList=hotelTransport.getListByQuery(query);
        return ResultVO.success(labelDicList.get(0).getFacilities());
    }

    /**
     * <b>查询酒店政策</b>
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("/queryhotelpolicy/{id}")
    public ResultVO getHotelPolicy(@PathVariable("id") Long id)throws Exception{
        Hotel query=new Hotel();
        query.setId(id);
        List<Hotel> labelDicList=hotelTransport.getListByQuery(query);
        return ResultVO.success(labelDicList.get(0).getHotelPolicy());
    }

    /**
     * <b> 根据targetId查询酒店图片(type=0)</b>
     * @param targetId
     * @return
     * @throws Exception
     */
    @GetMapping("/getimg/{targetId}")
    public ResultVO getImg(@PathVariable Long targetId)throws Exception{
        Img img=new Img();
        img.setType("0");
        img.setTargetId(targetId);
        List<Img> imgs=imgTransport.getCommentImg(img);
        if (imgs != null) {
            return ResultVO.success(imgs);
        }
        return ResultVO.failure("获取图片失败");
    }

    /**
     * <b> 查询商圈</b>
     * @param cityId
     * @return
     * @throws Exception
     */
    @GetMapping("/querytradearea/{cityId}")
    public ResultVO queryTradeArea(@PathVariable Long cityId)throws Exception{
       //根据城市id查询是否是商圈
        Area query=new Area();
        Area area=new Area();
        area.setId(cityId);
        query.setParent(area);
        query.setIsTradingArea(TradingAreaEnum.TRADING_AREA_YES.getCode());
        List<Area> areaList=areaTransport.getAreaByQuery(query);
        List<String> list=new ArrayList<>();
        if (list!=null){
            for (int i=0;i<areaList.size();i++){
                list.add(i,areaList.get(i).getName());
            }
            return ResultVO.success(areaList);
        }
        return ResultVO.failure("获取失败");

    }

}


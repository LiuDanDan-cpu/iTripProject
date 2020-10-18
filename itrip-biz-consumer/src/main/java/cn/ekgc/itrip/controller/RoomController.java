package cn.ekgc.itrip.controller;

import cn.ekgc.itrip.base.BaseController;
import cn.ekgc.itrip.base.pojo.vo.ResultVO;
import cn.ekgc.itrip.enums.ImgTypeEnum;
import cn.ekgc.itrip.pojo.entity.Img;
import cn.ekgc.itrip.pojo.entity.LabelDic;
import cn.ekgc.itrip.pojo.entity.Room;
import cn.ekgc.itrip.pojo.vo.HotelRoomVO;
import cn.ekgc.itrip.transport.biz.LabelDicTransport;
import cn.ekgc.itrip.transport.biz.RoomTransport;
import cn.ekgc.itrip.transport.comment.ImgTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>房间综合控制层</b>
 * @author LiuDanDan
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController("roomController")
@RequestMapping("/biz/api/hotelroom")
public class RoomController extends BaseController {
    @Autowired
    private LabelDicTransport labelDicTransport;
    @Autowired
    private RoomTransport roomTransport;
    @Autowired
    private ImgTransport imgTransport;

    /**
     * <b>查询所有酒店</b>
     * @param hotelRoomVO
     * @return
     * @throws Exception
     */
    @PostMapping("/queryhotelroombyhotel")
    public ResultVO hotelRoomByHotel(@RequestBody HotelRoomVO hotelRoomVO)throws Exception{
        System.out.println(hotelRoomVO.getHotelId());
        Room query=new Room();
        query.setHotelId(hotelRoomVO.getHotelId());
        List<Room> roomList=roomTransport.getListByQuery(query);
        List<List<Room>> list=new ArrayList<List<Room>>();
        for (Room room : roomList) {
            List<Room> rooms=new ArrayList<>();
            rooms.add(room);
            list.add(rooms);
        }
        return ResultVO.success(list);
    }

    /**
     * <b>获取酒店床型列表</b>
     * @return
     * @throws Exception
     */
    @GetMapping("/queryhotelroombed")
    public ResultVO queryHotelRoomBed()throws Exception{
        LabelDic labelDic=new LabelDic();
        labelDic.setId(1l);
        LabelDic query=new LabelDic();
        query.setParent(labelDic);
        List<LabelDic> bedList=labelDicTransport.getLabelDicListByQuery(query);
        return ResultVO.success(bedList);
    }
    /**
     * <b>查询酒店评论图片</b>
     * @param targetId
     * @return
     * @throws Exception
     */
    @GetMapping("/getimg/{targetId}")
    public ResultVO getImg(@PathVariable Long targetId)throws Exception{
        Img img=new Img();
        //按照其对应的要求封装其对象
        img.setTargetId(targetId);
        img.setType(ImgTypeEnum.IMG_TYPE_EOOM.getCode());
        List<Img> imgList=imgTransport.getCommentImg(img);
        return ResultVO.success(imgList);
    }
}

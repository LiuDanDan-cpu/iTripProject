package cn.ekgc.itrip.pojo.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * <b> 前端输入-查询酒店房间搜索条件VO</b>
 * @author LiuDanDan
 * @version 1.0.0
 * @since 1.0.0
 */
public class HotelRoomVO implements Serializable {
    private static final long serialVersionUID = 2693837172086470829L;
//    ("[必填] 酒店ID")
    private Long hotelId;
//    ("[非必填] 是否预订(0:未预定 1:预订)")
    private Integer isBook;
//    ("[非必填] 是否有早餐(0:没有 1:有)")
    private Integer isHavingBreakfast;
//    ("[非必填] 是否及时响应(0:不及时 1:及时)")
    private Integer isTimelyResponse;
//    ("[非必填] 床型ID")
    private Long roomBedTypeId;
//    ("[必填] 入职日期")
    private Date startDate;
//    ("[必填] 退房日期")
    private Date endDate;
//   ("[非必填] 是否可取消(0:不可以 1:可以)")
    private Integer isCancel;
//    ("[非必填] 支付类型(1:在线付 2:到店付 3:不限)")
    private Integer payType;

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getIsBook() {
        return isBook;
    }

    public void setIsBook(Integer isBook) {
        this.isBook = isBook;
    }

    public Integer getIsHavingBreakfast() {
        return isHavingBreakfast;
    }

    public void setIsHavingBreakfast(Integer isHavingBreakfast) {
        this.isHavingBreakfast = isHavingBreakfast;
    }

    public Integer getIsTimelyResponse() {
        return isTimelyResponse;
    }

    public void setIsTimelyResponse(Integer isTimelyResponse) {
        this.isTimelyResponse = isTimelyResponse;
    }

    public Long getRoomBedTypeId() {
        return roomBedTypeId;
    }

    public void setRoomBedTypeId(Long roomBedTypeId) {
        this.roomBedTypeId = roomBedTypeId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getIsCancel() {
        return isCancel;
    }

    public void setIsCancel(Integer isCancel) {
        this.isCancel = isCancel;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }
}

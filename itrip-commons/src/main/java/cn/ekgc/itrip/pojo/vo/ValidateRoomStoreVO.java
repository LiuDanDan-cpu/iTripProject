package cn.ekgc.itrip.pojo.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * <b>生成订单前,获取预订信息实体类</b>
 * @author LiuDanDan
 * @version 1.0.0
 * @since 1.0.0
 */
public class ValidateRoomStoreVO implements Serializable {
    private static final long serialVersionUID = 6314976658576650762L;
    private Long hotelId;
//    @ApiModelProperty("[必填，注：接收数字类型 房间ID")
    private Long roomId;
//    @ApiModelProperty("[必填，注：接收日期类型 入住时间")
    private Date checkInDate;
//    @ApiModelProperty("[必填，注：接收日期类型 退房时间")
    private Date checkOutDate;
//    @ApiModelProperty("[必填，默认请传1")
    private Integer count;
//    token 用于验证用户
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}

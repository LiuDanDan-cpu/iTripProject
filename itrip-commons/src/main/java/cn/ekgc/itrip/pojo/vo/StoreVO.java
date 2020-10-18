package cn.ekgc.itrip.pojo.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <b>验证房间库存时，返回的库存列表VO</b>
 */
public class StoreVO implements Serializable {
    private static final long serialVersionUID = 6292058475151051300L;
    private Long roomId;
    private BigDecimal price;
    private Long hotelId;
    private String hotelName;

    private Date date;

    private Integer store;

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getStore() {
        return store;
    }

    public void setStore(Integer store) {
        this.store = store;
    }
}

package cn.ekgc.itrip.enums;

/**
 * <b>订单状态枚举类</b>
 */
public enum OrderStatusEnum {
    ORDER_STATUS_ZERO(0,"待支付"),
    ORDER_STATUS_ONE(1,"已取消"),
    ORDER_STATUS_TWO(2,"支付成功"),
    ORDER_STATUS_THREE(3,"已消费"),
    ORDER_STATUS_FOUY(4,"已点评"),
    ;
    private Integer code;
    private String remark;

    OrderStatusEnum(Integer code, String remark) {
        this.code = code;
        this.remark = remark;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

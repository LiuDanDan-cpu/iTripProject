package cn.ekgc.itrip.enums;

/**
 *
 */
public enum CityLevelEnum {
    CITY_LEVEL_COUNTRY(1,"国家级"),
    CITY_LEVEL_PROVINCE(2,"省级"),
    CITY_LEVEL_CITY(3,"市级"),
    CITY_LEVEL_DISTRICT(4,"区县级");

    private Integer code;
    private String remark;

    CityLevelEnum(Integer code, String remark) {
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

package cn.ekgc.itrip.pojo.vo;

import java.io.Serializable;

/**
 * <b>返回前端-酒店特色和介绍VO</b>
 * @author LiuDanDan
 * @version 1.0.0
 * @since 1.0.0
 */
public class DetailsHotelVO implements Serializable {
    private static final long serialVersionUID = 6451949487101705156L;
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

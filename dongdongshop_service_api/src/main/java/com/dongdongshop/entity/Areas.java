package com.dongdongshop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 行政区域县区信息表
 * </p>
 *
 * @author Smoky
 * @since 2022-10-23
 */
@TableName("tb_areas")
public class Areas implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 区域ID
     */
    private String areaid;

    /**
     * 区域名称
     */
    private String area;

    /**
     * 城市ID
     */
    private String cityid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    @Override
    public String toString() {
        return "Areas{" +
            "id=" + id +
            ", areaid=" + areaid +
            ", area=" + area +
            ", cityid=" + cityid +
        "}";
    }
}

package com.tensquare.base.pojo;

/**
 * null
 * 
 * @author wcyong
 * 
 * @date 2019-03-05
 */
public class City {
    /**
     * ID
     */
    private String id;

    /**
     * 城市名称
     */
    private String name;

    /**
     * 是否热门
     */
    private String ishot;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIshot() {
        return ishot;
    }

    public void setIshot(String ishot) {
        this.ishot = ishot == null ? null : ishot.trim();
    }
}
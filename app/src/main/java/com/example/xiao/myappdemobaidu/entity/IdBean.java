package com.example.xiao.myappdemobaidu.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 项目名：MyAppDemoBaidu
 * 包名：com.example.xiao.myappdemobaidu.entity
 * 文件名：IdBean
 * 创建者 ：${梅华黎}
 * 创建时间： 2017/5/2 0002 下午 6:36
 * 描述：greenDao数据库实体类
 */
@Entity
public class IdBean {
    @Id
    private Long id;
    private String name;

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 501051898)
    public IdBean(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 936066209)
    public IdBean() {
    }
}

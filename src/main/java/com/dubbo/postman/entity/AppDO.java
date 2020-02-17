package com.dubbo.postman.entity;

import lombok.Data;

import java.util.Date;
@Data
public class AppDO {

    public Long id;
    public Long zkId;
    public String appName;
    public Date createTime;
    public Date updateTime;


}

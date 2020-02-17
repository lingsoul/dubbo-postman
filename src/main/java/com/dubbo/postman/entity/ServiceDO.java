package com.dubbo.postman.entity;

import lombok.Data;

import java.util.Date;
@Data
public class ServiceDO {

    public Long id;
    public Long zkId;
    public Long appId;
    public String serviceInfo;
    public Date createTime = new Date();
    public Date updateTime = new Date();


}

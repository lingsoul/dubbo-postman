package com.dubbo.postman.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ZkAddressDO {

    public Long id;
    public String zkAddress;
    public Date createTime = new Date();
    public Date updateTime = new Date();

}

package com.dubbo.postman.entity;

import lombok.Data;

import java.util.Date;
@Data
public class TestCaseGroupDO {

    public Long id;
    public String groupName;
    public Date createTime = new Date();
    public Date updateTime = new Date();

}

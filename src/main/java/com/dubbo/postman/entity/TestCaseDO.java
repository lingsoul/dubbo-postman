package com.dubbo.postman.entity;

import lombok.Data;

import java.util.Date;

@Data
public class TestCaseDO {

    public Long id;
    public Long groupId;
    public String caseName;
    public String caseData;
    public Date createTime = new Date();
    public Date updateTime = new Date();



}

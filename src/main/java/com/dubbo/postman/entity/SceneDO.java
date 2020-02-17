package com.dubbo.postman.entity;

import lombok.Data;

import java.util.Date;
@Data
public class SceneDO {

    public Long id;
    public String sceneName;
    public String sceneData;
    public Date createTime;
    public Date updateTime;

}

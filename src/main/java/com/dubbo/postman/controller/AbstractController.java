/*
 * MIT License
 *
 * Copyright (c) 2019 everythingbest
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.dubbo.postman.controller;

import com.dubbo.postman.dao.AppDao;
import com.dubbo.postman.dao.ServiceDao;
import com.dubbo.postman.dao.ZkAddressDao;
import com.dubbo.postman.domain.DubboInterfaceModel;
import com.dubbo.postman.domain.DubboModel;
import com.dubbo.postman.util.JSON;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author everythingbest
 * 提供一些公共的模板方法
 */
@Service
public abstract class AbstractController {


    @Resource
    ZkAddressDao zkAddressDao;

    @Resource
    AppDao appDao;

    @Resource
    ServiceDao serviceDao;


    Map<String,String> getAllClassName(String zk,String serviceName){

        Long zkId = zkAddressDao.getZkId(zk);

        Long appId = appDao.getAppId(zkId,serviceName);

        String modelObj = serviceDao.getServiceDO(zkId,appId).getServiceInfo();

        Map<String,String> interfaceMap = new HashMap<>(10);

        DubboModel dubboModel = JSON.parseObject(modelObj, DubboModel.class);

        for(DubboInterfaceModel serviceModel : dubboModel.getServiceModelList()){

            String className = serviceModel.getInterfaceName();
            String simpleClassName = className.substring(className.lastIndexOf(".")+1);

            interfaceMap.put(simpleClassName,serviceModel.getKey());
        }

        return interfaceMap;
    }
}

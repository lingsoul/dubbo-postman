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

import com.dubbo.postman.dao.ZkAddressDao;
import com.dubbo.postman.dto.WebApiRspDto;
import com.dubbo.postman.entity.ZkAddressDO;
import com.dubbo.postman.service.appfind.zk.ZkServiceFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author everythingbest
 * 目前提供动态添加和删除zk的地址
 */
@Controller
@RequestMapping("/dubbo-postman/")
public class ZkConfigController {
    
    @Value("${dubbo.postman.env}")
    private String env;

    @Resource
    ZkAddressDao zkAddressDao;


    @RequestMapping(value = "configs", method = RequestMethod.GET)
    @ResponseBody
    public WebApiRspDto configs(){

      //  Set<Object> sets = redisRepository.members(RedisKeys.ZK_REDIS_KEY);
        List<String> zks = zkAddressDao.getZkList();
        return WebApiRspDto.success(zks);

    }

    /**
     * 可执行修改其他的值,设置这个主要是防止随意修改注册地址
     */
    private final static String PASSWORD = "123456";

    @RequestMapping(value = "new/config", method = RequestMethod.GET)
    @ResponseBody
    public WebApiRspDto queryDubbo(@RequestParam(name = "zk") String zk,
                                   @RequestParam(name = "password") String password){


        if(!password.equals(PASSWORD)){

            return WebApiRspDto.error("密码错误");
        }
        
        if(zk.isEmpty()){

            return WebApiRspDto.error("zk不能为空");
        }

        if(ZkServiceFactory.ZK_SET.contains(zk)){

            return WebApiRspDto.error("zk地址已经存在");
        }
            
        ZkServiceFactory.ZK_SET.add(zk);

        try {
            ZkServiceFactory.get(zk);
        }catch (Exception exp){
            ZkServiceFactory.ZK_SET.remove(zk);
            return WebApiRspDto.error("zk地址连接失败:"+exp.getMessage());
        }

        ZkAddressDO zkAddressDO = new ZkAddressDO();
        zkAddressDO.setZkAddress(zk);
        zkAddressDao.addZk(zkAddressDO);


        return WebApiRspDto.success("保存成功");
    }
    
    @RequestMapping(value = "zk/del", method = RequestMethod.GET)
    @ResponseBody
    public WebApiRspDto del(@RequestParam(name = "zk") String zk,
                            @RequestParam(name = "password") String password){
        
        if(password.equals(PASSWORD)){
            
            if(zk.isEmpty()){
                
                return WebApiRspDto.error("zk不能为空");
            }
            
            if(!ZkServiceFactory.ZK_SET.contains(zk)){
                
                return WebApiRspDto.error("zk地址不存在");
            }
            
            ZkServiceFactory.ZK_SET.remove(zk);
            
            ZkServiceFactory.ZKSERVICE_MAP.remove(zk);

      //      redisRepository.setRemove(RedisKeys.ZK_REDIS_KEY,zk);

            zkAddressDao.delZk(zk);


            return WebApiRspDto.success("删除成功");
            
        }else {
            
            return WebApiRspDto.error("密码错误");
        }
    }
    
    @RequestMapping(value = "env", method = RequestMethod.GET)
    @ResponseBody
    public WebApiRspDto env(){
        
        return WebApiRspDto.success(env);
    }
}

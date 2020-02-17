package com.dubbo.postman.dao;

import com.dubbo.postman.entity.ServiceDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ServiceDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void addService(ServiceDO serviceDO){

        String sql = "insert into t_service(zk_id,app_id,service_info, create_time, update_time) values(?,?,?,?,?)";
        jdbcTemplate.update(sql,serviceDO.getZkId(),serviceDO.getAppId(),serviceDO.getServiceInfo(),serviceDO.getCreateTime(), serviceDO.getUpdateTime());

    }

    public void delete(Long zkId,Long appId){

        String sql = "delete from t_service where zk_id = ? and app_id = ? ";
        Object [] params = {zkId, appId};
        jdbcTemplate.update(sql,params);

    }


    public List<ServiceDO> getAll(){

        String sql = "select zk_id as zkId,app_id as appId,service_info as serviceInfo from t_service ";
        RowMapper<ServiceDO> rowMapper = new BeanPropertyRowMapper<ServiceDO>(ServiceDO.class);
        List<ServiceDO> list = this.jdbcTemplate.query(sql,rowMapper);
        return list;

    }

    public ServiceDO getServiceDO(Long zkId,Long appId){

        String sql = "select zk_id as zkId,app_id as appId,service_info as serviceInfo from t_service where zk_id = ? and app_id = ? ";
        Object [] params = {zkId, appId};
        RowMapper<ServiceDO> rowMapper = new BeanPropertyRowMapper<ServiceDO>(ServiceDO.class);
        ServiceDO serviceDO = this.jdbcTemplate.queryForObject(sql,params,rowMapper);
        return serviceDO;

    }



}

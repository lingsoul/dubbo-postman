package com.dubbo.postman.dao;

import com.dubbo.postman.entity.AppDO;
import com.dubbo.postman.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AppDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Long addApp(final AppDO appDO){

        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = con -> {
            PreparedStatement ps = con.prepareStatement("INSERT INTO t_app (zk_id,app_name,create_time,update_time) VALUES ("+appDO.getZkId()+",'"+appDO.getAppName()+"','"+ DateUtils.getCurrTime()+"','"+DateUtils.getCurrTime()+"')", Statement.RETURN_GENERATED_KEYS);
            return ps;
        };
        jdbcTemplate.update(preparedStatementCreator, keyHolder);
        return keyHolder.getKey().longValue();

    }


    public Long getAppId(Long zkId,String appName){

        String sql = "select id from t_app where zk_id = ? and app_name = ? ";
        Object [] params = {zkId, appName};

        try {
            return  jdbcTemplate.queryForObject(sql,params,Long.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }


    public String getAppName(Long appId){

        String sql = "select app_name from t_app where id = ? ";
        Object [] params = {appId};
        return jdbcTemplate.queryForObject(sql,params,String.class);

    }


    public List<String> getAppList(Long zkId){

        String sql = "select app_name from t_app where zk_id = ? ";
        Object [] params = {zkId};
        try {
            return this.jdbcTemplate.queryForList(sql,params,String.class);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }


}

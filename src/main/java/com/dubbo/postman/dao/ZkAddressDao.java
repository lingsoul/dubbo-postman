package com.dubbo.postman.dao;

import com.dubbo.postman.entity.ZkAddressDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ZkAddressDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void addZk(ZkAddressDO zkAddressDO){

        String sql = "insert into t_zk_address(zk_address, create_time, update_time) values(?,?,?)";
        jdbcTemplate.update(sql, zkAddressDO.getZkAddress(), zkAddressDO.getCreateTime(), zkAddressDO.getUpdateTime());

    }

    public void delZk(String zk){

        String sql = "delete from t_zk_address where zk_address = ? ";
        jdbcTemplate.update(sql, zk);

    }

    public List<String>  getZkList(){

        String sql = "select zk_address from t_zk_address";
        return this.jdbcTemplate.queryForList(sql,String.class);

    }

    public Long getZkId(String zkAddress){
        String sql = "select id from t_zk_address where zk_address = ? ";
        Object [] params = {zkAddress};
        return this.jdbcTemplate.queryForObject(sql,params,Long.class);
    }


    public String getZkAddress(Long zkId){
        String sql = "select zk_address from t_zk_address where id = ? ";
        Object [] params = {zkId};
        try {
            return this.jdbcTemplate.queryForObject(sql,params,String.class);
        } catch (EmptyResultDataAccessException e) {
           return null;
        }
    }


}

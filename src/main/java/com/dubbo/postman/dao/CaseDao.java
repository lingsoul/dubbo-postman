package com.dubbo.postman.dao;

import com.dubbo.postman.entity.SceneDO;
import com.dubbo.postman.entity.TestCaseDO;
import com.dubbo.postman.entity.TestCaseGroupDO;
import com.dubbo.postman.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

@Repository
public class CaseDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<TestCaseGroupDO> getAllGroup(){

        String sql = "select id,group_name as groupName from t_test_case_group";
        RowMapper<TestCaseGroupDO> rowMapper = new BeanPropertyRowMapper<TestCaseGroupDO>(TestCaseGroupDO.class);
        List<TestCaseGroupDO> list = this.jdbcTemplate.query(sql,rowMapper);
        return list;

    }

    public Long addCaseGroup(TestCaseGroupDO caseGroupDO){

        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = con -> {
            PreparedStatement ps = con.prepareStatement("INSERT INTO t_test_case_group (group_name,create_time,update_time) VALUES ('"+caseGroupDO.getGroupName()+"','"+ DateUtils.getCurrTime()+"','"+DateUtils.getCurrTime()+"')", Statement.RETURN_GENERATED_KEYS);
            return ps;
        };
        jdbcTemplate.update(preparedStatementCreator, keyHolder);
        return keyHolder.getKey().longValue();

    }

    public void addCase(TestCaseDO testCaseDO){

        String sql = "insert into t_test_case(group_id,case_name,case_data, create_time, update_time) values(?,?,?,?,?)";
        Object [] params = {testCaseDO.getGroupId(),testCaseDO.getCaseName(),testCaseDO.getCaseData(),testCaseDO.getCreateTime(),testCaseDO.getUpdateTime()};
        this.jdbcTemplate.update(sql,params);

    }


    public List<TestCaseDO> getTestCaseByGroupId(Long groupId){

        String sql = "select case_data as caseData,case_name as caseName from t_test_case where group_id = ? ";
        Object [] params = {groupId};
        RowMapper<TestCaseDO> rowMapper = new BeanPropertyRowMapper<TestCaseDO>(TestCaseDO.class);
        List<TestCaseDO> list = this.jdbcTemplate.query(sql,params,rowMapper);
        return list;

    }

    public String getCaseData(String groupName,String caseName){

        String sql = "select case_data from t_test_case t1 left join t_test_case_group t2 on t1.group_id = t2.id where t2.group_name = ? and t1.case_name = ? ";
        Object [] params = {groupName,caseName};
        try {
        return this.jdbcTemplate.queryForObject(sql,params,String.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    public Long getCaseId(String groupName,String caseName){

        String sql = "select t1.id from t_test_case t1 left join t_test_case_group t2 on t1.group_id = t2.id where t2.group_name = ? and t1.case_name = ? ";
        Object [] params = {groupName,caseName};
        try {
            return this.jdbcTemplate.queryForObject(sql,params,Long.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void updateCaseData(Long caseId,String caseData){

        String sql = "update t_test_case set case_data = ? ,update_time = now() where id = ?";
        Object [] params = {caseData,caseId};
        this.jdbcTemplate.update(sql,params);

    }

    public Long getGroupId(String groupName){

        String sql = "select id from t_test_case_group where group_name = ? ";
        Object [] params = {groupName};
        try {
            return this.jdbcTemplate.queryForObject(sql,params,Long.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    public void deleteCase(Long groupId,String caseName){

        String sql = "delete from t_test_case where group_id = ? and case_name = ? ";
        Object [] params = {groupId, caseName};
        this.jdbcTemplate.update(sql,params);

    }

    public void addScene(String sceneName,String sceneData){

        String sql = "insert into t_scene(scene_name,scene_data, create_time, update_time) values(?,?,?,?)";
        Object [] params = {sceneName, sceneData,new Date(),new Date()};
        this.jdbcTemplate.update(sql,params);

    }


    public List<SceneDO> getSceneList(){

        String sql = "select scene_name as sceneName,scene_data as sceneData from t_scene";
        RowMapper<SceneDO> rowMapper = new BeanPropertyRowMapper<SceneDO>(SceneDO.class);
        Object [] params = {};
        return this.jdbcTemplate.query(sql,params,rowMapper);

    }

    public SceneDO getSceneByName(String sceneName){

        String sql = "select scene_name as sceneName,scene_data as sceneData from t_scene where scene_name = ? ";
        Object [] params = {sceneName};
        RowMapper<SceneDO> rowMapper = new BeanPropertyRowMapper<SceneDO>(SceneDO.class);
        return this.jdbcTemplate.queryForObject(sql,params,rowMapper);

    }


    public void deleteScene(String sceneName){

        String sql = "delete from t_scene where scene_name = ?";
        Object [] params = {sceneName};
        this.jdbcTemplate.update(sql,params);

    }



}

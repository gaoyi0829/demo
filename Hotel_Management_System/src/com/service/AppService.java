package com.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pojo.Appraise;
import com.pojo.PageTool;
import com.pojo.Condition;

import com.mapper.AppMapper;


public class AppService {
    private AppMapper mapper;



    public AppMapper getMapper() {
        return mapper;
    }
    public void setMapper(AppMapper mapper) {
        this.mapper = mapper;
    }
    //查询所有Appraise
    public List<Appraise> selectAll(){
        System.out.println(mapper.selectAll());
        return mapper.selectAll();
    }
    public int showAppCount() {
        return mapper.selectAppCount();
    }
    public List<Appraise> showAppPage(PageTool pt) {
        return 	mapper.selectAppPage(pt);

    }
    //AddEmployee
    public List<Integer> selectId() {
        return mapper.selectId();

    }
    public void addAppraise(Appraise app) {
        mapper.addAppraise(app);
    }
    //DeleteEmployee
    public void delAppraise(Appraise app) {
        mapper.delAppraise(app);
    }
    public void delAppraises(@Param("appId") List<Integer> appId) {
        mapper.delAppraises(appId);
    }
    //UpdateEmployee
    public void updateAppraise(Appraise app) {
        System.out.println("跳入service");
        mapper.updateAppraise(app);
    }
    //SearchOrder
    public List<Appraise> searchAppByCon(String cond,PageTool pt){
        return mapper.searchAppByCon(cond,pt);
    }
    public int selectAppCountByCon(String cond) {
        return 	mapper.selectAppCountByCon(cond);
    }

}
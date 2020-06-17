package com.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mapper.OrderMapper;
import com.pojo.Order1;
import com.pojo.PageTool;
import com.pojo.Condition;
import com.pojo.Customer;
import com.pojo.LayuiTransform;

public class OrderService {
    OrderMapper orderMapper;

    public OrderMapper getOrderMapper() {
        return orderMapper;
    }

    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }
    //查询所有
    public List<Order1>selectAll(){
        System.out.println(orderMapper.selectAll());
        return orderMapper.selectAll();
    }
    public int showOrderCount() {
        return orderMapper.selectOrderCount();
    }
    public List<Order1> showOrderPage(PageTool pt) {
        return 	orderMapper.selectOrderPage(pt);

    }
    //查询一个通过顾客id
    //SearchOrder
    public List<Order1> searchOrd(Condition cond){
        return orderMapper.searchOrd(cond);
    }
    public int showSEmpCount(Condition cond) {
        return 	orderMapper.selectSOrdCount(cond);
    }

    //查询一个通过顾客id 查订单状态
    //SearchOrder
    public List<Order1> searchOrd2(Condition cond){
        return orderMapper.searchOrd2(cond);
    }
    public int showSEmpCount2(Condition cond) {
        return 	orderMapper.selectSOrdCount2(cond);
    }


    public List<Order1> searchAllOrd3(Integer cusPage,Integer cusSize,String id){
        return orderMapper.searchAllOrd3(cusPage,cusSize,id);
    }
    public List<Order1> searchOrd3(Integer cusPage,Integer cusSize,String id,String condition){
        return orderMapper.searchOrd3(cusPage,cusSize,id,condition);
    }
    public int showOrdCount3(String id,String cond) {
        return 	orderMapper.selectOrdCount3(id,cond);
    }
    public int showOrdAllCount3(String id) {
        return 	orderMapper.selectAllOrdCount3(id);
    }


    //AddOrder
    public List<Integer> selectId(){
        return orderMapper.selectId();

    }
    public void addOrder(Order1 order1) {
        orderMapper.addOrder(order1);
    }
    //DeleteOrder
    public void delOrder(Order1 order1) {
        orderMapper.delOrder(order1);
    }
    public void delOrders(@Param("orderIds") List<Integer> orderIds) {
        System.out.println("调用orderdels方法");
        orderMapper.delOrders(orderIds);
    }
    //UpdateOrder
    public void updateOrder(Order1 order1) {
        orderMapper.updateOrder(order1);
    }


    public Customer searchCus(String id){
        return orderMapper.searchCus(id);
    }




}
package com.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mapper.OrderMapper;
import com.pojo.Condition;
import com.pojo.Customer;
import com.pojo.LayuiTransform;
import com.pojo.Order1;
import com.pojo.PageTool;
import com.service.OrderService;

@Controller
public class OrderController {
    ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
    OrderService orderService=ac.getBean("OrderService",OrderService.class);
    @RequestMapping("ordersList")
    @ResponseBody
    public LayuiTransform<Order1> ordersList(@RequestParam("ordersPage") Integer page
            , @RequestParam("ordersSize") Integer size
            , @RequestParam("condition") String condition){
        System.out.println("-------------------------------------------condition="+condition);
        PageTool pt=new PageTool((page-1)*10,size);
//		List<Order1> orders=orderService.showOrderPage(pt);
//		System.out.println(orders);
//		int count=orderService.showOrderCount();
//		System.out.println("count:"+count);
//		LayuiTransform<Order1> Lorders=new LayuiTransform<Order1>(0,"",orders,count);
//		System.out.println("Lorders"+Lorders);
//	    return Lorders;
        LayuiTransform<Order1>Lorders = new LayuiTransform<Order1>();
        if(condition.equals("")) {
            List<Order1> ords=orderService.showOrderPage(pt);
            int count=orderService.showOrderCount();
            Lorders=new LayuiTransform<Order1>(0, "", ords,count);

            //System.out.println("----------------------------------------------"+Lorders);
        }else {
            Condition c=new Condition(condition);

            List<Order1> ords=orderService.searchOrd(c);
            System.out.println("-------------------------------------------emp="+ords);
            int count=0;
			/*List<Order1> newOrds=new ArrayList<>();
			newOrds.add(ord);*/
            Lorders=new LayuiTransform<Order1>(0, "", ords,count);
            System.out.println("----------------------------------------------"+Lorders);
            if(ords==null) {
                List<Order1> newEmps2=new ArrayList<>();
                count=1;
                Order1 nemp=new Order1();
                newEmps2.add(nemp);
                Lorders=new LayuiTransform<Order1>(1, "查无此项", newEmps2,count);
            }
        }
        System.out.println("----------------------------------------------=="+Lorders);
        return Lorders;
    }
//	@RequestMapping("/ordersByCustomerIdList")
//	@ResponseBody
//	public LayuiTransform<Order1> ordersByCustomerIdList(@RequestParam("ordersPage") Integer page
//			, @RequestParam("ordersSize") Integer size
//			, @RequestParam("condition") String condition){
//		System.out.println("-------------------------------------------condition="+condition);
//		PageTool pt=new PageTool((page-1)*10,size);
//		List<Order1> ordersByCusId=orderService.selectOrderByCustomerId();
//		System.out.println(ordersByCusId);
//		int count=orderService.showOrderByCustomerIdCount();
//		System.out.println("count:"+count);
//		LayuiTransform<Order1> LordersByCusId=new LayuiTransform<Order1>(0,"",ordersByCusId,count);
//		System.out.println("LordersByCusId"+LordersByCusId);
//		return LordersByCusId;
//
//	}

    @RequestMapping("addOrder")
    @ResponseBody
    public int addOrder(
            @RequestParam("orderState") String orderState
            ,@RequestParam("roomId") int roomId
            ,@RequestParam("inDate") Date inDate
            ,@RequestParam("outDate") Date outDate
            ,@RequestParam("customerId") String customerId
            ,@RequestParam("customerName") String customerName
            ,@RequestParam("customerPhone") String customerPhone
            ,@RequestParam("roomType") String roomType
            ,@RequestParam("price") double price)throws IOException{
//			List<Integer> ordersId=orderService.selectId();
        int ayon=1;

        Order1 orderOfAdd=new Order1(orderState, roomId, inDate, outDate, customerId, customerName, customerPhone, roomType, price);

        orderService.addOrder(orderOfAdd);
        return ayon;
    }

    //DeleteOrder
    @RequestMapping("delOrders")
    @ResponseBody
    public int delOrder( @RequestParam("orderIds") List<Integer> orderIds) throws UnsupportedEncodingException  {
        //URLDecoder.decode(txt, "UTF-8");
        System.out.println("跳入orderIds");
        int dyon=-1;
//		System.out.println(orderids.toString());
//		orderService.delOrders(orderids);
        if(orderIds.size()>0) {
            System.out.println(orderIds);
            orderService.delOrders(orderIds);
            dyon=1;
        }else if(orderIds.size()<1) {
            dyon=0;
        }
        return dyon;
    }

    //updateOrder
    @RequestMapping("updateOrder")
    @ResponseBody
    public void updateOrder(
            @RequestParam("orderId") int orderId
            ,@RequestParam("orderState") String orderState
            ,@RequestParam("roomId") int roomId
            ,@RequestParam("inDate") Date inDate
            ,@RequestParam("outDate") Date outDate
            ,@RequestParam("customerId") String customerId
            ,@RequestParam("customerName") String customerName
            ,@RequestParam("customerPhone") String customerPhone
            ,@RequestParam("roomType") String roomType
            ,@RequestParam("price") double price){
        Order1 order1= new Order1(orderId, orderState, roomId, inDate, outDate, customerId, customerName, customerPhone, roomType, price);
        orderService.updateOrder(order1);
    }



    @RequestMapping(value="ordersList2",method = RequestMethod.POST)
    @ResponseBody
    public LayuiTransform<Order1> ordersList2(@RequestParam("ordersPage") Integer page
            , @RequestParam("ordersSize") Integer size
            , @RequestParam("condition") String condition ,HttpServletRequest req) throws Exception{
        System.out.println("-------------------------------------------condition="+condition);
        PageTool pt=new PageTool((page-1)*10,size);
//		List<Order1> orders=orderService.showOrderPage(pt);
//		System.out.println(orders);
//		int count=orderService.showOrderCount();
//		System.out.println("count:"+count);
//		LayuiTransform<Order1> Lorders=new LayuiTransform<Order1>(0,"",orders,count);
//		System.out.println("Lorders"+Lorders);
//	    return Lorders;
        LayuiTransform<Order1>Lorders = new LayuiTransform<Order1>();
        if(condition.equals("")) {
            List<Order1> ords=orderService.showOrderPage(pt);
            int count=orderService.showOrderCount();
            Lorders=new LayuiTransform<Order1>(0, "", ords,count);

            //System.out.println("----------------------------------------------"+Lorders);
        }else {
            Condition c=new Condition(condition);
            System.out.println("c--------------------------------------------------"+c);
            List<Order1> ords=orderService.searchOrd2(c);
            System.out.println("-------------------------------------------emp="+ords);
            int count=0;
			/*List<Order1> newOrds=new ArrayList<>();
			newOrds.add(ord);*/
            Lorders=new LayuiTransform<Order1>(0, "", ords,count);
            System.out.println("----------------------------------------------"+Lorders);
            if(ords==null) {
                List<Order1> newEmps2=new ArrayList<>();
                count=1;
                Order1 nemp=new Order1();
                newEmps2.add(nemp);
                Lorders=new LayuiTransform<Order1>(1, "查无此项", newEmps2,count);
            }
        }
        System.out.println("----------------------------------------------=="+Lorders);
        return Lorders;
    }

    @RequestMapping(value="ordersList3",method = RequestMethod.POST)
    @ResponseBody
    public LayuiTransform<Order1> ordersList3(
            @RequestParam("ordersPage") Integer page
            , @RequestParam("ordersSize") Integer size
            ,@RequestParam("id") String id
            ,@RequestParam("condition") String condition ,HttpServletRequest req) throws Exception{
        PageTool pt=new PageTool((page-1)*10,size);

        System.out.println("-------------------------------------------condition="+condition);
        System.out.println("-------------------------------------------id="+id);
        List<Order1> cusList;
        int count=0;
        if(condition.equals("")||condition==null) {
            cusList=orderService.searchAllOrd3((page-1)*10, size, id);
            count=orderService.showOrdAllCount3(id);
        }else {
            cusList=orderService.searchOrd3((page-1)*10,size,id,condition);
            count=orderService.showOrdCount3(id, condition);
        }
        LayuiTransform<Order1>Lcus = new LayuiTransform<Order1>();

        Lcus=new LayuiTransform<Order1>(0, "", cusList,count);
        System.out.println("Lcus====="+Lcus);
        return Lcus;
    }


    @RequestMapping(value="/ordersList4",method = RequestMethod.POST)
    @ResponseBody
    public LayuiTransform<Customer> ordersList4(
            @RequestParam("cusPage") Integer page
            , @RequestParam("cusSize") Integer size
            ,@RequestParam("id") String id,HttpServletRequest req) throws Exception{
        PageTool pt=new PageTool((page-1)*10,size)	;
        List<Customer> cusList=new ArrayList<Customer>();
        int count=1;
        Customer cus=orderService.searchCus(id);
        System.out.println("_________________----_cus====="+cus);
        cusList.add(cus);


        LayuiTransform<Customer>Lcus = new LayuiTransform<Customer>();
        Lcus=new LayuiTransform<Customer>(0, "", cusList, count);
        return Lcus;

    }
}

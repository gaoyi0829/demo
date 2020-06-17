package com.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.AppService;

import com.pojo.Appraise;
import com.pojo.Condition;
import com.pojo.LayuiTransform;
import com.pojo.PageTool;

@Controller
public class MyController {
    ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
    AppService serv=ac.getBean("AppService",AppService.class);
    @RequestMapping("/appsList")
    @ResponseBody
    public LayuiTransform<Appraise> appsList(@RequestParam("appsPage") Integer appsPage
            ,@RequestParam("appsSize") Integer appsSize
            ,@RequestParam("condition") String condition){
        //System.out.println("-------------------------------------------condition="+condition);
        System.out.println("------------------------------------------appsPage="+appsPage);

        PageTool pt=new PageTool((appsPage-1)*10,appsSize);

        List<Appraise> apps=serv.showAppPage(pt);
        System.out.println(apps);
        int count=serv.showAppCount();
        System.out.println();
        LayuiTransform<Appraise> Lapps=new LayuiTransform<Appraise>(0,"",apps,count);

        return Lapps;
    }
    //SelectByRoomType
    @RequestMapping(value="/appsList2",method = RequestMethod.POST)
    @ResponseBody
    public LayuiTransform<Appraise> specialList(@RequestParam("appsPage") Integer page
            , @RequestParam("appsSize")	 Integer size
            , @RequestParam("condition") String condition,HttpServletRequest req){
        PageTool pt=new PageTool((page-1)*10,size);
        LayuiTransform<Appraise>Lapps = new LayuiTransform<Appraise>();
        if(condition.equals("")) {
            List<Appraise> app=serv.showAppPage(pt);
            int count=serv.showAppCount();
            Lapps=new LayuiTransform<Appraise>(0, "",app,count);

            System.out.println("----------------------------------------------"+Lapps);
        }else {
			/*Condition c=new Condition(condition);
			System.out.println("-------------------------------------------c="+c);*/
            List<Appraise> app=serv.searchAppByCon(condition,pt);
            System.out.println("-------------------------------------------Spe="+app);
            int count=serv.selectAppCountByCon(condition);
		/*	List<SpecialCase> newSpe=new ArrayList<>();
			newSpe.add(spe);*/
            Lapps=new LayuiTransform<Appraise>(0, "", app,count);
            System.out.println("----------------------------------------------"+Lapps);
            if(app==null) {
                List<Appraise> newEmps2=new ArrayList<>();
                count=1;
                Appraise nemp=new Appraise();
                newEmps2.add(nemp);
                Lapps=new LayuiTransform<Appraise>(1, "查无此项", newEmps2,count);
            }
        }
        System.out.println("----------------------------------------------=="+Lapps);
        return Lapps;

    }
    //AddEmployee
    @RequestMapping("addAppraise")
    @ResponseBody
    public int addAppraise(
            @RequestParam("cusId") String cusId
            ,@RequestParam("appStar") String appStar
            ,@RequestParam("roomType") String roomType
            ,@RequestParam("cusName") String cusName
            ,@RequestParam("appContent") String appContent
            ,@RequestParam("appDate") String appDate

    ) throws IOException  {


        List<Integer> appsId=serv.selectId();


        int ayon=1;
        String appStar1=appStar+"星";
        System.out.println(appStar1);
        Appraise appOfAdd=new Appraise(cusId,appStar1,roomType,cusName,appContent,appDate);
	/*	System.out.println("empId"+empId);
		System.out.println("empName"+empName);
		System.out.println("empGender"+empGender);
		System.out.println("empAge"+empAge);
		System.out.println("empPosition"+empPosition);

		System.out.println("empsId"+empsId);
		*/


        if(ayon==1) {
            System.out.println("-------------------------------"+appOfAdd);
            serv.addAppraise(appOfAdd);
        }else {

        }

        return ayon;
    }
    //UpdateEmployee
    @RequestMapping("/updateAppraise")
    @ResponseBody
    public void updateAppraise(
            @RequestParam("appId") String appId
            ,@RequestParam("cusId") String cusId
            ,@RequestParam("appStar") String appStar
            ,@RequestParam("roomType") String roomType
            ,@RequestParam("cusName") String cusName
            ,@RequestParam("appContent") String appContent
            ,@RequestParam("appDate") String appDate
            ,@RequestParam("appReply") String appReply
            ,@RequestParam("replyDate") String replyDate
    ) throws UnsupportedEncodingException  {
        System.out.println("1");
        int iappId=Integer.parseInt(appId);



        System.out.println("2");
        //URLDecoder.decode(txt, "UTF-8");
        Appraise app=new Appraise(iappId,cusId,appStar,roomType,cusName,appContent,appDate,appReply,replyDate);
        System.out.println("3");
        serv.updateAppraise(app);

    }
    //DeleteEmployee
    @RequestMapping("/delAppraise")
    @ResponseBody
    public int delAppraise( @RequestParam("appId") List<Integer> appId) throws UnsupportedEncodingException  {
        //URLDecoder.decode(txt, "UTF-8");
        int dyon=-1;
        if(appId.size()>0) {
            System.out.println(appId);
            serv.delAppraises(appId);
            dyon=1;
        }else if(appId.size()<1) {
            dyon=0;
        }
        return dyon;
    }


}
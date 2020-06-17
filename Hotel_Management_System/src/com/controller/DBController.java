package com.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mapper.HSMSmapper;
import com.pojo.Complaint;
import com.pojo.Condition;
import com.pojo.Employee;
import com.pojo.LayuiTransform;
import com.pojo.PageTool;
import com.pojo.Praise;
import com.pojo.SpecialCase;
import com.pojo.Suggestion;
import com.pojo.Supply;
import com.service.HSMSservice;

@Controller
public class DBController {
    //SelectEmployees
    @RequestMapping("/empsList")
    @ResponseBody
    public LayuiTransform<Employee> empsList(@RequestParam("empsPage") Integer page
            , @RequestParam("empsSize") Integer size
            , @RequestParam("condition") String condition){
//		public LayuiTransform<Employee> empsList(Integer empsPage
//				, Integer empsSize
//				, String condition){
        //System.out.println("-------------------------------------------condition="+condition);
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        HSMSservice serv=ac.getBean("HSMSservice",HSMSservice.class);
        ////PageTool pt=new PageTool((page-1)*10,size);
        PageTool pt=new PageTool((page-1)*10,size);
        LayuiTransform<Employee> Lemps=new LayuiTransform<Employee>();
        if(condition.equals("")) {
            List<Employee> emps=serv.showEmpPage(pt);
            int count=serv.showEmpCount();
            Lemps=new LayuiTransform<Employee>(0, "", emps,count);

            //System.out.println("----------------------------------------------"+Lemps);
        }else {
            Condition c=new Condition(condition);
            System.out.println("-------------------------------------------c="+c);
            Employee emp=serv.searchEmp(c);
            System.out.println("-------------------------------------------emp="+emp);
            //	System.out.println(emp);
            int count=0;
            List<Employee> newEmps=new ArrayList<>();
            newEmps.add(emp);
            Lemps=new LayuiTransform<Employee>(0, "", newEmps,count);
            System.out.println("----------------------------------------------"+Lemps);
            if(emp==null) {
                List<Employee> newEmps2=new ArrayList<>();
                count=1;
                Employee nemp=new Employee();
                newEmps2.add(nemp);
                Lemps=new LayuiTransform<Employee>(1, "查无此项", newEmps2,count);
            }
        }
        System.out.println("----------------------------------------------=="+Lemps);
        return Lemps;

    }
    //SelectSupply
    @RequestMapping("/supplyList")
    @ResponseBody
    public LayuiTransform<Supply> supplyList(@RequestParam("supplyPage") Integer page
            , @RequestParam("supplySize") Integer size
            , @RequestParam("condition") String condition){
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        HSMSservice serv=ac.getBean("HSMSservice",HSMSservice.class);
        PageTool pt=new PageTool((page-1)*10,size);
        LayuiTransform<Supply> LSupp=null;
		/*
		//System.out.println("-----------------------+++++++++++"+serv.showSupp());
		List<Supply> supp=serv.showSuppPage(pt);
		int count=serv.showSuppCount();
		//System.out.println("----------------------------------------------"+supp);
		if(supp==null) {
			List<Supply> newSupp2=new ArrayList<>();
			count=1;
			Supply nsupp=new Supply();
			newSupp2.add(nsupp);
			LSupp=new LayuiTransform<Supply>(1, "查无此项", newSupp2,count);
		}

		LSupp=new LayuiTransform<Supply>(0, "", supp,count);
		System.out.println("----------------------------------------------"+LSupp);*/
        if(condition.equals("")) {
            List<Supply> supp=serv.showSuppPage(pt);
            int count=serv.showSuppCount();
            LSupp=new LayuiTransform<Supply>(0, "",supp,count);

            //System.out.println("----------------------------------------------"+Lemps);
        }else {
            condition='%'+condition+'%';
            Condition c=new Condition(condition);
            System.out.println("-------------------------------------------c="+c);
            List<Supply> supp=serv.searchSupp(c);
            System.out.println("-------------------------------------------supp="+supp);
            //	System.out.println(emp);
            int count=0;
		/*	List<Supply> newSupp=new ArrayList<>();
			newSupp.add(supp);*/
            LSupp=new LayuiTransform<Supply>(0, "", supp,count);
            System.out.println("----------------------------------------------"+LSupp);
            if(supp==null) {
                List<Supply> newSupp2=new ArrayList<>();
                count=1;
                Supply nsupp=new Supply();
                newSupp2.add(nsupp);
                LSupp=new LayuiTransform<Supply>(1, "查无此项", newSupp2,count);
            }
        }
        System.out.println("----------------------------------------------=="+LSupp);
        return LSupp;

    }
    //SelectSpecial
    @RequestMapping("/specialList")
    @ResponseBody
    public LayuiTransform<SpecialCase> specialList(@RequestParam("specialPage") Integer page
            , @RequestParam("specialSize")	 Integer size
            , @RequestParam("condition") String condition){
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        HSMSservice serv=ac.getBean("HSMSservice",HSMSservice.class);
        PageTool pt=new PageTool((page-1)*10,size);
        LayuiTransform<SpecialCase> LSpe=null;
			/*
			//System.out.println("-----------------------+++++++++++"+serv.showSupp());
			List<Supply> supp=serv.showSuppPage(pt);
			int count=serv.showSuppCount();
			//System.out.println("----------------------------------------------"+supp);
			if(supp==null) {
				List<Supply> newSupp2=new ArrayList<>();
				count=1;
				Supply nsupp=new Supply();
				newSupp2.add(nsupp);
				LSupp=new LayuiTransform<Supply>(1, "查无此项", newSupp2,count);
			}

			LSupp=new LayuiTransform<Supply>(0, "", supp,count);
			System.out.println("----------------------------------------------"+LSupp);*/
        if(condition.equals("")) {
            List<SpecialCase> spe=serv.showSpePage(pt);
            int count=serv.showSpeCount();
            LSpe=new LayuiTransform<SpecialCase>(0, "",spe,count);

            System.out.println("----------------------------------------------"+LSpe);
        }else {
            condition='%'+condition+'%';
            Condition c=new Condition(condition);
            System.out.println("-------------------------------------------c="+c);
            List<SpecialCase> spe=serv.searchSpe(c);
            System.out.println("-------------------------------------------Spe="+spe);
            //	System.out.println(emp);
            int count=0;
			/*	List<SpecialCase> newSpe=new ArrayList<>();
				newSpe.add(spe);*/
            LSpe=new LayuiTransform<SpecialCase>(0, "", spe,count);
            System.out.println("----------------------------------------------"+LSpe);
            if(spe==null) {
                List<SpecialCase> newSpe2=new ArrayList<>();
                count=1;
                SpecialCase nSpe=new SpecialCase();
                newSpe2.add(nSpe);
                LSpe=new LayuiTransform<SpecialCase>(1, "查无此项", newSpe2,count);
            }
        }
        System.out.println("----------------------------------------------=="+LSpe);
        return LSpe;

    }
    //SelectPraise
    @RequestMapping("/empsPraise")
    @ResponseBody
    public LayuiTransform<Praise> empsPraise(@RequestParam("praisePage") Integer page, @RequestParam("praiseSize") Integer size	, @RequestParam("num") Integer num) {
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        HSMSservice serv=ac.getBean("HSMSservice",HSMSservice.class);
        PageTool pt=new PageTool((page-1)*10,size,num);
        List<Praise> pra=serv.showPraPage(pt);
        int count=serv.showPraCount(pt);
        //System.out.println("count="+count+"pra"+pra);
        LayuiTransform<Praise> Lpra=new LayuiTransform<Praise>(0, "", pra,count);

        //System.out.println("Ppage------size------"+page+"---"+size);
        //System.out.println("num"+num);
        //System.out.println("num"+Lpra);
        return Lpra;

    }
    //SelectSuggestion
    @RequestMapping("/empsSuggestion")
    @ResponseBody
    public LayuiTransform<Suggestion> empsSuggestion(@RequestParam("suggestionPage") Integer page, @RequestParam("suggestionSize") Integer size	, @RequestParam("num") Integer num) {
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        HSMSservice serv=ac.getBean("HSMSservice",HSMSservice.class);
        PageTool pt=new PageTool((page-1)*10,size,num);
        List<Suggestion> sug=serv.showSugPage(pt);
        int count=serv.showSugCount(pt);
        //System.out.println("count="+count+"sug"+sug);
        LayuiTransform<Suggestion> Lsug=new LayuiTransform<Suggestion>(0, "", sug,count);

        //System.out.println("Ppage------size------"+page+"---"+size);
        //System.out.println("num"+Lsug);
        return Lsug;

    }
    //SelectComplaint
    @RequestMapping("/empsComplaint")
    @ResponseBody
    public LayuiTransform<Complaint> empsComplaint(@RequestParam("complaintPage") Integer page, @RequestParam("complaintSize") Integer size	, @RequestParam("num") Integer num) {
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        HSMSservice serv=ac.getBean("HSMSservice",HSMSservice.class);
        PageTool pt=new PageTool((page-1)*10,size,num);
        List<Complaint> comp=serv.showCompPage(pt);
        int count=serv.showCompCount(pt);
        //System.out.println("count="+count+"comp"+comp);
        LayuiTransform<Complaint> Lcomp=new LayuiTransform<Complaint>(0, "", comp,count);

        //System.out.println("Ppage------size------"+page+"---"+size);
        //System.out.println("num"+Lcomp);
        return Lcomp;

    }
    //GiveSuggestion
    @RequestMapping("/giveSuggestion")
    @ResponseBody
    public void giveSuggestion( @RequestParam("num") Integer num,@RequestParam("txt") String txt) throws UnsupportedEncodingException  {
        //URLDecoder.decode(txt, "UTF-8");
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        HSMSservice serv=ac.getBean("HSMSservice",HSMSservice.class);
        SimpleDateFormat dateForm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // System.out.println("================="+txt);
        String snum=String.valueOf(num);
        Date nowDate = new Date();
        String dateString=dateForm.format(nowDate);
        Suggestion sug=new Suggestion(txt,dateString,snum);
        serv.giveSuggestion(sug);
    }
    //GivePraise
    @RequestMapping("/givePraise")
    @ResponseBody
    public void givePraise( @RequestParam("num") Integer num,@RequestParam("txt") String txt) throws UnsupportedEncodingException  {
        //URLDecoder.decode(txt, "UTF-8");
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        HSMSservice serv=ac.getBean("HSMSservice",HSMSservice.class);
        SimpleDateFormat dateForm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // System.out.println("================="+txt);
        String snum=String.valueOf(num);
        Date nowDate = new Date();
        String dateString=dateForm.format(nowDate);

        Praise pra=new Praise(txt,dateString,snum);
        serv.givePraise(pra);
    }
    //GiveComplaint
    @RequestMapping("/giveComplaint")
    @ResponseBody
    public void giveComplaint( @RequestParam("num") Integer num,@RequestParam("txt") String txt) throws UnsupportedEncodingException  {
        //URLDecoder.decode(txt, "UTF-8");
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        HSMSservice serv=ac.getBean("HSMSservice",HSMSservice.class);
        SimpleDateFormat dateForm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // System.out.println("================="+txt);
        String snum=String.valueOf(num);
        Date nowDate = new Date();
        String dateString=dateForm.format(nowDate);

        Complaint comp=new Complaint(txt,dateString,snum);
        serv.giveComplaint(comp);
    }

    //AddEmployee
    @RequestMapping("/addEmployee")
    @ResponseBody
    public int addEmployee(@RequestParam("empId") String empId
            ,@RequestParam("empName") String empName
            ,@RequestParam("empGender") String empGender
            ,@RequestParam("empAge") String empAge
            ,@RequestParam("empPosition") String empPosition
    ) throws IOException  {
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        HSMSservice serv=ac.getBean("HSMSservice",HSMSservice.class);
        List<Integer> empsId=serv.selectId();
        int iempId=Integer.parseInt(empId);
        int iempAge=Integer.parseInt(empAge);
        int ayon=1;
        Employee empOfAdd=new Employee(iempId,iempAge,empName,empGender,empPosition);
				/*	System.out.println("empId"+empId);
					System.out.println("empName"+empName);
					System.out.println("empGender"+empGender);
					System.out.println("empAge"+empAge);
					System.out.println("empPosition"+empPosition);

					System.out.println("empsId"+empsId);
					*/
        for(int i=0;i<empsId.size();i++) {
            Integer eid=(Integer)empsId.get(i);

            if(iempId==eid) {
                ayon=0;
                break;
            }else {
            }
        }

        if(ayon==1) {
            serv.addEmployee(empOfAdd);
        }else {

        }

        return ayon;
    }
    //DeleteEmployee
    @RequestMapping("/delEmployee")
    @ResponseBody
    public int delEmployee( @RequestParam("ids") List<Integer> ids) throws UnsupportedEncodingException  {
        //URLDecoder.decode(txt, "UTF-8");
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        HSMSservice serv=ac.getBean("HSMSservice",HSMSservice.class);
        int dyon=-1; //d:delete y:yes o:or n:no
        if(ids.size()>0) {
            System.out.println(ids);
            serv.delEmployees(ids);
            dyon=1;
        }else if(ids.size()<1) {
            dyon=0;
        }
        return dyon;
    }
    //DeletePraise
    @RequestMapping("/delPraise")
    @ResponseBody
    public int delPraise( @RequestParam("praise_inf") String praise_inf,@RequestParam("praise_date") String praise_date) throws UnsupportedEncodingException  {
        //URLDecoder.decode(txt, "UTF-8");
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        HSMSservice serv=ac.getBean("HSMSservice",HSMSservice.class);
        int pyon=-1;
        Praise pra=new Praise(praise_inf,praise_date);
        int count=serv.ifDelPraise(pra);
        if(count>0) {
            System.out.println(pra);
            serv.delPraise(pra);
            pyon=1;
        }else{
            pyon=0;
        }
        return pyon;
    }
    //DeleteSuggestion
    @RequestMapping("/delSuggestion")
    @ResponseBody
    public int delSuggestion( @RequestParam("suggestion_inf") String suggestion_inf,@RequestParam("suggestion_date") String suggestion_date) throws UnsupportedEncodingException  {
        //URLDecoder.decode(txt, "UTF-8");
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        HSMSservice serv=ac.getBean("HSMSservice",HSMSservice.class);
        int syon=-1;
        Suggestion sug=new Suggestion(suggestion_inf,suggestion_date);
        int count=serv.ifDelSuggestion(sug);
        if(count>0) {
            System.out.println(sug);
            serv.delSuggestion(sug);
            syon=1;
        }else{
            syon=0;
        }
        return syon;
    }
    //DeletePraise
    @RequestMapping("/delComplaint")
    @ResponseBody
    public int delComplaint( @RequestParam("complaint_inf") String complaint_inf,@RequestParam("complaint_date") String complaint_date) throws UnsupportedEncodingException  {
        //URLDecoder.decode(txt, "UTF-8");
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        HSMSservice serv=ac.getBean("HSMSservice",HSMSservice.class);
        int cyon=-1;
        Complaint comp=new Complaint(complaint_inf,complaint_date);
        int count=serv.ifDelComplaint(comp);
        if(count>0) {
            System.out.println(comp);
            serv.delComplaint(comp);
            cyon=1;
        }else{
            cyon=0;
        }
        return cyon;
    }
    //UpdateEmployee
    @RequestMapping("/updateEmployee")
    @ResponseBody
    public void updateEmployee(
            @RequestParam("empId") String empId
            ,@RequestParam("empName") String empName
            ,@RequestParam("empGender") String empGender
            ,@RequestParam("empAge") String empAge
            ,@RequestParam("empPosition") String empPosition
    ) throws UnsupportedEncodingException  {
        //URLDecoder.decode(txt, "UTF-8");
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        HSMSservice serv=ac.getBean("HSMSservice",HSMSservice.class);
        int iempId=Integer.parseInt(empId);
        int iempAge=Integer.parseInt(empAge);

        Employee emp=new Employee(iempId,iempAge,empName,empGender,empPosition);
        serv.updateEmployee(emp);
    }

    //AddSupply
    @RequestMapping("/addSupply")
    @ResponseBody
    public int addSupply(@RequestParam("suppId") String suppId
            ,@RequestParam("suppName") String suppName
            ,@RequestParam("suppInventory") String suppInventory
            ,@RequestParam("suppNote") String suppNote
    ) throws IOException  {
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        HSMSservice serv=ac.getBean("HSMSservice",HSMSservice.class);
        List<Integer> suppsId=serv.selectSId();
        int isuppId=Integer.parseInt(suppId);
        int ayon=1;
        Supply suppOfAdd=new Supply(isuppId,suppName,suppInventory,suppNote);

        for(int i=0;i<suppsId.size();i++) {
            Integer eid=(Integer)suppsId.get(i);

            if(isuppId==eid) {
                ayon=0;
                break;
            }else {
            }
        }

        if(ayon==1) {
            serv.addSupply(suppOfAdd);
        }else {

        }

        return ayon;
    }
    //UpdateSupply
    @RequestMapping("/updateSupply")
    @ResponseBody
    public void updateSupply(
            @RequestParam("suppId") String suppId
            ,@RequestParam("suppName") String suppName
            ,@RequestParam("suppInventory") String suppInventory
            ,@RequestParam("suppNote") String suppNote
    ) throws UnsupportedEncodingException  {
        //URLDecoder.decode(txt, "UTF-8");
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        HSMSservice serv=ac.getBean("HSMSservice",HSMSservice.class);
        int isuppId=Integer.parseInt(suppId);


        Supply supp=new Supply(isuppId,suppName,suppInventory,suppNote);
        serv.updateSupply(supp);
    }
    //DeleteSupply
    @RequestMapping("/delSupply")
    @ResponseBody
    public int delSupply( @RequestParam("ids") List<Integer> ids) throws UnsupportedEncodingException  {
        //URLDecoder.decode(txt, "UTF-8");
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        HSMSservice serv=ac.getBean("HSMSservice",HSMSservice.class);
        int dyon=-1;
        if(ids.size()>0) {
            System.out.println(ids);
            serv.delSupplies(ids);
            dyon=1;
        }else if(ids.size()<1) {
            dyon=0;
        }
        return dyon;
    }
    /*
//SearchEmp
    @RequestMapping("/searchEmp")
    @ResponseBody
    public LayuiTransform<Employee> searchEmp(@RequestParam("condition") Condition condition){
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        HSMSservice serv=ac.getBean("HSMSservice",HSMSservice.class);
        List<Employee>searchEmp = serv.searchEmp(condition);
        int count=serv.showSEmpCount(condition);
        LayuiTransform<Employee> Lemps=new LayuiTransform<Employee>(0, "", searchEmp,count);
        return Lemps;

    }
    */
//AddSpecial
    @RequestMapping("/addSpecial")
    @ResponseBody
    public int addSpecial(@RequestParam("caseId") String caseId
            ,@RequestParam("caseInf") String caseInf
            ,@RequestParam("caseDate") String caseDate
    ) throws IOException  {
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        HSMSservice serv=ac.getBean("HSMSservice",HSMSservice.class);
        List<Integer> spesId=serv.selectSCId();
        int icaseId=Integer.parseInt(caseId);
        int ayon=1;
        SpecialCase speOfAdd=new SpecialCase(icaseId,caseInf,caseDate);
        System.out.println(caseId);
        for(int i=0;i<spesId.size();i++) {
            Integer eid=(Integer)spesId.get(i);

            if(icaseId==eid) {
                ayon=0;
                break;
            }else {
            }
        }

        if(ayon==1) {
            serv.addSpecial(speOfAdd);
        }else {

        }

        return ayon;
    }
    //UpdateSpecial
    @RequestMapping("/updateSpecial")
    @ResponseBody
    public void updateSpecial(
            @RequestParam("caseId") String caseId
            ,@RequestParam("caseInf") String caseInf
            ,@RequestParam("caseDate") String caseDate
    ) throws UnsupportedEncodingException  {
        //URLDecoder.decode(txt, "UTF-8");
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        HSMSservice serv=ac.getBean("HSMSservice",HSMSservice.class);
        int icaseId=Integer.parseInt(caseId);


        SpecialCase spe=new SpecialCase(icaseId,caseInf,caseDate);
        serv.updateSpecial(spe);
    }
    //DeleteSpecial
    @RequestMapping("/delSpecial")
    @ResponseBody
    public int delSpecial( @RequestParam("ids") List<Integer> ids) throws UnsupportedEncodingException  {
        //URLDecoder.decode(txt, "UTF-8");
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        HSMSservice serv=ac.getBean("HSMSservice",HSMSservice.class);
        int dyon=-1;
        if(ids.size()>0) {
            System.out.println(ids);
            serv.delSpecials(ids);
            dyon=1;
        }else if(ids.size()<1) {
            dyon=0;
        }
        return dyon;
    }
}

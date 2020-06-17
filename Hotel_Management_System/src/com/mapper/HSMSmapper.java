package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.pojo.Complaint;
import com.pojo.Condition;
import com.pojo.Employee;
import com.pojo.PageTool;
import com.pojo.Praise;
import com.pojo.SpecialCase;
import com.pojo.Suggestion;
import com.pojo.Supply;

public interface HSMSmapper {
    //SelectEmps
    @Select("select * from hsms_employee_inf")
    public List<Employee> selectAllEmp();



    @Select("select count(*)from hsms_employee_inf ")
    public int selectEmpCount();

    @Select("select * from hsms_employee_inf limit #{currData},#{pageSize};")
    public List<Employee> selectEmpPage(PageTool pt);

    //SearchEmp
    @Select("SELECT * FROM hsms_employee_inf WHERE employee_id=#{condition}")
    public Employee searchEmp(Condition cond);

    @Select("select count(*)from hsms_employee_inf WHERE employee_id=#{condition}")
    public int selectSEmpCount(Condition cond);
    //SelectPraise

    @Select("select praise_inf,praise_date from hsms_praise_list where praise_employee=#{num} limit #{currData},#{pageSize};")
    public List<Praise> selectPraPage(PageTool pt);

    @Select("select count(*)from hsms_praise_list where praise_employee=#{num}")
    public int selectPraCount(PageTool pt);

    //SelectSuggestion

    @Select("select suggestion_inf,suggestion_date from hsms_suggestion_list where suggestion_employee=#{num} limit #{currData},#{pageSize};")
    public List<Suggestion> selectSugPage(PageTool pt);

    @Select("select count(*)from hsms_suggestion_list where suggestion_employee=#{num}")
    public int selectSugCount(PageTool pt);

    //SelectComplaint

    @Select("select complaint_inf,complaint_date from hsms_complaint_list where complaint_employee=#{num} limit #{currData},#{pageSize};")
    public List<Complaint> selectCompPage(PageTool pt);

    @Select("select count(*)from hsms_complaint_list where complaint_employee=#{num}")
    public int selectCompCount(PageTool pt);

    //GiveSuggest
    @Insert("insert into hsms_suggestion_list(suggestion_employee,suggestion_inf,suggestion_date)"
            + "values(#{suggestion_employee},#{suggestion_inf},#{suggestion_date})")
    public void giveSuggestion(Suggestion sug);

    //GivePraise
    @Insert("insert into hsms_praise_list(praise_employee,praise_inf,praise_date)"
            + "values(#{praise_employee},#{praise_inf},#{praise_date})")
    public void givePriase(Praise pra);
    //GiveComplaint
    @Insert("insert into hsms_complaint_list(complaint_employee,complaint_inf,complaint_date)"
            + "values(#{complaint_employee},#{complaint_inf},#{complaint_date})")
    public void giveComplaint(Complaint comp);
    //AddEmployee
    @Select("SELECT employee_id from hsms_employee_inf")
    public List<Integer> selectId();

    @Insert("insert into hsms_employee_inf(employee_id,employee_name,employee_gender,employee_age,employee_position)"
            + "values(#{employee_id},#{employee_name},#{employee_gender},#{employee_age},#{employee_position})")
    public void addEmployee(Employee emp);
    //DeleteEmployee
    @Delete("Delete from hsms_employee_inf where employee_id=#{employee_id}")
    public void delEmployee(Employee emp);

    @Delete({
            "<script>"
                    + "DELETE FROM hsms_employee_inf where employee_id in"
                    +      "<foreach item='id' collection='ids' open='(' separator=',' close=')'>"
                    +           "#{id} "
                    +      "</foreach>"
                    + "</script>"
    })
    public void delEmployees(@Param("ids") List<Integer> ids);

    //DeletePraise
    @Select("SELECT COUNT(*) FROM hsms_praise_list WHERE praise_inf=#{praise_inf} AND praise_date = #{praise_date}")
    public int ifDelPraise(Praise pra) ;

    @Delete("DELETE FROM hsms_praise_list where praise_inf=#{praise_inf} AND praise_date = #{praise_date}")
    public void delPraise(Praise pra);

    //DeleteSuggestion
    @Select("SELECT COUNT(*) FROM hsms_suggestion_list WHERE suggestion_inf=#{suggestion_inf} AND suggestion_date = #{suggestion_date}")
    public int ifDelSuggestion(Suggestion sug) ;

    @Delete("DELETE FROM hsms_suggestion_list where suggestion_inf=#{suggestion_inf} AND suggestion_date = #{suggestion_date}")
    public void delSuggestion(Suggestion sug);

    //DeleteComplaint
    @Select("SELECT COUNT(*) FROM hsms_complaint_list WHERE complaint_inf=#{complaint_inf} AND complaint_date = #{complaint_date}")
    public int ifDelComplaint(Complaint comp) ;

    @Delete("DELETE FROM hsms_complaint_list where complaint_inf=#{complaint_inf} AND complaint_date = #{complaint_date}")
    public void delComplaint(Complaint comp);

    //UpdateEmployee
    @Update("UPDATE hsms_employee_inf SET employee_name=#{employee_name},employee_age=#{employee_age},employee_gender=#{employee_gender},employee_position=#{employee_position}"
            + "WHERE employee_id=#{employee_id}")
    public void updateEmployee(Employee emp);
    //SelectSupply
    @Select("select * from hsms_supply_inventory")
    public List<Supply> selectAllSupp();


    @Select("select count(*)from hsms_supply_inventory ")
    public int selectSuppCount();

    @Select("select * from hsms_supply_inventory limit #{currData},#{pageSize};")
    public List<Supply> selectSuppPage(PageTool pt);
    //AddSupply
    @Select("SELECT supply_id from hsms_supply_inventory")
    public List<Integer> selectSId();

    @Insert("insert into hsms_supply_inventory(supply_id,supply_name,supply_inventory,supply_note)"
            + "values(#{supply_id},#{supply_name},#{supply_inventory},#{supply_note})")
    public void addSupply(Supply supp);

    //UpdateSupply
    @Update("UPDATE hsms_supply_inventory SET supply_name=#{supply_name},supply_inventory=#{supply_inventory},supply_note=#{supply_note}"
            + "WHERE supply_id=#{supply_id}")
    public void updateSupply(Supply supp);

    //DeleteSupply
    @Delete("Delete from hsms_supply_inventory where supply_id=#{supply_id}")
    public void delSupply(Supply supp);

    @Delete({
            "<script>"
                    + "DELETE FROM hsms_supply_inventory where supply_id in"
                    +      "<foreach item='id' collection='ids' open='(' separator=',' close=')'>"
                    +           "#{id} "
                    +      "</foreach>"
                    + "</script>"
    })
    public void delSupplies(@Param("ids") List<Integer> ids);



    //SearchSupply             在mapper文件中使用#占位符传参时，一定不要在两侧添加引号，否则会导致参数解析异常。
    ///	@Select("SELECT * FROM hsms_supply_inventory WHERE supply_name LIKE '%${condition}%'")
    @Select("SELECT * FROM hsms_supply_inventory WHERE supply_name LIKE #{condition}")
    public List<Supply> searchSupp(Condition cond);

    //	@Select("select count(*) from hsms_supply_inventory WHERE supply_name LIKE '%${condition}%'")
    @Select("select count(*) from hsms_supply_inventory WHERE supply_name LIKE #{condition}")
    public int selectSSuppCount(Condition cond);

    //SelectSpecial
    @Select("select * from hsms_special_case")
    public List<SpecialCase> selectAllSpe();


    @Select("select count(*)from hsms_special_case ")
    public int selectSpeCount();

    @Select("select * from hsms_special_case limit #{currData},#{pageSize};")
    public List<SpecialCase> selectSpePage(PageTool pt);
    //AddSpecial
    @Select("SELECT case_id from hsms_special_case")
    public List<Integer> selectSCId();

    @Insert("insert into hsms_special_case(case_id,case_inf,case_date)"
            + "values(#{case_id},#{case_inf},#{case_date})")
    public void addSpecial(SpecialCase supp);

    //UpdateSpecial
    @Update("UPDATE hsms_special_case SET case_inf=#{case_inf},case_date=#{case_date}"
            + "WHERE case_id=#{case_id}")
    public void updateSpecial(SpecialCase spe);

    //DeleteSpecial
    @Delete("Delete from hsms_special_case where case_id=#{case_id}")
    public void delSpecial(SpecialCase spe);

    @Delete({
            "<script>"
                    + "DELETE FROM hsms_special_case where case_id in"
                    +      "<foreach item='id' collection='ids' open='(' separator=',' close=')'>"
                    +           "#{id} "
                    +      "</foreach>"
                    + "</script>"
    })
    public void delSpecials(@Param("ids") List<Integer> ids);



    //SearchSpecial             在mapper文件中使用#占位符传参时，一定不要在两侧添加引号，否则会导致参数解析异常。
    ///	@Select("SELECT * FROM hsms_supply_inventory WHERE supply_name LIKE '%${condition}%'")
    @Select("SELECT * FROM hsms_special_case WHERE case_inf LIKE #{condition}")
    public List<SpecialCase> searchSpe(Condition cond);

    //	@Select("select count(*) from hsms_supply_inventory WHERE supply_name LIKE '%${condition}%'")
    @Select("select count(*) from hsms_special_case WHERE case_inf LIKE #{condition}")
    public int selectSSpeCount(Condition cond);
}

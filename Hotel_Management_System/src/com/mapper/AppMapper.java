package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.pojo.Appraise;
import com.pojo.PageTool;
import com.pojo.Condition;

public interface AppMapper {
    //查询所有
    //@Select("SELECT a.`order_id`,a.`order_state`,b.`in_date`,b.`out_date`,c.`number`,c.`cus_name`,c.`telephone`,d.`room_type`,d.`price`FROM hotel_order a,room_info b,customer c,room_type dWHERE a.`room_id` = b.`room_id` AND a.`customer_id` = c.`number` AND a.`room_type` = d.`room_type`")
    @Select("select * from appraise")
    public List<Appraise> selectAll();
    @Select("select count(*)from appraise")
    public int selectAppCount();
    @Select("select * from appraise limit #{currData},#{pageSize};")
    public List<Appraise> selectAppPage(PageTool pt);
    //AddEmployee
    @Select("SELECT appraise_id from appraise")
    public List<Integer> selectId();

    @Insert("insert into appraise(customer_id,appraise_star,room_type,customer_name,appraise_content,appraise_date,appraise_reply,reply_date)"
            + "values(#{customer_id},#{appraise_star},#{room_type},#{customer_name},#{appraise_content},#{appraise_date},#{appraise_reply},#{reply_date})")
    public void addAppraise(Appraise app);
    //DeleteEmployee
    @Delete("Delete from appraise where appraise_id=#{appraise_id}")
    public void delAppraise(Appraise app);

    @Delete({
            "<script>"
                    + "DELETE FROM appraise where appraise_id in"
                    +      "<foreach item='id' collection='appId' open='(' separator=',' close=')'>"
                    +           "#{id} "
                    +      "</foreach>"
                    + "</script>"
    })
    public void delAppraises(@Param("appId") List<Integer> appId);
    //UpdateEmployee
    @Update("UPDATE appraise SET customer_id=#{customer_id},appraise_star=#{appraise_star},room_type=#{room_type},customer_name=#{customer_name},appraise_content=#{appraise_content},appraise_date=#{appraise_date},appraise_reply=#{appraise_reply},reply_date=#{reply_date}"
            + "WHERE appraise_id=#{appraise_id}")
    public void updateAppraise(Appraise app);


    //按房间类型查询
    ///	@Select("SELECT * FROM hsms_supply_inventory WHERE supply_name LIKE '%${condition}%'")
    @Select("SELECT * FROM appraise WHERE room_type=#{condition} limit 0,10")
    public List<Appraise> searchAppByCon(@Param("condition") String condition,@Param("pt") PageTool pt);

    //	@Select("select count(*) from hsms_supply_inventory WHERE supply_name LIKE '%${condition}%'")
    @Select("select count(*) from appraise WHERE room_type=#{condition}")
    public int selectAppCountByCon(@Param("condition") String condition);





}
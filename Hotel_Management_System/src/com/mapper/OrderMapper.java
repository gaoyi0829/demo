package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.pojo.Condition;
import com.pojo.Customer;
import com.pojo.Order1;
import com.pojo.PageTool;
public interface OrderMapper {
    //selectAll查询所有
    //@Select("SELECT a.`order_id`,a.`order_state`,b.`in_date`,b.`out_date`,c.`number`,c.`cus_name`,c.`telephone`,d.`room_type`,d.`price`FROM hotel_order a,room_info b,customer c,room_type dWHERE a.`room_id` = b.`room_id` AND a.`customer_id` = c.`number` AND a.`room_type` = d.`room_type`")
    @Select("select * from hotel_order")
    public List<Order1> selectAll();
    @Select("select count(*)from hotel_order")
    public int selectOrderCount();
    @Select("select * from hotel_order limit #{currData},#{pageSize};")
    public List<Order1> selectOrderPage(PageTool pt);

    //通过customer_id查询
    @Select("select * from hotel_order where customer_id=#{condition}")
    public List<Order1> searchOrd (Condition cond);
    @Select("select count(*)from hotel_order where customer_id=#{condition}")
    public int selectSOrdCount(Condition cond);

    //通过order_state查询
    @Select("select * from hotel_order where order_	state=#{condition}")
    public List<Order1> searchOrd2 (Condition cond);
    @Select("select count(*)from hotel_order where order_state=#{condition}")
    public int selectSOrdCount2(Condition cond);

    ////通过customer_id查询
    @Select("select * from hotel_order where customer_id=#{id} limit #{page},#{size}")
    public  List<Order1> searchAllOrd3 (@Param("page")Integer page,@Param("size")Integer size,@Param("id")String id);


    @Select("select * from hotel_order where order_state=#{condition} and customer_id=#{id} limit #{page},#{size}")
    public List<Order1> searchOrd3 (@Param("page")Integer page,@Param("size")Integer size,@Param("id")String id,@Param("condition") String condition);


    @Select("select count(*)from hotel_order where customer_id=#{id}")
    public int selectAllOrdCount3(@Param("id") String id);



    @Select("select count(*)from hotel_order where customer_id=#{id} and order_state=#{condition}")
    public int selectOrdCount3(@Param("id") String id,@Param("condition") String cond);



    //AddOrder
    @Select("select order_id from hotel_order")
    public List<Integer> selectId();

    @Insert("insert into hotel_order(order_state,room_id,in_date,out_date,customer_id,customer_name,customer_phone,room_type,price)"+
            "values(#{order_state},#{room_id},#{in_date},#{out_date},#{customer_id},#{customer_name},#{customer_phone},#{room_type},#{price})")
    public void addOrder(Order1 order1);

    //DeletOrder
    @Delete("delete from hotel_order where order_id=#{order_id}")
    public void delOrder(Order1 order1);


    @Delete({
            "<script>"
                    +"delete from hotel_order where order_id in"
                    +"<foreach item='id' collection='orderIds' open='(' separator=',' close=')'>"
                    +        "#{id}"
                    +  "</foreach>"
                    +"</script>"
//    	"delete from hotel_order where order_id in (4,5)"
    })
    public void delOrders(@Param("orderIds") List<Integer> orderIds);

    //UpdateOrder
    @Update("update hotel_order set order_state=#{order_state},room_id=#{room_id},in_date=#{in_date},out_date=#{out_date},customer_id=#{customer_id},customer_name=#{customer_name},customer_phone=#{customer_phone},room_type=#{room_type},price=#{price} where order_id=#{order_id}")
    public void updateOrder(Order1 order1);


    //select customer
    @Select("select * from customer where number= #{id} ")
    public  Customer searchCus (String id);




}
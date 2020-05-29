package com.ssm.dao;

import com.ssm.entity.Appointment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentDao {
    @Insert("insert ignore INTO appointment(book_id,student_id) values(#{bookId},#{studentId})")
    int insertAppointment(@Param("bookId") long bookId,@Param("studentId") long studentId);
    @Select("SELECT a.book_id,a.student_id,_a.appoint_time,b.book_id 'book.book_id',b.'name' 'book.name',b.number 'book.number' from appointment a INNER JOIN book b ON a.book_id=bb.book_id where a.book_id=#{bookId} and a.student_id=#{studentId}")
    Appointment queryByKeyWithBook(@Param("bookId") long bookId,@Param("studentId") long studentId);
}

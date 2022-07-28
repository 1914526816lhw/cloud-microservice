package com.atguigu.mapper;

import com.atguigu.entities.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


import java.util.Map;

/**
 * @author lihw
 * @className StudentMapper
 * @date 2022-05-19 18:05
 * @description
 */
@Repository
public interface StudentMapper {

    int insertStudent(Student student);

    int selectStudent(@Param("param") Map<String, Object> updateTime);
}

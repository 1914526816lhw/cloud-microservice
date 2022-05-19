import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.atguigu.entities.Student;
import com.atguigu.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * @author lihw
 * @className Test_student
 * @date 2022-05-19 18:18
 * @description
 */
@SpringBootTest
public class Test_student {

    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void test() {
        Student student = new Student();
        student.setName("lihua");
        Date date = new Date();
        DateTime dateTime = DateUtil.beginOfHour(date);
        student.setUpdateTime(dateTime);
        studentMapper.insertStudent(student);
        DateTime updateTime = DateUtil.endOfHour(date);
        studentMapper.selectStudent(updateTime);
    }
}

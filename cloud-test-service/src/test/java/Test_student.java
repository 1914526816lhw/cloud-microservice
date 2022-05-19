import cn.hutool.core.date.CalendarUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.atguigu.TestApplication;
import com.atguigu.entities.Student;
import com.atguigu.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;

/**
 * @author lihw
 * @className Test_student
 * @date 2022-05-19 18:18
 * @description
 */
@SpringBootTest(classes = TestApplication.class)
public class Test_student {

    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void test() {
        Student student = new Student();
        student.setName("lihua");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Date preDate = CalendarUtil.beginOfHour(calendar).getTime();
        student.setUpdateTime(preDate);
        studentMapper.insertStudent(student);
        Date updateTime = CalendarUtil.beginOfHour(calendar).getTime();
        studentMapper.selectStudent(updateTime).forEach(n -> System.out.println(n.toString()));
    }
}

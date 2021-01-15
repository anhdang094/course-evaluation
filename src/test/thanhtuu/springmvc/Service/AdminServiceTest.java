package thanhtuu.springmvc.Service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import thanhtuu.springmvc.Domain.Teacher;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by anh.dang on 05/27/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test-context.xml"})
public class AdminServiceTest {
    @Autowired
    private AdminServiceLocal adminService;

    @Test
    public void getAllTeacher() throws Exception {
        //AdminServiceLocal adminService = new AdminService();
        List<Teacher>  teacherList = adminService.getAllTeacher();
        Assert.assertEquals(teacherList.size(), 20);
    }

    @Test
    public void getAllStudent() throws Exception {
    }

    @Test
    public void getAllSubject() throws Exception {
    }

}
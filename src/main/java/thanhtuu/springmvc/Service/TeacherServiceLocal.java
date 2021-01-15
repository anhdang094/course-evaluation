package thanhtuu.springmvc.Service;

import thanhtuu.springmvc.Domain.Teacher;

/**
 * Created by Administrator on 9/1/2016.
 */
public interface TeacherServiceLocal {

    void insertTeacher(Teacher teacher);
    Teacher teacher(long id);
}

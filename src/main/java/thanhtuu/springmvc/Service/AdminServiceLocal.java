package thanhtuu.springmvc.Service;
import java.util.List;

import thanhtuu.springmvc.Domain.Notification;
import thanhtuu.springmvc.Domain.Student;
import thanhtuu.springmvc.Domain.Subject;
import thanhtuu.springmvc.Domain.Super_Teacher_Subject;
import thanhtuu.springmvc.Domain.Teacher;
import thanhtuu.springmvc.Domain.Teacher_Subject;

public interface AdminServiceLocal {
	public List<Teacher> getAllTeacher();
	
	public List<Notification> getAllNotification();
	
	void addNotification(Notification notification);
	
	void modifyNotification(Notification notification);
	
	void deleteNotification(long id);
	
	public List<Subject> getAllSubject();
	
	public List<Student> getAllStudent();
	
	public int createSubject(Subject subject);
	
	public int superTeacherToSubject(Super_Teacher_Subject superTeacherSubject);
	
	public int teacherToSubject(Teacher_Subject teacher_subject);
	
	public List<Subject> fetchSubjectTeachOfTeacher(long teacherId);
	
	public List<Subject> fetchSubjectManageOfTeacher(long teacherId);
	
	public List<Teacher> fetchTeacherTeachOfSubject(long subjectId);
	
	public List<Teacher> fetchTeacherManageOfSubject(long subjectId);
	
	public int unassignTeacher(Teacher_Subject teacherSubject);
	
	public int unassignSuperTeacher(Super_Teacher_Subject superTeacherSubject);
	
	public int deleteSubject(Subject subject);
}

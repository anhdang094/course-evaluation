package thanhtuu.springmvc.Service;

import thanhtuu.springmvc.Domain.Class_Member;
import thanhtuu.springmvc.Domain.Users;

public interface CheckServiceLocal {
	Users checkStudent(Users student);
	
	Class_Member checkStudentExam(long studentId, long examsId);
}

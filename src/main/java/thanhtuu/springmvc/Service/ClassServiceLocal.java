package thanhtuu.springmvc.Service;

import java.util.List;

import thanhtuu.springmvc.Domain.Class_Member;
import thanhtuu.springmvc.Domain.Classes;
import thanhtuu.springmvc.Domain.Users;
import thanhtuu.springmvc.Temporary.StudentExams;

public interface ClassServiceLocal {
	int createClass(Classes classes);
	
	int assignStudentToClass(List<Class_Member> classMemberList);
	
	List<Classes> getClassByTeacherId(long teacherId);
	
	List<Classes> getClassByStudentId(long studentId);
	
	List<Users> getStudentOfClass(int classId);
	
	Classes getClassByClassId(long classId);
	
	List<Integer> getStudentCountByClassIdList(List<Integer> classIdList);
	
	List<Integer> getExamsCountByClassIdList(List<Integer> classIdList);
	
	List<StudentExams> getAllExamsByClassIdForTeacher(long classId);
	
	List<StudentExams> getAllExamsByClassIdForStudent(long classId);
}

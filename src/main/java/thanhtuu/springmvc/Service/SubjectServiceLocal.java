package thanhtuu.springmvc.Service;

import java.util.List;

import thanhtuu.springmvc.Domain.Chapter;
import thanhtuu.springmvc.Domain.Class_Member;
import thanhtuu.springmvc.Domain.Classes;
import thanhtuu.springmvc.Domain.Classes_Exams;
import thanhtuu.springmvc.Domain.Exams;
import thanhtuu.springmvc.Domain.Student;
import thanhtuu.springmvc.Domain.Student_Exam;
import thanhtuu.springmvc.Domain.Student_Subject;
import thanhtuu.springmvc.Domain.Subject;
import thanhtuu.springmvc.Domain.Target;
import thanhtuu.springmvc.Temporary.TargetList;

public interface SubjectServiceLocal {

	int createChapter(Chapter chapter);
	
	boolean isSuperTeacherOfSubject(long teacherId, long subjectId);
	
	int assignStudentToSubject(Student_Subject studentSubject);
	
	int createTarget(Target target);
	
	int assign(Classes_Exams classExams);
	
	int fileStudentToSubject(long subjectId, String content);
	
	int removeStudentFromSubject(Student_Subject studentSubject);
	
	int removeStudentFromClass(Class_Member classMember);
	
	int removeTargetFromSubject(Target target);
	
	int removeChapterFromSubject(Chapter chapter);
	
	int closeClass(Classes classes);
	
	List<Student_Exam> fetchExamsResult(long subjectId, long examsId);
	
	List<Student_Exam> fetchExamsResultClass(long classId, long examsId);
	
	/************ Fetch function **************/
	List<Target> getAllTargetBySubjectId(long subjectId);
	
	List<Chapter> getAllChapterBySubjectId(int subjectId);
	
	List<Classes> getAllClassBySubjectId(long teacherId, int subjectId);
	
	List<Student> getAllStudentBySubjectId(int subjectId);
	
	List<Exams> getAllExamsBySubjectId(int subjectId);
	
	List<Target> getAllTargetBySubjectIdChapterId(long subjectId, long chapterId);
	
	List<TargetList> getAllTargetBySubjectIdChapterId1(long subjectId, long chapterId);
	
	Subject getSubjectOfExam123 (long examsId);
}

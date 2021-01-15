package thanhtuu.springmvc.Service;

import java.util.List;

import thanhtuu.springmvc.Domain.Exams;
import thanhtuu.springmvc.Domain.Exams_Info;
import thanhtuu.springmvc.Domain.Student_Exam;
import thanhtuu.springmvc.Temporary.CreateExams;
import thanhtuu.springmvc.Temporary.ExamsInfo;
import thanhtuu.springmvc.Temporary.Exam.ExamCount;
import thanhtuu.springmvc.Temporary.Exam.RootExamTemp;

public interface ExamsServiceLocal {
	
	long createExams(CreateExams exams);
	
	long createRootExams(CreateExams exams);
	
	List<Integer> getStudentExamsCountBySubjectId(long subjectId, List<Integer> examsIdList);
	
	List<Integer> getStudentExamsCountByClassId(long classId, List<Integer> examsIdList);
	
	long getExamsCountOfStudentByClassId(long classId, long studentId, long examsId);

	long getExamsCountOfStudentBySubjectId(long subjectId, long studentId, long examsId);

	int approve(long examsId, Long teacherId);
	
	List<Student_Exam> getResultExam(Long classId, Long studentId, Long examsId);
	
	ExamsInfo getExams(long examsId);
	
	Exams fetchRootExamByExamId(long examsId);
	
	int insertExamInfoByExam(RootExamTemp exams_info) throws InterruptedException;
	
	int UpdateRootExamCount(ExamCount exams);
	
	
}

package thanhtuu.springmvc.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import thanhtuu.springmvc.Dao.ChapterMapper;
import thanhtuu.springmvc.Dao.Class_MemberMapper;
import thanhtuu.springmvc.Dao.ClassesMapper;
import thanhtuu.springmvc.Dao.Classes_ExamsMapper;
import thanhtuu.springmvc.Dao.ExamsMapper;
import thanhtuu.springmvc.Dao.QuestionsMapper;
import thanhtuu.springmvc.Dao.Questions_TargetMapper;
import thanhtuu.springmvc.Dao.StudentMapper;
import thanhtuu.springmvc.Dao.Student_ExamMapper;
import thanhtuu.springmvc.Dao.Student_SubjectMapper;
import thanhtuu.springmvc.Dao.SubjectMapper;
import thanhtuu.springmvc.Dao.Super_Teacher_SubjectMapper;
import thanhtuu.springmvc.Dao.TargetMapper;
import thanhtuu.springmvc.Domain.Chapter;
import thanhtuu.springmvc.Domain.Class_Member;
import thanhtuu.springmvc.Domain.Classes;
import thanhtuu.springmvc.Domain.Classes_Exams;
import thanhtuu.springmvc.Domain.Exams;
import thanhtuu.springmvc.Domain.Questions;
import thanhtuu.springmvc.Domain.Questions_TargetKey;
import thanhtuu.springmvc.Domain.Student;
import thanhtuu.springmvc.Domain.Student_Exam;
import thanhtuu.springmvc.Domain.Student_Subject;
import thanhtuu.springmvc.Domain.Subject;
import thanhtuu.springmvc.Domain.Super_Teacher_Subject;
import thanhtuu.springmvc.Domain.Target;
import thanhtuu.springmvc.Temporary.TargetList;

public class SubjectService implements SubjectServiceLocal{
	@Override
	public int createChapter(Chapter chapter) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		
		ChapterMapper mapper = sqlSession.getMapper(ChapterMapper.class);
		Date now = new Date();
		chapter.setModifiedat(now);
		chapter.setCreatedat(now);
		chapter.setIsactive(true);
		int result = mapper.insertSelective(chapter);
		sqlSession.close();
		
		return result;
	}
	
	@Override
	public int createTarget(Target target) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		
		System.out.println(target.getContent());
		TargetMapper targetMapper = sqlSession.getMapper(TargetMapper.class);
		target.setIsactive(true);
		int result = targetMapper.insertSelective(target);
		
		sqlSession.close();
		return result;
	}
	
	@Override
	public boolean isSuperTeacherOfSubject(long teacherId, long subjectId) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		
		Super_Teacher_SubjectMapper mapper = sqlSession.getMapper(Super_Teacher_SubjectMapper.class);
		List<Super_Teacher_Subject> result = mapper.isSuperTeacherOfSubject(teacherId, subjectId);
		sqlSession.close();
		
		if (result.size() >= 1) return true;
		else return false;
	}
	
	@Override
	public int assignStudentToSubject(Student_Subject studentSubject) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		Student_SubjectMapper mapper = sqlSession.getMapper(Student_SubjectMapper.class);
		
		int result = 0;
		Date now = new Date();
		studentSubject.setModifiedat(now);
		studentSubject.setIsactive(true);
		
		if (mapper.selectByPrimaryKey(studentSubject) == null) {
			studentSubject.setCreatedat(now);
			result = mapper.insertSelective(studentSubject);
		} else {
			result = mapper.updateByPrimaryKeySelective(studentSubject);
		}
		
		sqlSession.close();
		return result;
	}
	
	/************ Fetch function **************/
	@Override
	public List<Target> getAllTargetBySubjectIdChapterId(long subjectId, long chapterId){
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		
		TargetMapper mapper = sqlSession.getMapper(TargetMapper.class);
		List<Target> result = mapper.getBySubjectIdChapterId(subjectId, chapterId);
		
		sqlSession.close();
		return result;
	}
	
	public List<TargetList> getAllTargetBySubjectIdChapterId1(long subjectId, long chapterId){
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		TargetMapper mapper = sqlSession.getMapper(TargetMapper.class);
		QuestionsMapper questionMapper = sqlSession.getMapper(QuestionsMapper.class);
		Questions_TargetMapper questionTargetMapper = sqlSession.getMapper(Questions_TargetMapper.class);
		
		List<Target> targetList = mapper.getBySubjectIdChapterId(subjectId, chapterId);
		List<TargetList> result = new ArrayList<TargetList>();
		
		System.out.println(targetList.size());
		
		for (int i = 0; i < targetList.size(); i++) {
			TargetList target = new TargetList();
			target.target = targetList.get(i);
			target.questionL1Count = 0;
			target.questionL2Count = 0;
			target.questionL3Count = 0;
			target.questionL4Count = 0;
			target.questionL5Count = 0;
			
			List<Questions_TargetKey> questionTargetList = questionTargetMapper.getBySubjectIdChapterIdTargetId(targetList.get(i).getSubjectid(), targetList.get(i).getChapterid(), targetList.get(i).getId());
			List<Questions> questionL1List = questionMapper.getL1BySubjectIdChapterId(targetList.get(i).getSubjectid(), targetList.get(i).getChapterid());
			List<Questions> questionL2List = questionMapper.getL2BySubjectIdChapterId(targetList.get(i).getSubjectid(), targetList.get(i).getChapterid());
			List<Questions> questionL3List = questionMapper.getL3BySubjectIdChapterId(targetList.get(i).getSubjectid(), targetList.get(i).getChapterid());
			List<Questions> questionL4List = questionMapper.getL4BySubjectIdChapterId(targetList.get(i).getSubjectid(), targetList.get(i).getChapterid());
			List<Questions> questionL5List = questionMapper.getL5BySubjectIdChapterId(targetList.get(i).getSubjectid(), targetList.get(i).getChapterid());
		
			for (int j = 0; j < questionL1List.size(); j++) {
				for (int z = 0; z < questionTargetList.size(); z++) {
					if(questionL1List.get(j).getId() == questionTargetList.get(z).getQuestionid()) {
						target.questionL1Count += 1;
					}
				}
			}
			
			for (int j = 0; j < questionL2List.size(); j++) {
				for (int z = 0; z < questionTargetList.size(); z++) {
					if(questionL2List.get(j).getId() == questionTargetList.get(z).getQuestionid()) {
						target.questionL2Count += 1;
					}
				}
			}
			
			for (int j = 0; j < questionL3List.size(); j++) {
				for (int z = 0; z < questionTargetList.size(); z++) {
					if(questionL3List.get(j).getId() == questionTargetList.get(z).getQuestionid()) {
						target.questionL3Count += 1;
					}
				}
			}
			
			for (int j = 0; j < questionL4List.size(); j++) {
				for (int z = 0; z < questionTargetList.size(); z++) {
					if(questionL4List.get(j).getId() == questionTargetList.get(z).getQuestionid()) {
						target.questionL4Count += 1;
					}
				}
			}
			
			for (int j = 0; j < questionL5List.size(); j++) {
				for (int z = 0; z < questionTargetList.size(); z++) {
					if(questionL5List.get(j).getId() == questionTargetList.get(z).getQuestionid()) {
						target.questionL5Count += 1;
					}
				}
			}
			
			result.add(target);
		}
		
		sqlSession.close();
		return result;
	}
	
	@Override
	public List<Chapter> getAllChapterBySubjectId(int subjectId) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		
		ChapterMapper mapper = sqlSession.getMapper(ChapterMapper.class);
		List<Chapter> result = mapper.getAllChapterBySubjectId(subjectId);
		
		sqlSession.close();
		return result;
	}
	
	@Override
	public List<Target> getAllTargetBySubjectId(long subjectId) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		
		TargetMapper mapper = sqlSession.getMapper(TargetMapper.class);
		List<Target> result = mapper.getBySubjectId(subjectId);
		
		sqlSession.close();
		return result;
	}
	
	@Override
	public List<Classes> getAllClassBySubjectId(long teacherId, int subjectId) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		
		ClassesMapper classMapper = sqlSession.getMapper(ClassesMapper.class);
		List<Classes> result = classMapper.getBySubjectId(teacherId, subjectId);
		
		sqlSession.close();	
		return result;
	}
	
	@Override
	public List<Student> getAllStudentBySubjectId(int subjectId) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		
		Student_SubjectMapper studentSubjectMapper = sqlSession.getMapper(Student_SubjectMapper.class);
		List<Student_Subject> studentSubjectList = studentSubjectMapper.getBySubjectId(subjectId);
		
		List<Integer> studentIdList = new ArrayList<Integer>();
		for(int i = 0; i < studentSubjectList.size(); i++) {
			studentIdList.add(studentSubjectList.get(i).getStudentid());
		}
		
		List<Student> result = new ArrayList<Student>();
		if(studentIdList.size() > 0) {
			StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
			result = studentMapper.getByIdInList(studentIdList);
		}

		sqlSession.close();
		return result;
	}
	
	@Override
	public List<Exams> getAllExamsBySubjectId(int subjectId) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		
		ExamsMapper examsMapper = sqlSession.getMapper(ExamsMapper.class);
		List<Exams> result = examsMapper.getBySubjectId(subjectId);
		
		sqlSession.close();
		return result;
	}
	
	@Override
	public int assign(Classes_Exams classExams) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		
		Date now = new Date();
		Classes_ExamsMapper classExamsMapper = sqlSession.getMapper(Classes_ExamsMapper.class);
		classExams.setModifiedat(now);
		classExams.setCreatedat(now);
		int result = classExamsMapper.insertSelective(classExams);
		
		sqlSession.close();
		return result;
	}
	
	@Override
	public int fileStudentToSubject(long subjectId, String content) {
		BufferedReader bufReader = new BufferedReader(new StringReader(content));
		String line = null;
		try {
			while ((line = bufReader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 1;
	}
	
	@Override
	public int removeStudentFromSubject(Student_Subject studentSubject){
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		Student_SubjectMapper mapper = sqlSession.getMapper(Student_SubjectMapper.class);
		Class_MemberMapper classMembetMapper = sqlSession.getMapper(Class_MemberMapper.class);
		
		Class_Member classMember = new Class_Member();
		classMember.setSubjectid(studentSubject.getSubjectid().intValue());
		classMember.setStudentid(studentSubject.getStudentid().longValue());
		classMembetMapper.removeBySubjectId(classMember);
		
		studentSubject.setIsactive(false);
		int result = mapper.updateByPrimaryKeySelective(studentSubject);
		sqlSession.close();
		return result;
	}
	
	@Override
	public int removeStudentFromClass(Class_Member classMember){
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		Class_MemberMapper mapper = sqlSession.getMapper(Class_MemberMapper.class);
		classMember.setIsactive(false);
		int result = mapper.removeByClassId(classMember);
		sqlSession.close();
		return result;
	}
	
	@Override
	public int removeTargetFromSubject(Target target) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		TargetMapper mapper = sqlSession.getMapper(TargetMapper.class);
		target = mapper.getIdTarget(target.getId().intValue());
		target.setIsactive(false);
		int result = mapper.updateByPrimaryKeySelective(target);
		System.out.println("Check Target:" + target.getContent() + target.getIsactive());
		sqlSession.close();
		return result;
	}
	
	@Override
	public int removeChapterFromSubject(Chapter chapter) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		ChapterMapper mapper = sqlSession.getMapper(ChapterMapper.class);
		chapter.setIsactive(false);
		int result = mapper.updateByPrimaryKeySelective(chapter);
		sqlSession.close();
		return result;
	}
	
	@Override
	public int closeClass(Classes classes) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		ClassesMapper mapper = sqlSession.getMapper(ClassesMapper.class);
		classes.setIsopen(false);;
		int result = mapper.updateByPrimaryKeySelective(classes);
		sqlSession.close();
		return result;
	}
	
	@Override
	public List<Student_Exam> fetchExamsResult(long subjectId, long examsId) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		Student_ExamMapper mapper = sqlSession.getMapper(Student_ExamMapper.class);
		List<Student_Exam> result = mapper.getBySubjectIdExamsId(subjectId, examsId);
		sqlSession.close();
		return result;
	}
	
	@Override
	public List<Student_Exam> fetchExamsResultClass(long classId, long examsId) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		Student_ExamMapper mapper = sqlSession.getMapper(Student_ExamMapper.class);
		List<Student_Exam> result = mapper.getBySubjectIdClassIdExamsId(classId, examsId);
		sqlSession.close();
		return result;
	}

	@Override
	public Subject getSubjectOfExam123(long examsId) {
		// TODO Auto-generated method stub
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		SubjectMapper subjectMapper  = sqlSession.getMapper(SubjectMapper.class);
		ExamsMapper examsMapper = sqlSession.getMapper(ExamsMapper.class);
	    Exams findExams = new Exams();
	    findExams = examsMapper.selectByPrimaryKey(examsId);
		Subject subject = new Subject();
		
		subject = subjectMapper.selectByPrimaryKey(findExams.getSubjectid().longValue());
		sqlSession.close();
		return subject;
	}
}

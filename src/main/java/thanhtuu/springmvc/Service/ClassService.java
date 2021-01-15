package thanhtuu.springmvc.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import thanhtuu.springmvc.Dao.Class_MemberMapper;
import thanhtuu.springmvc.Dao.ClassesMapper;
import thanhtuu.springmvc.Dao.Classes_ExamsMapper;
import thanhtuu.springmvc.Dao.ExamsMapper;
import thanhtuu.springmvc.Dao.UsersMapper;
import thanhtuu.springmvc.Domain.Class_Member;
import thanhtuu.springmvc.Domain.Classes;
import thanhtuu.springmvc.Domain.Classes_Exams;
import thanhtuu.springmvc.Domain.Exams;
import thanhtuu.springmvc.Domain.Users;
import thanhtuu.springmvc.Temporary.StudentExams;

public class ClassService implements ClassServiceLocal{
	@Override
	public int createClass(Classes classes) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
        SqlSession sqlSession = sqlMapper.openSession(true);
        ClassesMapper mapper = sqlSession.getMapper(ClassesMapper.class);
        
        classes.setCreatedat(new Date());
        classes.setModifiedat(new Date());
        classes.setIsopen(true);
        int result = mapper.insertSelective(classes);
 
        sqlSession.close();
        return result;
	}
	
	@Override
	public int assignStudentToClass(List<Class_Member> classMemberList) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
        SqlSession sqlSession = sqlMapper.openSession(true);
        Class_MemberMapper mapper = sqlSession.getMapper(Class_MemberMapper.class);
        
        int result = 1;
        Date now = new Date();
        for(int i = 0; i < classMemberList.size(); i++) {
        	classMemberList.get(i).setIsactive(true);
        	classMemberList.get(i).setModifiedat(now);
        	if (mapper.selectByPrimaryKey(classMemberList.get(i)) == null) {
        		classMemberList.get(i).setCreatedat(now);
        		result = result & mapper.insertSelective(classMemberList.get(i));
        	} else {
        		result = result & mapper.updateByPrimaryKeySelective(classMemberList.get(i));
        	}
        }
        sqlSession.close();
        return result;
	}
	
	@Override
	public List<Classes> getClassByTeacherId(long teacherId) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
        SqlSession sqlSession = sqlMapper.openSession(true);
        ClassesMapper mapper = sqlSession.getMapper(ClassesMapper.class);
        List<Classes> result = mapper.getClassOfTeacher(teacherId);
        sqlSession.close();
        return result;
	}
	
	@Override
	public List<Users> getStudentOfClass(int classId) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
        SqlSession sqlSession = sqlMapper.openSession(true);
        UsersMapper userMapper = sqlSession.getMapper(UsersMapper.class);
        Class_MemberMapper classMemberMapper = sqlSession.getMapper(Class_MemberMapper.class);
       
        List<Class_Member> classMemberList = classMemberMapper.getStudentOfClass(classId);
        List<Long> studentIdList = new ArrayList<Long>();
        
        for(int i = 0; i < classMemberList.size(); i++) {
        	studentIdList.add(classMemberList.get(i).getStudentid());
        }
        
        List<Users> result= new ArrayList<Users>();
        if (studentIdList.size() > 0) {     	
            result = userMapper.getUserOfList(studentIdList);
        }
        
        sqlSession.close();
        return result;
	}
	
	@Override
	public Classes getClassByClassId(long classId) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
        SqlSession sqlSession = sqlMapper.openSession(true);
        
        ClassesMapper mapper = sqlSession.getMapper(ClassesMapper.class);
        Classes result = mapper.selectByPrimaryKey(classId);
        sqlSession.close();
        
        return result;
	}
	
	@Override
	public List<Classes> getClassByStudentId(long studentId) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
        SqlSession sqlSession = sqlMapper.openSession(true);
        Class_MemberMapper classMemberMapper = sqlSession.getMapper(Class_MemberMapper.class);
        List<Class_Member> classMemberList = classMemberMapper.getClassOfStudent(studentId);

        List<Integer> classIdList = new ArrayList<Integer>();
        for(int i = 0; i < classMemberList.size(); i++) {
        	classIdList.add(classMemberList.get(i).getClassid());
        }
        
        List<Classes> classList = new ArrayList<Classes>();
        if (classIdList.size() > 0) {
        	ClassesMapper classMapper = sqlSession.getMapper(ClassesMapper.class);
        	classList = classMapper.getClassInList(classIdList);
        }
        
        sqlSession.close();
        return classList;
	}
	
	@Override
	public List<Integer> getStudentCountByClassIdList(List<Integer> classIdList) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
        SqlSession sqlSession = sqlMapper.openSession(true);
	    Class_MemberMapper classMemberMapper = sqlSession.getMapper(Class_MemberMapper.class);
	    
	    List<Integer> studenrCountList = new ArrayList<Integer>();
	    for(int i = 0; i < classIdList.size(); i++) {
	    	studenrCountList.add(classMemberMapper.getStudentCountByClassId(classIdList.get(i)).intValue());
	    }
	    
	    sqlSession.close();
	    return studenrCountList;
	}
	
	@Override
	public List<Integer> getExamsCountByClassIdList(List<Integer> classIdList) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
        SqlSession sqlSession = sqlMapper.openSession(true);
	    Classes_ExamsMapper classExamsMapper = sqlSession.getMapper(Classes_ExamsMapper.class);
	    
	    List<Integer> examsCountList = new ArrayList<Integer>();
	    for(int i = 0; i < classIdList.size(); i++) {
	    	examsCountList.add(classExamsMapper.getExamsCountByClassId(classIdList.get(i)).intValue());
	    }
	    
	    sqlSession.close();
	    return examsCountList;
	}
	
	@Override
	public List<StudentExams> getAllExamsByClassIdForTeacher(long classId) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
        SqlSession sqlSession = sqlMapper.openSession(true);
        
	    Classes_ExamsMapper classExamsMapper = sqlSession.getMapper(Classes_ExamsMapper.class);
	    List<Classes_Exams> classExamsList = classExamsMapper.getByClassId(classId);
	    
	    List<Integer> examsIdList = new ArrayList<Integer>();
	    for(int i = 0; i < classExamsList.size(); i++) {
	    	examsIdList.add(classExamsList.get(i).getExamsid());
	    }
	    
	    List<Exams> examsList = new ArrayList<Exams>();
	    if(classExamsList.size() > 0) {
	    	ExamsMapper examsMapper = sqlSession.getMapper(ExamsMapper.class);
	    	examsList = examsMapper.getByIdInList(examsIdList);
	    }
	    
	    List<StudentExams> studentExamList = new ArrayList<StudentExams>();
	    for (int i = 0; i < examsList.size(); i++) {
	    	StudentExams studentExam = new StudentExams();
	    	studentExam.exams = examsList.get(i);
	    	studentExamList.add(studentExam);
	    }
	    
	    sqlSession.close();
	    return studentExamList;
	}
	
	@Override
	public List<StudentExams> getAllExamsByClassIdForStudent(long classId) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
        SqlSession sqlSession = sqlMapper.openSession(true);
        
	    Classes_ExamsMapper classExamsMapper = sqlSession.getMapper(Classes_ExamsMapper.class);
	    List<Classes_Exams> classExamsList = classExamsMapper.getByClassId(classId);
	    
	    List<Integer> examsIdList = new ArrayList<Integer>();
	    for(int i = 0; i < classExamsList.size(); i++) {
	    	examsIdList.add(classExamsList.get(i).getExamsid());
	    }
	    
	    List<Exams> examsList = new ArrayList<Exams>();
	    if(classExamsList.size() > 0) {
	    	ExamsMapper examsMapper = sqlSession.getMapper(ExamsMapper.class);
	    	examsList = examsMapper.getByIdInListForStudent(examsIdList);
	    }
	    
	    List<StudentExams> studentExamList = new ArrayList<StudentExams>();
	    for (int i = 0; i < examsList.size(); i++) {
	    	StudentExams studentExam = new StudentExams();
	    	studentExam.exams = examsList.get(i);
	    	studentExamList.add(studentExam);
	    }
	    
	    sqlSession.close();
	    return studentExamList;
	}
}

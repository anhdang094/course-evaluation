package thanhtuu.springmvc.Service;

import java.util.ArrayList;

import thanhtuu.springmvc.Domain.Subject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import thanhtuu.springmvc.Dao.ExamsMapper;
import thanhtuu.springmvc.Dao.SubjectMapper;
import thanhtuu.springmvc.Domain.Exams;
import thanhtuu.springmvc.Temporary.Exam.StoreViewExam;

public class ViewStoresExamService {
      public ArrayList <StoreViewExam> viewAllBySubject(int TeacherId){
    	  SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
  		  SqlSession sqlSession = sqlMapper.openSession(true);
  		  SubjectMapper subjectMapper = sqlSession.getMapper(SubjectMapper.class);
  		  ExamsMapper examsMapper = sqlSession.getMapper(ExamsMapper.class);
  		  String examType ="offline";
  		  //Constructor List Store
  		  ArrayList<StoreViewExam> storeviewExam = new ArrayList<StoreViewExam>();
  		  //Created List Subject Id
  		  ArrayList<Integer> teachOfSubjectId = new ArrayList<Integer>();
  		  ArrayList<Exams> examsCreated = (ArrayList<Exams>) examsMapper.getAllSubjectOfTeacher(TeacherId, examType);
  		  for(int i = 0; i < examsCreated.size(); i++){
  			  teachOfSubjectId.add(examsCreated.get(i).getSubjectid());
  		  }
  		  
  		  //Create List
  		  for(int j = 0; j <teachOfSubjectId.size(); j++){
  			  StoreViewExam storeViewExam = new StoreViewExam();
  			  storeViewExam.subject = subjectMapper.selectByPrimaryKey((long)teachOfSubjectId.get(j));
  			  ArrayList<Exams> examsTemp = new ArrayList<Exams>();
  			  examsTemp = (ArrayList<Exams>) examsMapper.getAllExamOfSubjectOfTeacher(TeacherId, teachOfSubjectId.get(j), examType);
  			  storeViewExam.exams = examsTemp;
  		      storeviewExam.add(storeViewExam);
  		  
  		  }
  		  System.out.println("StoreViewExam : " );
  		  for(int l = 0; l < storeviewExam.size(); l++){
  			  Subject subjectList = storeviewExam.get(l).subject;
  			  System.out.println("Subject :" + subjectList.getName());
  			  ArrayList<Exams> examsList = storeviewExam.get(l).exams;
  			  for(int h =0; h <examsList.size(); h ++){
  				  System.out.println("ExamId" + examsList.get(h).getName());
  			  }
  		  }
  		sqlSession.close();
		return storeviewExam;
      }
}

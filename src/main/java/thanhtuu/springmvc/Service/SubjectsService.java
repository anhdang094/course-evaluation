package thanhtuu.springmvc.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import thanhtuu.springmvc.Dao.Student_SubjectMapper;
import thanhtuu.springmvc.Dao.SubjectMapper;
import thanhtuu.springmvc.Domain.Student_Subject;
import thanhtuu.springmvc.Domain.Subject;

public class SubjectsService implements SubjectsServiceLocal{
	
	@Override
	public List<Subject> getAllSubjectByStudentId(long userId) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		
		Student_SubjectMapper mapper = sqlSession.getMapper(Student_SubjectMapper.class);
		List<Student_Subject> result = mapper.getByStudentId(userId);
		sqlSession.close();
		
		List<Integer> list = new LinkedList<Integer>();
		for(int i = 0; i < result.size(); i++) {
			list.add(result.get(i).getSubjectid().intValue());
		}
		sqlSession.close();
		return this.getSubjectInList(list);
	}
	
	@Override
	public List<Subject> getSubjectInList(List<Integer> subjectIdList) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		SubjectMapper mapper = sqlSession.getMapper(SubjectMapper.class);
		
		List<Subject> result;
		if (subjectIdList.size() > 0) {
			result = mapper.getSubjectInList(subjectIdList);
		} else {
			result = new ArrayList<Subject>();
		}
		
		sqlSession.close();
		return result;
	}
}

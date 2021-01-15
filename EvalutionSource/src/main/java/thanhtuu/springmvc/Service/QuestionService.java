package thanhtuu.springmvc.Service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.annotations.Param;
import thanhtuu.springmvc.Dao.QuestionMapper;
import thanhtuu.springmvc.Domain.Question;
import thanhtuu.springmvc.Domain.QuestionWithBLOBs;

public class QuestionService implements QuestionServiceLocal {

	@Override
	public Question getByQuestionId(long id) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		QuestionMapper mapper = sqlSession.getMapper(QuestionMapper.class);
		Question question = mapper.selectByPrimaryKey(id);
		sqlSession.close();
		return question;
	}

	@Override
	public List<Question> getQuestionList() {
		// TODO Auto-generated method stub
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		QuestionMapper mapper = sqlSession.getMapper(QuestionMapper.class);
		List<Question> qList = mapper.getQuestionList();
		sqlSession.close();
		return qList;
	}

	@Override
	public List<Question> getqueryExamQuestion(int ExamID, int chapterSubjects) {
		// TODO Auto-generated method stub
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		QuestionMapper mapper = sqlSession.getMapper(QuestionMapper.class);
		List<Question> qList = mapper.getqueryExamQuestion(ExamID, chapterSubjects);
		sqlSession.close();
		return qList;
	}

	@Override
	public List<QuestionWithBLOBs> getQuestionWithBLOBsList() {
		// TODO Auto-generated method stub
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		QuestionMapper mapper = sqlSession.getMapper(QuestionMapper.class);
		List<QuestionWithBLOBs> qList = mapper.getQuestionWithBLOBsList();
		sqlSession.close();
		return qList;
	}

	@Override
	public void insert(QuestionWithBLOBs questionW) {
		// TODO Auto-generated method stub
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		QuestionMapper mapper = sqlSession.getMapper(QuestionMapper.class);
		mapper.insert(questionW);
		sqlSession.close();
	}

	@Override
	public List<Question> getqueryLevelQuestion(String LevelQ) {
		// TODO Auto-generated method stub
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		QuestionMapper mapper = sqlSession.getMapper(QuestionMapper.class);
		List<Question> qList = mapper.getqueryLevelQuestion(LevelQ);
		sqlSession.close();
		return qList;
	}

	@Override
	public void deleteQuestionId(long questionid) {
		// TODO Auto-generated method stub
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		QuestionMapper mapper = sqlSession.getMapper(QuestionMapper.class);
		mapper.deleteByPrimaryKey(questionid);
		sqlSession.close();
		
	}

	@Override
	public void updateByPrimaryKeyWithBLOBs(QuestionWithBLOBs questionEdit) {
		// TODO Auto-generated method stub
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		QuestionMapper mapper = sqlSession.getMapper(QuestionMapper.class);
		mapper.updateByPrimaryKeyWithBLOBs(questionEdit);
		sqlSession.close();
	}

	@Override
	public List<Question> getSubjectsList() {
		// TODO Auto-generated method stub
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		QuestionMapper mapper = sqlSession.getMapper(QuestionMapper.class);
		List<Question> getsubject = mapper.getSubject();
		sqlSession.close();
		return getsubject;
	}

	@Override
	public List<Question> getquerySubjectQuestion(String subjects) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		QuestionMapper mapper = sqlSession.getMapper(QuestionMapper.class);
		List<Question> getexamsubject = mapper.getquerySubjectQuestion(subjects);
		sqlSession.close();
		return getexamsubject;
	}

}

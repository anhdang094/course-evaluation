package thanhtuu.springmvc.Service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import thanhtuu.springmvc.Dao.StudentMapper;
import thanhtuu.springmvc.Domain.Student;

public class StudentService implements StudentServiceLocal {

	@Override
	public void insertStudent(Student student) {
		// TODO Auto-generated method stub
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
        SqlSession sqlSession = sqlMapper.openSession(true);
	    StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
	    mapper.insert(student);
	    sqlSession.close();
	}
}

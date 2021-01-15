package thanhtuu.springmvc.Service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import thanhtuu.springmvc.Dao.TeacherMapper;
import thanhtuu.springmvc.Domain.Teacher;

/**
 * Created by Administrator on 9/1/2016.
 */
public class TeacherService implements TeacherServiceLocal {
    @Override
    public void insertTeacher(Teacher teacher) {
        // TODO Auto-generated method stub
        SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
        SqlSession sqlSession = sqlMapper.openSession(true);
        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
        mapper.insert(teacher);
        sqlSession.close();
    }

	@Override
	public Teacher teacher(long id) {
		// TODO Auto-generated method stub
		 SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
	     SqlSession sqlSession = sqlMapper.openSession(true);
	     TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
	     
		Teacher teacher = mapper.getTeacherById(id);
		sqlSession.close();
		return teacher;
		
	}
}

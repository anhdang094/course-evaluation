package thanhtuu.springmvc.Service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import thanhtuu.springmvc.Dao.UsernameMapper;
import thanhtuu.springmvc.Domain.Username;

public class UsernameService implements UsernameServiceLocal {

	public Username getByUserId(long id) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		UsernameMapper mapper = sqlSession.getMapper(UsernameMapper.class);
		Username user = mapper.selectByPrimaryKey(id);
		sqlSession.close();
		return user;
	}

	@Override
	public List<Username> getUserList() {
		// TODO Auto-generated method stub
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		UsernameMapper mapper = sqlSession.getMapper(UsernameMapper.class);
		List<Username> userList = mapper.getUserList();
		sqlSession.close();
		return userList;
	}

	@Override
	public void insert(Username user) {
		// TODO Auto-generated method stub
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		UsernameMapper mapper = sqlSession.getMapper(UsernameMapper.class);
		mapper.insert(user);
	}

	@Override
	public void updateByPrimaryKey(Username user) {
		// TODO Auto-generated method stub
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		UsernameMapper mapper = sqlSession.getMapper(UsernameMapper.class);
		mapper.updateByPrimaryKey(user);
		sqlSession.close();
	}

	@Override
	public Username getIDByEmail(String email) {
		// TODO Auto-generated method stub
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		UsernameMapper mapper = sqlSession.getMapper(UsernameMapper.class);
		Username user = mapper.getEmail(email);
		sqlSession.close();
		return user;
	}
}

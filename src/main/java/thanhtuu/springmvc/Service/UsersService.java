package thanhtuu.springmvc.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import thanhtuu.springmvc.Dao.StudentMapper;
import thanhtuu.springmvc.Dao.TeacherMapper;
import thanhtuu.springmvc.Dao.UsersMapper;
import thanhtuu.springmvc.Domain.Users;
import thanhtuu.springmvc.Temporary.Account;

public class UsersService implements UsersServiceLocal{

	@Override
	public Users getIdByEmail(String eID) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		UsersMapper mapper = sqlSession.getMapper(UsersMapper.class);
		Users user = mapper.getEmail(eID);
		sqlSession.close();
		return user;
	}

	@Override
	public void createAccount(Account account) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		UsersMapper userMapper = sqlSession.getMapper(UsersMapper.class);
		StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
		TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
		
		String password = account.user.getPassword();
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		md.update(password.getBytes());
		byte byteData[] = md.digest();
		
		// Convert the byte to hex format method 1
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}
		
		account.user.setPassword(sb.toString());
		account.user.setIsactive(true);
	    userMapper.insertSelective(account.user);
	    
	    if (account.user.getRole().equals("teacher")) {
	    	account.teacher.setId(account.user.getId());
	    	teacherMapper.insertSelective(account.teacher);
	    }
	    
	    if (account.user.getRole().equals("student")) {
	    	account.student.setId(account.user.getId());
	    	studentMapper.insertSelective(account.student);
	    }
	    
		sqlSession.close();
	}

}

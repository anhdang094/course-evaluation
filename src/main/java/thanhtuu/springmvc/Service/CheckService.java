package thanhtuu.springmvc.Service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import thanhtuu.springmvc.Dao.Class_MemberMapper;
import thanhtuu.springmvc.Dao.Classes_ExamsMapper;
import thanhtuu.springmvc.Dao.StudentMapper;
import thanhtuu.springmvc.Dao.UsersMapper;
import thanhtuu.springmvc.Domain.Class_Member;
import thanhtuu.springmvc.Domain.Classes_Exams;
import thanhtuu.springmvc.Domain.Users;

public class CheckService implements CheckServiceLocal{

	@Override
	public Users checkStudent(Users student) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
        SqlSession sqlSession = sqlMapper.openSession(true);
        
        UsersMapper userMapper = sqlSession.getMapper(UsersMapper.class);
        Users users = userMapper.getEmail(student.getEmail());  
        
        if (!users.equals(null) && !users.getRole().isEmpty()) {
        	StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            long result = studentMapper.checkStudent(users.getId());
            sqlSession.close();
            System.out.println(result);
            if(result > 0) return users;
        }
        sqlSession.close();
        return new Users();
	}
	
	@Override
	public Class_Member checkStudentExam(long studentId, long examsId) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
        SqlSession sqlSession = sqlMapper.openSession(true);
        
        Classes_ExamsMapper userMapper = sqlSession.getMapper(Classes_ExamsMapper.class);
        List<Classes_Exams> classExamsList = userMapper.getClassExamsByExamsId(examsId);
        
        List<Integer> classIdList = new ArrayList<Integer>();
        for(int i = 0; i < classExamsList.size(); i++) {
        	classIdList.add(classExamsList.get(i).getClassid().intValue());
        }
        Class_MemberMapper classMemberMapper = sqlSession.getMapper(Class_MemberMapper.class);
        Class_Member classesMember = classMemberMapper.getClassMemberList(studentId, classIdList);
        
        sqlSession.close();  
        return classesMember;
	}
}

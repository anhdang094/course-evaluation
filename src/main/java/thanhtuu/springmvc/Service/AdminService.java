package thanhtuu.springmvc.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import thanhtuu.springmvc.Dao.SubjectMapper;
import thanhtuu.springmvc.Dao.Super_Teacher_SubjectMapper;
import thanhtuu.springmvc.Dao.TeacherMapper;
import thanhtuu.springmvc.Dao.Teacher_SubjectMapper;
import thanhtuu.springmvc.Dao.NotificationMapper;
import thanhtuu.springmvc.Dao.StudentMapper;
import thanhtuu.springmvc.Domain.Notification;
import thanhtuu.springmvc.Domain.Student;
import thanhtuu.springmvc.Domain.Subject;
import thanhtuu.springmvc.Domain.Super_Teacher_Subject;
import thanhtuu.springmvc.Domain.Teacher;
import thanhtuu.springmvc.Domain.Teacher_Subject;

public class AdminService implements AdminServiceLocal{
	@Override
	public List<Teacher> getAllTeacher() {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
		List<Teacher> teacherList = mapper.getAllTeacher();
		sqlSession.close();
		return teacherList;
	}
	
	@Override
	public List<Student> getAllStudent() {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
		List<Student> subjectList = mapper.getAll();
		sqlSession.close();
		return subjectList;
	}
	
	@Override
	public List<Subject> getAllSubject() {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		SubjectMapper mapper = sqlSession.getMapper(SubjectMapper.class);
		List<Subject> subjectList = mapper.getAllSubject();
		sqlSession.close();
		return subjectList;
	}
	
	@Override
	public int createSubject(Subject subject) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		SubjectMapper mapper = sqlSession.getMapper(SubjectMapper.class);
		
		Date now = new Date();
		subject.setCreatedat(now);
		subject.setModifiedat(now);
		subject.setIsactive(true);
		
		int result = mapper.insertSelective(subject);
		sqlSession.close();
		return result;
	}
	
	@Override
	public int superTeacherToSubject(Super_Teacher_Subject superTeacherSubject) {
		Teacher_Subject teacherSubject = new Teacher_Subject();
		teacherSubject.setAdminid(superTeacherSubject.getAdminid());
		teacherSubject.setSubjectid(superTeacherSubject.getSubjectid());
		teacherSubject.setTeacherid(superTeacherSubject.getTeacherid());
		this.teacherToSubject(teacherSubject);
		
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		Super_Teacher_SubjectMapper mapper = sqlSession.getMapper(Super_Teacher_SubjectMapper.class);
		
		int result = 0;
		Date now = new Date();
		superTeacherSubject.setModifiedat(now);
		superTeacherSubject.setIsactive(true);
		
		if (mapper.selectByPrimaryKey(superTeacherSubject) == null) {
			superTeacherSubject.setCreatedat(now);
			result = mapper.insertSelective(superTeacherSubject);
		} else {
			result = mapper.updateByPrimaryKeySelective(superTeacherSubject);
		}
		
		
		sqlSession.close();
		return result;
	}
	
	@Override
	public int teacherToSubject(Teacher_Subject teacherSubject) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		Teacher_SubjectMapper mapper = sqlSession.getMapper(Teacher_SubjectMapper.class);
		
		int result = 0;
		Date now = new Date();
		teacherSubject.setModifiedat(now);
		teacherSubject.setIsactive(true);
		
		if (mapper.selectByPrimaryKey(teacherSubject) == null) {
			teacherSubject.setCreatedat(now);
			result = mapper.insertSelective(teacherSubject);
		} else {
			mapper.updateByPrimaryKeySelective(teacherSubject);
		}
		sqlSession.close();
		return result;
	}
	
	@Override
	public List<Subject> fetchSubjectTeachOfTeacher(long teacherId) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		Teacher_SubjectMapper teacherSubjecMapper = sqlSession.getMapper(Teacher_SubjectMapper.class);
		SubjectMapper subjectMapper = sqlSession.getMapper(SubjectMapper.class);
		
		List<Teacher_Subject> teacherSubjectList = teacherSubjecMapper.getAllSubjectByTeacherId(teacherId);
		List<Integer> subjectIdList = new ArrayList<Integer>();
		for(int i = 0; i < teacherSubjectList.size(); i++) {
			subjectIdList.add(teacherSubjectList.get(i).getSubjectid().intValue());
		}
		
		List<Subject> subjectList = new ArrayList<Subject>();
		if (subjectIdList.size() > 0) {
			subjectList = subjectMapper.getSubjectInList(subjectIdList);
		}
		sqlSession.close();
		return subjectList;
	}
	
	@Override
	public List<Subject> fetchSubjectManageOfTeacher(long teacherId) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		Super_Teacher_SubjectMapper superTeacherSubjectMapper = sqlSession.getMapper(Super_Teacher_SubjectMapper.class);
		SubjectMapper subjectMapper = sqlSession.getMapper(SubjectMapper.class);
		
		List<Super_Teacher_Subject> superTeacherSubjectList = superTeacherSubjectMapper.getByTeacherId(teacherId);
		List<Integer> subjectIdList = new ArrayList<Integer>();
		for(int i = 0; i < superTeacherSubjectList.size(); i++) {
			subjectIdList.add(superTeacherSubjectList.get(i).getSubjectid().intValue());
		}
		
		List<Subject> subjectList = new ArrayList<Subject>();
		if (subjectIdList.size() > 0) {
			subjectList = subjectMapper.getSubjectInList(subjectIdList);
		}
		sqlSession.close();
		return subjectList;
	}
	
	@Override
	public int unassignSuperTeacher(Super_Teacher_Subject superTeacherSubject) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		Super_Teacher_SubjectMapper teacherSubjectMapper = sqlSession.getMapper(Super_Teacher_SubjectMapper.class);
		superTeacherSubject.setIsactive(false);
		int result =  teacherSubjectMapper.updateByPrimaryKeySelective(superTeacherSubject);
		sqlSession.close();
		return result;
	}
	
	@Override
	public int unassignTeacher(Teacher_Subject teacherSubject) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		Teacher_SubjectMapper teacherSubjectMapper = sqlSession.getMapper(Teacher_SubjectMapper.class);
		teacherSubject.setIsactive(false);
		int result = teacherSubjectMapper.updateByPrimaryKeySelective(teacherSubject);
		sqlSession.close();
		
		Super_Teacher_Subject superTeacherSubject = new Super_Teacher_Subject();
		superTeacherSubject.setSubjectid(teacherSubject.getSubjectid());
		superTeacherSubject.setTeacherid(teacherSubject.getTeacherid());
		this.unassignSuperTeacher(superTeacherSubject);
		sqlSession.close();
		return result;
	}
	
	@Override
	public List<Teacher> fetchTeacherManageOfSubject(long subjectId){
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		Super_Teacher_SubjectMapper superTeacherSubjectMapper = sqlSession.getMapper(Super_Teacher_SubjectMapper.class);
		TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
		
		List<Super_Teacher_Subject> superTeacherSubjectList = superTeacherSubjectMapper.getBySubjectId(subjectId);
		List<Integer> teacherIdList = new ArrayList<Integer>();
		for(int i = 0; i < superTeacherSubjectList.size(); i++) {
			teacherIdList.add(superTeacherSubjectList.get(i).getTeacherid());
		}
		
		List<Teacher> teacherList = new ArrayList<Teacher>();
		if (teacherIdList.size() > 0) {
			teacherList = teacherMapper.getInList(teacherIdList);
		}
		sqlSession.close();
		return teacherList;
	}
	
	@Override
	public List<Teacher> fetchTeacherTeachOfSubject(long subjectId){
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		Teacher_SubjectMapper teacherSubjectMapper = sqlSession.getMapper(Teacher_SubjectMapper.class);
		TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
		
		List<Teacher_Subject> teacherSubjectList = teacherSubjectMapper.getBySubjectId(subjectId);
		List<Integer> teacherIdList = new ArrayList<Integer>();
		for(int i = 0; i < teacherSubjectList.size(); i++) {
			teacherIdList.add(teacherSubjectList.get(i).getTeacherid());
		}
		
		List<Teacher> teacherList = new ArrayList<Teacher>();
		if (teacherIdList.size() > 0) {
			teacherList = teacherMapper.getInList(teacherIdList);
		}
		sqlSession.close();
		return teacherList;
	}
	
	@Override
	public int deleteSubject(Subject subject) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		SubjectMapper subjectMapper = sqlSession.getMapper(SubjectMapper.class);
		
		subject.setIsactive(false);
		subject.setModifiedat(new Date());
		int result = subjectMapper.updateByPrimaryKeySelective(subject);
		sqlSession.close();
		return result;
	}

	@Override
	public List<Notification> getAllNotification() {
		// TODO Auto-generated method stub
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		NotificationMapper mapper = sqlSession.getMapper(NotificationMapper.class);
		List<Notification> notiList = mapper.getAllNotification();
		sqlSession.close();
		return notiList;
		
	}

	@Override
	public void addNotification(Notification notification) {
		// TODO Auto-generated method stub
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		NotificationMapper mapper = sqlSession.getMapper(NotificationMapper.class);
		Date now = new Date();
		notification.setCreatedat(now);
		notification.setModifiedat(now);
		mapper.insert(notification);
		 sqlSession.close();
	}

	@Override
	public void deleteNotification(long id) {
		// TODO Auto-generated method stub
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		NotificationMapper mapper = sqlSession.getMapper(NotificationMapper.class);
		mapper.deleteByPrimaryKey(id);
		sqlSession.close();
	}

	@Override
	public void modifyNotification(Notification notification) {
		// TODO Auto-generated method stub
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		Date date = new Date();
		NotificationMapper mapper = sqlSession.getMapper(NotificationMapper.class);
		Notification noti = mapper.selectByPrimaryKey(notification.getId());
		noti.setBody(notification.getBody());
		
		noti.setLink(notification.getLink());
		noti.setModifiedat(date);
		noti.setTitle(notification.getTitle());
		noti.setUsercreate(notification.getUsercreate());
		
		mapper.updateByPrimaryKeySelective(noti);
		sqlSession.close();
	}
}

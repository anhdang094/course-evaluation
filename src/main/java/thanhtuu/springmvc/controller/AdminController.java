package thanhtuu.springmvc.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import thanhtuu.springmvc.Domain.Notification;
import thanhtuu.springmvc.Domain.Student;
import thanhtuu.springmvc.Domain.Subject;
import thanhtuu.springmvc.Domain.Super_Teacher_Subject;
import thanhtuu.springmvc.Domain.Teacher;
import thanhtuu.springmvc.Domain.Teacher_Subject;
import thanhtuu.springmvc.Domain.Users;
import thanhtuu.springmvc.Service.AdminServiceLocal;
import thanhtuu.springmvc.Service.UsersServiceLocal;

@RestController
public class AdminController {
	@Autowired
	private AdminServiceLocal adminService;
	@Autowired
	private UsersServiceLocal usersService;
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/getAllTeacher", method = RequestMethod.GET)
	public ResponseEntity<List<Teacher>> getAllTeacher() {
		List<Teacher> teacherList = adminService.getAllTeacher();
		return new ResponseEntity<List<Teacher>>(teacherList, HttpStatus.OK);
	}
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/getAllNotification", method = RequestMethod.GET)
	public ResponseEntity<List<Notification>> getAllNotifications() {
		List<Notification> notiList = adminService.getAllNotification();
		return new ResponseEntity<List<Notification>>(notiList, HttpStatus.OK);
	}
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/getAllNotificationToHomePage", method = RequestMethod.GET)
	public ResponseEntity<List<Notification>> getAllNotificationToHomePage() {
		List<Notification> notiList = adminService.getAllNotification();
		return new ResponseEntity<List<Notification>>(notiList, HttpStatus.OK);
	}
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/addnotificationABC", method = RequestMethod.POST)
	public void addNotifications(@RequestBody Notification noti) {
		System.out.println("title : " + noti.getTitle());
		System.out.println("body : " + noti.getBody());
		System.out.println("link : " + noti.getLink());
		System.out.println("Usercreate : " + noti.getUsercreate());
		System.out.println("modifiat : " + noti.getCreatedat());
		adminService.addNotification(noti);
	}
	
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/modifyNotification", method = RequestMethod.POST)
	public void modifyNotification(@RequestBody Notification noti) {
		System.out.println("====MODIFY Notification  ====");
		System.out.println("modifiat : " + noti.getId());
		adminService.modifyNotification(noti);
	}
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/delNotification", method = RequestMethod.POST)
	public void delNotification(@RequestBody int Id) {
		System.out.println("title : " +Id);
		
		adminService.deleteNotification(Id);
	}
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/getAllSubject", method = RequestMethod.GET)
	public ResponseEntity<List<Subject>> getAllSubject() {
		List<Subject> subjectList = adminService.getAllSubject();
		return new ResponseEntity<List<Subject>>(subjectList, HttpStatus.OK);
	}
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/getAllStudent", method = RequestMethod.GET)
	public ResponseEntity<List<Student>> getAllStudent() {
		List<Student> studentList = adminService.getAllStudent();
		return new ResponseEntity<List<Student>>(studentList, HttpStatus.OK);
	}
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/fetchSubjectTeachOfTeacher/{teacherId}", method = RequestMethod.GET)
	public ResponseEntity<List<Subject>> fetchSubjectTeachOfTeacher(@PathVariable("teacherId") long teacherId) {
		List<Subject> subjectList = adminService.fetchSubjectTeachOfTeacher(teacherId);
		return new ResponseEntity<List<Subject>>(subjectList, HttpStatus.OK);
	}
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/fetchSubjectManageOfTeacher/{teacherId}", method = RequestMethod.GET)
	public ResponseEntity<List<Subject>> fetchSubjectOfTeacher(@PathVariable("teacherId") long teacherId) {
		List<Subject> subjectList = adminService.fetchSubjectManageOfTeacher(teacherId);
		return new ResponseEntity<List<Subject>>(subjectList, HttpStatus.OK);
	}
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/createSubject", method = RequestMethod.POST)
	public int createSubject(@RequestBody Subject subject, Principal principal) {
		String email = principal.getName();
		Users userlogin = usersService.getIdByEmail(email);
		subject.setAdminid(userlogin.getId().intValue());
		return adminService.createSubject(subject);
	}
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/assignSuperTeacher", method = RequestMethod.POST)
	public int assignTeacherToSubject(@RequestBody Super_Teacher_Subject superTeacherSubject, Principal principal) {
		String email = principal.getName();
		Users userlogin = usersService.getIdByEmail(email);
		superTeacherSubject.setAdminid(userlogin.getId().intValue());
		return adminService.superTeacherToSubject(superTeacherSubject);
	}
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/assignTeacher", method = RequestMethod.POST)
	public int teacherToSubject(@RequestBody Teacher_Subject teacherSubject, Principal principal) {
		String email = principal.getName();
		Users userlogin = usersService.getIdByEmail(email);
		teacherSubject.setAdminid(userlogin.getId().intValue());
		return adminService.teacherToSubject(teacherSubject);
	}
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/unassignTeacher", method = RequestMethod.POST)
	public int unassignTeacher(@RequestBody Teacher_Subject teacherSubject) {
		return adminService.unassignTeacher(teacherSubject);
	}
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/unassignSuperTeacher", method = RequestMethod.POST)
	public int unassignSuperTeacher(@RequestBody Super_Teacher_Subject teacherSubject, Principal principal) {
		return adminService.unassignSuperTeacher(teacherSubject);
	}
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/fetchTeacherTeachOfSubject/{subjectId}", method = RequestMethod.GET)
	public ResponseEntity<List<Teacher>> fetchTeacherTeachOfSubject(@PathVariable("subjectId") long subjectId) {
		List<Teacher> subjectList = adminService.fetchTeacherTeachOfSubject(subjectId);
		return new ResponseEntity<List<Teacher>>(subjectList, HttpStatus.OK);
	}
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/fetchTeacherManageOfSubject/{subjectId}", method = RequestMethod.GET)
	public ResponseEntity<List<Teacher>> fetchTeacherManageOfSubject(@PathVariable("subjectId") long subjectId) {
		List<Teacher> subjectList = adminService.fetchTeacherManageOfSubject(subjectId);
		return new ResponseEntity<List<Teacher>>(subjectList, HttpStatus.OK);
	}
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/deleteSubject", method = RequestMethod.POST)
	public int deleteSubject(@RequestBody Subject subject) {
		return adminService.deleteSubject(subject);
	}
}

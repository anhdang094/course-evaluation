package thanhtuu.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import thanhtuu.springmvc.Domain.Users;
import thanhtuu.springmvc.Service.CheckServiceLocal;

@RestController
public class CheckController {
	
	@Autowired
	private CheckServiceLocal checkService;
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/checkStudent", method = RequestMethod.POST)
	public ResponseEntity<Users> getClassOfUser(@RequestBody Users student) {
		System.out.println("------------------------");
		System.out.println(student.getEmail());
		System.out.println("------------------------");
		Users user = checkService.checkStudent(student);
		return new ResponseEntity<Users>(user, HttpStatus.OK);
	}
}

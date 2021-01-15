package thanhtuu.springmvc.controller;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import thanhtuu.springmvc.Service.UsersServiceLocal;
import thanhtuu.springmvc.Temporary.Account;
import thanhtuu.springmvc.Temporary.Message;

@RestController
public class UsersController {
	 @Autowired
	 private UsersServiceLocal usersService;
	
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/createAccount", method = RequestMethod.POST)
	public Message createAccount(@RequestBody Account account) throws ServletException, IOException {

		String checkRole = account.user.getRole();
		String checkemail = account.user.getEmail();
		String checkpassword = account.user.getPassword();
		
		Message message = new Message();
		message.message =  "Tạo tài khoản thành công";
		message.type =  "success";
		
		if (checkRole == null || checkRole.equals("")) {
			message.message =  "Phải chọn loại tài khoản";
			message.type =  "error";
			return message;
		}
		
		if (checkemail == null || checkemail.equals("")) {
			message.message = "Email không được rỗng";
			message.type =  "error";
			return message;
		}
		
		if (checkpassword == null || checkpassword.length() < 6 || checkpassword.length() > 20) {
			message.message =  "Mật khẩu từ 6 đến 20 ký tự";
			message.type =  "error";
			return message;
		}
		
		if (usersService.getIdByEmail(checkemail) != null) {
			message.message =  "Emai đã được đăng ký";
			message.type =  "error";
			return message;
		}
		
		usersService.createAccount(account);
		return message;
	}
}

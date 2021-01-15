package thanhtuu.springmvc.controller;

import java.security.MessageDigest;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import thanhtuu.springmvc.Domain.Username;
import thanhtuu.springmvc.Service.UsernameServiceLocal;;

@Controller
public class UserController {
	@Autowired
	private UsernameServiceLocal userService;

	@RequestMapping("username/{UserID}")
	public String helloworld(@PathVariable long UserID, Model model) {
		Username username = userService.getByUserId(UserID);
		model.addAttribute("username", username);
		return "hello";
	}

	@RequestMapping("/list")
	public String list(Model model) {
		List<Username> userList = userService.getUserList();
		model.addAttribute("userList", userList);
		return "userList";
	}

	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getloginform(Model model, Integer id) {
		return "login";
	}

	/*
	 * Create Form
	 */
	@RequestMapping(value = "/signupform", method = RequestMethod.GET)
	public String formGet(Model model, Integer id) {
		Username username;
		if (id != null) {
			username = userService.getByUserId(id);
		} else {
			username = new Username();
		}
		model.addAttribute("username", username);
		return "registerForm";
	}

	@RequestMapping(value = "/signupform", method = RequestMethod.POST)
	public String formPost(Model model, Username username) throws Exception {
		if (username.getUserid() == null) {
			String password = username.getPasswordu();
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte byteData[] = md.digest();
			// convert the byte to hex format method 1
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
			username.setPasswordu(sb.toString());
			userService.insert(username);
		} else {
			userService.updateByPrimaryKey(username);
		}
		return "redirect:/login";
	}

	@RequestMapping("profile/{UserID}")
	public String profilelistId(@PathVariable long UserID, Model model) {
		Username userp = userService.getByUserId(UserID);
		model.addAttribute("username", userp);
		return "userprofile";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String postloginform(Model model, Principal principal) {		
			String email = principal.getName();
			Username userlogin = userService.getIDByEmail(email);
			model.addAttribute("userlogin", userlogin);
		
		return "home";
	}
}

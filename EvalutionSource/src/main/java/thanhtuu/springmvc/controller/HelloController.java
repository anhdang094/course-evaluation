package thanhtuu.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class HelloController {
	 @RequestMapping("/helloworld/hello")
	    public String helloworld(Model model) {
	         
	        model.addAttribute("name", "HELLO");
	         
	        return "hello";
	    }
}

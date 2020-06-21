package vn.com.abcblog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin")
public class AdminHomeController {

	@RequestMapping(value = "/home")
	public String showHome() {
		return "admin/home";
	}
	
}

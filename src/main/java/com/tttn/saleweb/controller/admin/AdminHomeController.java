package com.tttn.saleweb.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AdminHomeController {

	@RequestMapping("/admin/home/index")
	public String home() {
		return "admin/home/index";
	}

}

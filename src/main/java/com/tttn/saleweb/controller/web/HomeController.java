package com.tttn.saleweb.controller.web;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tttn.saleweb.entity.Product;
import com.tttn.saleweb.service.ICategoryService;
import com.tttn.saleweb.service.IProductService;

@Controller
public class HomeController {
	@Autowired
	private ICategoryService serviceCategory;

	@Autowired
	private IProductService prodService;

	@RequestMapping("/home/index")
	public String index() {
		return "home/index";
	}

	@RequestMapping("/home/about")
	public String about() {
		return "home/about";
	}

	@RequestMapping("/home/contact")
	public String contact() {
		return "home/contact";
	}

	@RequestMapping("/home/feedback")
	public String feedback() {
		return "home/feedback";
	}

	@RequestMapping("/home/faq")
	public String faq() {
		return "home/faq";
	}

	@RequestMapping("/home/aside/category")
	public String category(Model model) {
		model.addAttribute("cates", serviceCategory.findAll());
		return "non-layout/layout/_aside-category";
	}

	@RequestMapping("/home/random")
	public String random(Model model) {
		List<Product> list = prodService.getRandomProduct();
		Collections.shuffle(list);
		model.addAttribute("list", list.subList(0, 6));
		return "non-layout/product/list";
	}
}

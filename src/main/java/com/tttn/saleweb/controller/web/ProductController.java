package com.tttn.saleweb.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tttn.saleweb.dao.IProductDAO;
import com.tttn.saleweb.entity.Product;
import com.tttn.saleweb.service.ICategoryService;
import com.tttn.saleweb.service.ICookieService;
import com.tttn.saleweb.service.IHttpService;
import com.tttn.saleweb.service.IMailService;
import com.tttn.saleweb.service.IProductService;


@Controller
public class ProductController {

	@Autowired
	private ICategoryService service;
	@Autowired
	private IProductDAO dao;

	@Autowired
	private IProductService serviceProduct;

	@Autowired
	private ICookieService cookieService;

	@Autowired
	private IMailService mailService;

	@Autowired
	private IHttpService httpService;

	@RequestMapping("/product/list-by-category/{cId}")
	public String listByCategory(@PathVariable("cId") Integer id, Model model) {
		model.addAttribute("list", service.findById(id).getProducts());
		return "product/list";
	}

	@RequestMapping("/product/list-by-keywords")
	public String listByKeyWords(@RequestParam("keywords") String keywords, Model model) {
		model.addAttribute("list", serviceProduct.findByKeywords(keywords));
		return "product/list";
	}

	@RequestMapping("/product/detail/{id}")
	public String detail(@PathVariable("id") Integer id, Model model) {    
		Product p = serviceProduct.findById(id);
		p.setViewCount(p.getViewCount() + 1); 
		dao.update(p);
		model.addAttribute("prod", p);
		List<Product> listDaXem = serviceProduct.getViewProduct("daXem", id.toString());
		model.addAttribute("daXem", listDaXem);
		String ids = cookieService.getCookieValue("like", "");
		if (!ids.isEmpty()) {
			List<Product> list = dao.findByIdsInCookie(ids);
			model.addAttribute("like", list);
		}
		return "product/detail";
	}

	@ResponseBody
	@RequestMapping("/product/favorite/{id}")
	public String[] favorite(@PathVariable("id") Integer id) {
		String ids = cookieService.getCookieValue("like", id.toString());
		if (!ids.contains(id.toString())) {
			ids += "," + id.toString();
		}
		cookieService.createCookie("like", ids, 15);
		return ids.split(",");
	}

	@RequestMapping("/product/list-by-hot/{key}")
	public String listByHot(@PathVariable("key") String key, Model model) 
	{
		List<Product> listP = serviceProduct.findByHot(key);
		model.addAttribute("list", listP);
		return "product/list";
	}
}

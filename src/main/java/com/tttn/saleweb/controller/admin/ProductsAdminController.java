package com.tttn.saleweb.controller.admin;

import java.io.File;
import java.util.List;

import com.tttn.saleweb.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tttn.saleweb.entity.Product;
import com.tttn.saleweb.service.ICategoryService;
import com.tttn.saleweb.service.IHttpService;
import com.tttn.saleweb.service.IProductService;

@Controller
@RequestMapping("/admin/product/")
public class ProductsAdminController {
	@Autowired
	private IProductService productService;
	
	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private IHttpService http;

	@RequestMapping("index")
	public String index(Model model, @RequestParam(name = "category_id",required = false)Integer category_id) {
		List<Category> list = categoryService.findAll();
		model.addAttribute("categories", list);
		if (category_id == null) {
			category_id = list.get(0).getId();
		}
		model.addAttribute("list", productService.findAllProductByCategory(category_id));
		model.addAttribute("product", new Product());
		return "admin/product/index";
	}

	@RequestMapping("edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("product", productService.findById(id));
		model.addAttribute("list", productService.findAll());
		model.addAttribute("cates", categoryService.findAll());
		return "admin/product/index";
	}

	@RequestMapping("create")
	public String create(Model model, RedirectAttributes params, @Validated Product form, BindingResult errors,
			@RequestParam("image_file") MultipartFile file) {
		if (errors.hasErrors()) {
			model.addAttribute("message", "Vui l??ng kh??ng b??? tr???ng c??c tr?????ng b??n tr??n");
			return "admin/product/index";
		}
		File image = http.saveProductPhoto(file);
		if (image != null) {
			form.setImage(image.getName());
		}
		// th??m xu???ng db
		productService.add(form);
		params.addAttribute("message", "Th??m m???i th??nh c??ng");
		return "redirect:/admin/product/index"; // redirect l???i ????? m???t d??? li???u tr??n form
	}

	@RequestMapping("update")
	public String update(Model model, RedirectAttributes params, @Validated Product form, BindingResult errors,
			@RequestParam("image_file") MultipartFile file) {
		if (errors.hasErrors()) {
			model.addAttribute("message", "Vui l??ng kh??ng b??? tr???ng b??n d?????i");
			return "admin/product/index";
		}


		File photo = http.saveProductPhoto(file);
		if (photo != null) {
			form.setImage(photo.getName());
		}
		productService.update(form);

		params.addAttribute("message", "C???p nh???t th??nh c??ng");
		return "redirect:/admin/product/edit/" + form.getId(); // redirect gi??? l???i d??? li???u
	}

	@RequestMapping("delete/{id}")
	public String delete(Model model, RedirectAttributes params, @PathVariable("id") Integer id) {
		productService.delete(id);

		params.addAttribute("message", "X??a th??nh c??ng");
		return "redirect:/admin/product/index";
	}
}

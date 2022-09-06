package com.tttn.saleweb.controller.web;

import java.util.List;
import java.util.stream.Collectors;

import com.tttn.saleweb.entity.OrderDetail;
import com.tttn.saleweb.entity.Product;
import com.tttn.saleweb.service.IProductService;
import com.tttn.saleweb.service.IRabbitmqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.tttn.saleweb.entity.Order;
import com.tttn.saleweb.service.IOrderSevice;
import com.tttn.saleweb.service.impl.CartService;

@Controller
public class OrderController {

	@Autowired
	private CartService cartService;

	@Autowired
	private IOrderSevice orderService;

	@Autowired
	private IProductService productService;
	
	@Autowired
	private IRabbitmqService rabbit;

	@GetMapping("/order/checkout")
	public String checkOut(Model model,RedirectAttributes attributes) {
		if (cartService.getCountCart() == 0) {
			attributes.addFlashAttribute("message","Chưa có sản phẩm trong giỏ hàng");
			return "redirect:/cart/view";
		}
		model.addAttribute("cart", cartService);
		Order order = orderService.createOrder();
		model.addAttribute("order", order);
		return "order/checkout";
	}

	@PostMapping("/order/checkout")
	public String checkOut(Model model, @Validated @ModelAttribute("order") Order or) {
//		rabbit.converToSendRabbit(or, cartService);
		// Hoặc thêm thẳng xuống db
		orderService.addOrderAndOrderDetail(or, cartService);
		cartService.clear();
		return "redirect:/home/index";
	}

	@RequestMapping("/order/list")
	public String listOrder(Model model) {
		List<Order> list = orderService.getAllOrderByUser();
		model.addAttribute("orders", list);
		model.addAttribute("ordersWaiting",(List<Order>) list.stream().filter(item -> item.getStatus() == 1).collect(Collectors.toList()));
		model.addAttribute("ordersDelivery",
				(List<Order>) list.stream().filter(item -> item.getStatus() == 2).collect(Collectors.toList()));
		model.addAttribute("ordersDeliverted",
				(List<Order>) list.stream().filter(item -> item.getStatus() == 3).collect(Collectors.toList()));
		model.addAttribute("ordersCancel",
				(List<Order>) list.stream().filter(item -> item.getStatus() == 4).collect(Collectors.toList()));
		return "order/list";
	}

	@RequestMapping("/order/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Order order = orderService.findById(id);
		model.addAttribute("order", order);
		return "order/detail";
	}

	@RequestMapping("/order/items")
	public String getPurchasedItems(Model model) {
		model.addAttribute("list", orderService.getPurchasedItems().values());
		return "product/list";
	}

	@RequestMapping("/order/cancel/{id}")
	public String cancel(Model model, @PathVariable("id") Integer id) {
		Order order = orderService.findById(id);
		if(order.getStatus() == 1) {
			order.setStatus(4);
			orderService.update(order);
			for (OrderDetail orderDetail : order.getOrderDetails()
			) {
				Product product = orderDetail.getProduct();
				product.setQuantity(product.getQuantity() + orderDetail.getQuantity());
				productService.update(product);
			}
			model.addAttribute("order", order);
			model.addAttribute("message", "Huỷ đơn hàng thành công");
		}
		else {
			model.addAttribute("order", orderService.findById(id));
			model.addAttribute("message", "Huỷ đơn hàng không thành công");
		}
		return "order/detail";
	}
}

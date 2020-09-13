package vn.t3h.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@GetMapping(value = {"/", ""})
	public String productInfo() {
		return "pages/product";
	}
	
	@GetMapping(value = "/create")
	public String create() {
		return "pages/product.create";
	}
}

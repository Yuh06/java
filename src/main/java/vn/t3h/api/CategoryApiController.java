package vn.t3h.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.t3h.model.Category;
import vn.t3h.services.CategoryService;

@RestController
@RequestMapping("/api")
public class CategoryApiController {
	
	private @Autowired CategoryService categoryService;
	
	@GetMapping(value="/category")
	public List<Category> listCategorys() {
		return categoryService.categoryWithIndent();
	}
}
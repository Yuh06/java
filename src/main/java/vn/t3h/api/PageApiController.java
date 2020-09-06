package vn.t3h.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.t3h.dao.PagesDao;
import vn.t3h.model.Menu;
import vn.t3h.model.Pages;
import vn.t3h.services.MenuService;

@RestController
@RequestMapping("/api")
public class PageApiController {
	
	private @Autowired PagesDao pagesDao;
	
	@PostMapping(value="/pages")
	public List<Pages> listPages(@RequestBody Pages pages) {
		return pagesDao.filter(pages);
	}
	
	@GetMapping(value="/pages")
	public List<Pages> getPages() {
		var pages = new Pages();
		pages.setName("phone");
		pages.setCate(2);
		return pagesDao.filter(pages);
	}
}
package vn.t3h.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.t3h.dao.PagesDao;
import vn.t3h.model.Pages;

@Controller
@RequestMapping("/pages")
public class PagesController extends BaseController {
	
	Logger log = LoggerFactory.getLogger(PagesController.class);
	@Autowired PagesDao pagesDao;
	
	@GetMapping(value = "/create")
	public String createPage(Model model) {
		var page = new Pages();
		model.addAttribute("page", page);
		return "pages/page";
	}
	
	@PostMapping(value = "/create")
	public String savePage(@Valid @ModelAttribute(value="page") Pages page, 
			BindingResult bindingResult,  Model model) {
		String VIEW_FILE = "pages/page";
		log.info("page name: {}, title: {}", page.getName(), page.getTitle());
		if(bindingResult.hasErrors()) {
			return VIEW_FILE;
		}
		
		pagesDao.persist(page);
		return VIEW_FILE;
	}
}

package vn.t3h.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import vn.t3h.services.HomeService;

@Controller
public class HomeController extends BaseController {

	static final String VIEW_INDEX = "pages/index";
	
	@Autowired private HomeService homeService;
	@Autowired private HttpSession httpSession;

	@GetMapping(value = {"","/"})
	public String getHome(Model model) {
		model.addAttribute("str", homeService.hello());
		httpSession.setAttribute("TODAY", System.currentTimeMillis());
		return VIEW_INDEX;
	}
	
	@GetMapping(value = "/about")
	public String getAbout(Model model) {
		model.addAttribute("str", "Data About");
		return "pages/about";
	}
}
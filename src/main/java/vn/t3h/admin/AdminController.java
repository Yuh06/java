package vn.t3h.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.t3h.controller.BaseController;

@Controller
@RequestMapping("admin")
public class AdminController extends BaseController {

	@GetMapping(value = {"","/", "/index"})
	public String getHome(Model model) {
		return "admin/index";
	}
}
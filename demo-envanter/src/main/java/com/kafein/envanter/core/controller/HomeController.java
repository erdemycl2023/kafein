package com.kafein.envanter.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController {


	
	// root dashboard redirect
	@GetMapping("/")
	public RedirectView root() {
		return new RedirectView("/dashboard");
	}
	
	//dashboard get
	@GetMapping("/dashboard")
	public ModelAndView dashboard(Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("dashboard");
		
		return modelAndView;
	}
	

	
}
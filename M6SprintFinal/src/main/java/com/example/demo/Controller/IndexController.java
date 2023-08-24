package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	
	@GetMapping("/")
	public String index() {
		return "inicio";
	}
	
	 @GetMapping("/logout")
	    public String logout() {
	        return "redirect:/Login";
	    }
}

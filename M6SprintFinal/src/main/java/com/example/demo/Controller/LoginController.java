package com.example.demo.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

	@Controller
	public class LoginController {

		@GetMapping("/Login")
		public String login(HttpServletRequest request) {
			
			 String role = (String) request.getSession().getAttribute("role");

		        if ("cliente".equals(role)) {
		           System.out.println("tiene un rol de cliente");
		        } else if ("profesional".equals(role)) {
		        	 System.out.println("tiene un rol de profesional");
		        } else if ("administrativo".equals(role)) {
		        	 System.out.println("tiene un rol de administrativo");
		        } else {
		            // El usuario no tiene un rol reconocido o no está autenticado correctamente
		        }

			
			return "login";
		}
	}

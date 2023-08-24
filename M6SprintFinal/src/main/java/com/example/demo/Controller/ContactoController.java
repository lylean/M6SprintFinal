package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactoController {

	@GetMapping("/contactos")
	public String contacto() {
		return "contactos";
	}
}

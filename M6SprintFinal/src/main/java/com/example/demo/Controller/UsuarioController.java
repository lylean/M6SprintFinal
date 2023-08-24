package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.Models.Rol;
import com.example.demo.Models.Usuario;
import com.example.demo.Repository.RolRepository;
import com.example.demo.Service.UsuarioService;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private RolRepository rolRepository;
	
	@GetMapping("/registro")
	public String registroForm(Model model) {
		
		List<Rol> rolesList = rolRepository.findAll();
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("rolesList", rolesList);
		return "registro";
	}
	
	@PostMapping("/registro")
	public String registrarUsuario(@ModelAttribute Usuario usuario, @RequestParam("tipo") String tipo,RedirectAttributes redirectAttributes) {
	    // Guardar el usuario en la base de datos
		
		System.out.println("el valor de tipo es: " + tipo);
		usuario.setTipo(tipo);
	    usuarioService.guardarUsuario(usuario);

	    // Agregar un mensaje de éxito para mostrar en la vista de login
	    redirectAttributes.addFlashAttribute("registroExitoso", true);

	    return "redirect:/Login";
	}

}

package com.example.demo.Controller;

import java.time.LocalDate;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.Models.Usuario;
import com.example.demo.Models.Visita;
import com.example.demo.Service.UsuarioJpaCrudService;
import com.example.demo.Service.UsuarioService;
import com.example.demo.Service.VisitaCrudServicio;

@Controller
public class VisitaController {

	private final VisitaCrudServicio visitaCrudServicio;
	
	
	@Autowired
	public VisitaController(VisitaCrudServicio visitaCrudServicio) {
		this.visitaCrudServicio = visitaCrudServicio;
	}
	
	
	@GetMapping("/crearVisitas")
	public String crearVisitas(Model model) {
		Map<Long, String> nombresUsuarios = visitaCrudServicio.ObtenerNombreUsuarioParaVisita();
		model.addAttribute("nombresUsuarios", nombresUsuarios);
		return "CrearVisitas";
	}
	
	@PostMapping("/guardarvisita")
	public String guardarVisita( @RequestParam("fecha_visita") Date fechaVisita,
	        @RequestParam("usuario_id") Long idUsuario,
	        RedirectAttributes redirectAttributes) {
		
		  Visita nuevaVisita = new Visita();
		    nuevaVisita.setFecha_visita(fechaVisita);
		    nuevaVisita.setId_usuario(idUsuario);
		    nuevaVisita.setEstado("cobrar");
		
		 // Establecer la fecha de creación como la fecha actual
		    nuevaVisita.setFecha_creacion(new Date(System.currentTimeMillis())); // Cambiar esto
		    // Guardar la visita en la base de datos
		    visitaCrudServicio.SaveVisita(nuevaVisita);
		    redirectAttributes.addFlashAttribute("mensaje", "Visita guardada exitosamente");

		    return "redirect:/listarVisitas";
	}
	
	@GetMapping("/listarVisitas")
	public String listarVisitas(Model model) {
	    Iterable<Visita> visitas = visitaCrudServicio.listVisitasByEstado("cobrar");
	    model.addAttribute("visitas", visitas);
	    return "listaVisitas";
	}

	@GetMapping("/listaVisitasPagadas")
	public String listaVisitasPagadas(Model model) {
		
		 Iterable<Visita> visitas = visitaCrudServicio.listVisitasByEstado("pagado");
		    model.addAttribute("visitas", visitas);
		
		return "listaVisitasPagadas";
	}
		
}

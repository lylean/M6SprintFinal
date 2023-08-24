package com.example.demo.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Models.Pago;
import com.example.demo.Models.Usuario;
import com.example.demo.Models.Visita;
import com.example.demo.Service.PagoCrudServicio;
import com.example.demo.Service.UsuarioJpaCrudServiceImpl;
import com.example.demo.Service.VisitaCrudServicio;

@Controller
public class PagoController {

	private final VisitaCrudServicio visitaCrudServicio;
	private final PagoCrudServicio pagoCrudServicio;
	
	@Autowired
	public PagoController(VisitaCrudServicio visitaCrudServicio, PagoCrudServicio pagoCrudServicio) {
		this.visitaCrudServicio = visitaCrudServicio;
		this.pagoCrudServicio = pagoCrudServicio;
	}
	
	@GetMapping("/ProcesoPago")
	public String procesoPago(@RequestParam("idEditar") Integer id, Model model) {
		
		Visita visita = visitaCrudServicio.getVisitaById(id);
		Map<Long, String> nombresUsuarios = visitaCrudServicio.ObtenerNombreUsuarioParaVisita();
		model.addAttribute("visita", visita);
		model.addAttribute("nombresUsuarios", nombresUsuarios);
		
		return "/PagoVisitas";
	}
	
	@PostMapping("/registrarPago")
	public String registrarPago(@RequestParam("nombre_usuario") String nombre_usuario, @RequestParam("id_visita") int id_visita) {
		
		Visita visita = visitaCrudServicio.getVisitaById(id_visita);
		Usuario usuario = visita.getUsuario();
		
		Pago pago = new Pago();
		pago.setId_visita(id_visita);
		pago.setId_usuario(usuario.getId());
		
		pagoCrudServicio.guardarPago(pago);
		
		
		visita.setEstado("pagado");
		visitaCrudServicio.SaveVisita(visita);
		
		return "redirect:/listarVisitas";
	}
	
	@GetMapping("/historialPagos")
	public String historialPagos(Model model){
		
		List<Pago> pagosConNombres = pagoCrudServicio.obtenerTodosLosPagosConNombres();
	    model.addAttribute("pagos", pagosConNombres);
		return "historialPagos";
	}
}
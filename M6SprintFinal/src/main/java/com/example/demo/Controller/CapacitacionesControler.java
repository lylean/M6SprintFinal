package com.example.demo.Controller;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.Models.Capacitaciones;
import com.example.demo.Service.CapacitacionesCrudServicio;

@Controller
public class CapacitacionesControler {
	
	/* Obtener una instancia del logger para la clase CrearCapacitacionesController*/
	 private static final Logger logger = LogManager.getLogger(CapacitacionesControler.class);
	
	 
	private final CapacitacionesCrudServicio capacitacionesCrudServicio;
	
	@Autowired
	public CapacitacionesControler(CapacitacionesCrudServicio capacitacionesCrudServicio) {
	this.capacitacionesCrudServicio = capacitacionesCrudServicio;
	}

	@GetMapping("/administrativo/listarCapacitaciones")
	public String listarCapacitaciones(Model model) {
		
		Iterable<Capacitaciones> capacitaciones = capacitacionesCrudServicio.listAllCapacitaciones();
		model.addAttribute("capacitaciones", capacitaciones);
		
		return "listaCapacitaciones";
	}
	
	@GetMapping("/crearcapacitaciones")
	public String crearCapacitaciones(Model model) {
		Map<Integer, String> nombresUsuarios = capacitacionesCrudServicio.ObtenerNombresUsuariosParaCapacitaciones();
        model.addAttribute("nombresUsuarios", nombresUsuarios);
		return "CrearCapacitaciones";
	}
	
	@PostMapping("/guardar-capacitacion")
    public String guardarCapacitacion(
            @RequestParam("usuario_id") Integer usuarioId,
            @RequestParam("validationCustom01") String nombre,
            @RequestParam("validationCustom04") String detalle,
            RedirectAttributes redirectAttributes) {

        Capacitaciones capacitacion = new Capacitaciones();
        capacitacion.setUsuario_id(usuarioId);
        capacitacion.setNombre(nombre);
        capacitacion.setDetalle(detalle);
        
        /* 3. Desplegar los datos de la capacitación usando System.out.println()*/
        System.out.println("Datos de la capacitación:");
        System.out.println("Usuario ID: " + capacitacion.getUsuario_id());
        System.out.println("Nombre: " + capacitacion.getNombre());
        System.out.println("Detalle: " + capacitacion.getDetalle());

        /* 4. Registrar los datos de la capacitación en el log de sistema*/
        logger.info("Datos de la capacitación - Usuario ID: " + capacitacion.getUsuario_id() +
        			", Nombre: " + capacitacion.getNombre() +
        			", Detalle: " + capacitacion.getDetalle());

       // capacitacionesServicio.saveCapacitacion(capacitacion);
        capacitacionesCrudServicio.saveCapacitacion(capacitacion);

        redirectAttributes.addFlashAttribute("mensaje", "Capacitación creada exitosamente");

        return "redirect:/administrativo/listarCapacitaciones";
    }
	
	@PostMapping("/eliminar-capacitacion")
    public String eliminarCapacitacion(@RequestParam("idEliminar") Integer id, RedirectAttributes redirectAttributes) {
        capacitacionesCrudServicio.eliminarCapacitacion(id);
        redirectAttributes.addFlashAttribute("mensaje", "Capacitación eliminada exitosamente");
        return "redirect:/administrativo/listarCapacitaciones";
    }
	
	@GetMapping("/editar-capacitacion")
    public String editarCapacitacion(@RequestParam("idEditar") Integer id, Model model) {
        Capacitaciones capacitacion = capacitacionesCrudServicio.getCapacitacionById(id);
        Map<Integer, String> nombresUsuarios = capacitacionesCrudServicio.ObtenerNombresUsuariosParaCapacitaciones();

        model.addAttribute("capacitacion", capacitacion);
        model.addAttribute("nombresUsuarios", nombresUsuarios);

        return "EditarCapacitacion";
    }

    @PostMapping("/guardar-edicion")
    public String guardarEdicionCapacitacion(
            @RequestParam("id") Integer id,
            @RequestParam("usuario_id") Integer usuarioId,
            @RequestParam("validationCustom01") String nombre,
            @RequestParam("validationCustom04") String detalle,
            RedirectAttributes redirectAttributes) {

        Capacitaciones capacitacion = capacitacionesCrudServicio.getCapacitacionById(id);
        capacitacion.setUsuario_id(usuarioId);
        capacitacion.setNombre(nombre);
        capacitacion.setDetalle(detalle);

        capacitacionesCrudServicio.saveCapacitacion(capacitacion);

        redirectAttributes.addFlashAttribute("mensaje", "Capacitación editada exitosamente");

        return "redirect:/administrativo/listarCapacitaciones";
    }
}

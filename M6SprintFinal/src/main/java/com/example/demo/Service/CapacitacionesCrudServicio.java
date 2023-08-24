package com.example.demo.Service;

import java.util.Map;



import com.example.demo.Models.Capacitaciones;

public interface CapacitacionesCrudServicio {
	Iterable<Capacitaciones> listAllCapacitaciones();
	Capacitaciones getCapacitacionById(Integer id);
	Capacitaciones saveCapacitacion(Capacitaciones capacitaciones);
	 void eliminarCapacitacion(Integer id);
	
	Map<Integer, String> ObtenerNombresUsuariosParaCapacitaciones();
	
	
}

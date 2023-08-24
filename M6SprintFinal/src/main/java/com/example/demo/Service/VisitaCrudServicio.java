package com.example.demo.Service;

import java.util.Map;

import com.example.demo.Models.Visita;

public interface VisitaCrudServicio {

	Iterable<Visita> listAllVisitas();
	Visita getVisitaById(Integer id);
	Visita SaveVisita(Visita visita);
	void EliminarVisita(Integer visita);
	
	 Iterable<Visita> listVisitasByEstado(String estado);
	
	Map<Long, String> ObtenerNombreUsuarioParaVisita();
}

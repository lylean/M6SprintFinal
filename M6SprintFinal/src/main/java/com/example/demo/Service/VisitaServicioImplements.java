package com.example.demo.Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.Usuario;
import com.example.demo.Models.Visita;
import com.example.demo.Repository.UsuarioRepository;
import com.example.demo.Repository.VisitaRepositorio;

@Service
public class VisitaServicioImplements implements VisitaCrudServicio{

	private VisitaRepositorio visitaRepositorio;
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	public void setVisitaRepositorio(VisitaRepositorio visitaRepositorio) {
		this.visitaRepositorio = visitaRepositorio;
	}
	
	@Autowired 
	public void setUsuariosRepositorio(UsuarioRepository usuarioRepository){
		this.usuarioRepository = usuarioRepository;
	}
	
	@Override
	public Iterable<Visita> listAllVisitas() {
		return visitaRepositorio.findAll();
	}
	
	@Override
	public Iterable<Visita> listVisitasByEstado(String estado) {
	    return visitaRepositorio.findByEstado(estado);
	}

	@Override
	public Visita getVisitaById(Integer id) {
		return visitaRepositorio.findById(id).orElse(null);
	}

	@Override
	public Visita SaveVisita(Visita visita) {
		return visitaRepositorio.save(visita);
	}

	@Override
	public void EliminarVisita(Integer visita) {
		visitaRepositorio.deleteById(visita);
	}
	
	@Override
	public Map<Long, String> ObtenerNombreUsuarioParaVisita() {
		Map<Long, String> nombresMap = new HashMap<>();
		Iterable<Usuario> usuarios = usuarioRepository.findAll();
		
		for(Usuario usuario : usuarios) {
			nombresMap.put(usuario.getId(), usuario.getNombre());
		}
		
		
		return nombresMap;
	}

}

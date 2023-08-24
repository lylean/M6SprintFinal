package com.example.demo.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Models.Visita;

public interface VisitaRepositorio extends CrudRepository<Visita, Integer>{
	
	Iterable<Visita> findByEstado(String estado);
}

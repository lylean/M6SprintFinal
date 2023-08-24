package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Models.Rol;



public interface RolRepository extends JpaRepository<Rol, Long>{

	Rol findByNombre(String nombre);
}
	
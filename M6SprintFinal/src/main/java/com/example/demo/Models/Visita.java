package com.example.demo.Models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="visita")
public class Visita {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Date fecha_creacion;
	private Date fecha_visita;
	private Long Id_usuario;
	private String estado;
	
	
	@ManyToOne
	@JoinColumn(name = "Id_usuario", referencedColumnName = "id", insertable = false, updatable = false)
	private Usuario usuario;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Date getFecha_creacion() {
		return fecha_creacion;
	}


	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}


	public Date getFecha_visita() {
		return fecha_visita;
	}


	public void setFecha_visita(Date fecha_visita) {
		this.fecha_visita = fecha_visita;
	}


	public Long getId_usuario() {
		return Id_usuario;
	}


	public void setId_usuario(Long id_usuario) {
		Id_usuario = id_usuario;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
}

package com.example.demo.Models;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pago")
public class Pago {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer id_visita;
	private Long id_usuario;
	
	@ManyToOne
	@JoinColumn(name= "id_usuario", referencedColumnName = "id", insertable = false, updatable = false)
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="id_visita", referencedColumnName = "id", insertable= false, updatable = false)
	private Visita visita;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Visita getVisita() {
		return visita;
	}

	public void setVisita(Visita visita) {
		this.visita = visita;
	}

	public Integer getId_visita() {
		return id_visita;
	}

	public void setId_visita(Integer id_visita) {
		this.id_visita = id_visita;
	}

	public Long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}
	
	
}
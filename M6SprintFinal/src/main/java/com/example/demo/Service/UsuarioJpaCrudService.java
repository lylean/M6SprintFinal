package com.example.demo.Service;

import com.example.demo.Models.Usuario;

public interface UsuarioJpaCrudService {
	Iterable<Usuario> listAllUsuarios();
	Usuario getUsuariosById(Long id);
	Usuario saveUsuarios(Usuario usuario);
}

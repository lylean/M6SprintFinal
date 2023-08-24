package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.Usuario;
import com.example.demo.Repository.UsuarioRepository;

@Service
public class UsuarioJpaCrudServiceImpl implements UsuarioJpaCrudService {
	  
	private final UsuarioRepository usuarioRepository;

	    @Autowired
	    public UsuarioJpaCrudServiceImpl(UsuarioRepository usuarioRepository) {
	        this.usuarioRepository = usuarioRepository;
	    }

		@Override
		public Iterable<Usuario> listAllUsuarios() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Usuario getUsuariosById(Long id) {
			return usuarioRepository.findById(id).orElse(null);
		}

		@Override
		public Usuario saveUsuarios(Usuario usuario) {
			// TODO Auto-generated method stub
			return null;
		}
}
package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Models.Usuario;
import com.example.demo.Repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Bean
    public PasswordEncoder userPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
		Usuario usuario = usuarioRepository.findByEmail(email);
		
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		 return User.builder()
	                .username(usuario.getEmail()) // Establecer el nombre de usuario (email en este caso)
	                .password(usuario.getPassword()) // Establecer la contraseña
	                .roles(usuario.getTipo()) // Establecer roles si es necesario
	                .build();
	}
	
	public Usuario guardarUsuario(Usuario usuario) {
		usuario.setPassword(userPasswordEncoder().encode(usuario.getPassword()));
		return usuarioRepository.save(usuario);
	}
    
}

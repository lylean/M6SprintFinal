package com.example.demo.ConfigSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.demo.Service.UsuarioService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/registro").permitAll()
                .antMatchers("/cliente/**").hasRole("cliente")
                .antMatchers("/profesional/**").hasRole("profesional")
                .antMatchers("/administrativo/**").hasRole("administrativo")
                .antMatchers("/videos/BongoCat.mp4", "/imagenes/clickme.jpg", "/Style.css", "/modoOscuro.js").permitAll()
                .antMatchers("/Cat2.css").permitAll()       
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/Login")
                .successHandler(successHandler())// Configurar el manejador de éxito de autenticación
                .permitAll()
                .and()
            .logout()
                .permitAll()
                .logoutSuccessUrl("/Login"); // Redirigir a la página de inicio de sesión después de cerrar sesión
    }
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Bean 
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public AuthenticationSuccessHandler successHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(usuarioService).passwordEncoder(passwordEncoder());
	}
}

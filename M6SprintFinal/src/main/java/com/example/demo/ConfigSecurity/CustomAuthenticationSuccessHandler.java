package com.example.demo.ConfigSecurity;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) 
    		throws IOException, ServletException {

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		authorities.forEach(authority ->{
			if(authority.getAuthority().equals("cliente")) {
				request.getSession().setAttribute("role", "cliente");
			}else if(authority.getAuthority().equals("administrativo")) {
				request.getSession().setAttribute("role", "administrativo");
			}else if(authority.getAuthority().equals("profesional")) {
				request.getSession().setAttribute("role", "profesional");
			}
		});
		
		super.onAuthenticationSuccess(request, response, authentication);
	}
}

package com.example.demo.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.service.UsuarioServiceApi;

//Filtro - Intercepta requisições e executa alguma operação
public class JwtAuthFilter extends OncePerRequestFilter {

	private JwtService jwtService;
	private UsuarioServiceApi usuarioServiceApi;
	
	public JwtAuthFilter(JwtService jwtService, UsuarioServiceApi usuarioServiceApi) {
		super();
		this.jwtService = jwtService;
		this.usuarioServiceApi = usuarioServiceApi;
	}

	//Este método define o que vai ser feito na interceptação do filtro
	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain)
			throws ServletException, IOException {
		
		//Pega do cabeçalho o valor Authorization que contém o token
		String authorization = request.getHeader("Authorization");
		String tokenCookie = "";
		for ( int i=0; request.getCookies() != null && i<request.getCookies().length; i++) {
	      Cookie cookie = request.getCookies()[i];
	      if (cookie.getName().equals("TOKEN"))
	        tokenCookie = cookie.getValue();
	    }
		//Nosso token sempre será passado com o Bearer na frente
		//if(authorization != null && authorization.startsWith("Bearer")) {
		if(authorization != null && authorization.startsWith("Bearer") || (!tokenCookie.equals(""))) {
			String tokenBearer = authorization.split(" ")[1];
			Boolean tokenValido = jwtService.tokenValido(tokenCookie) || jwtService.tokenValido(tokenBearer);
			
			if(tokenValido) {
				String loginUsuario = jwtService.obterLoginUsuario(tokenBearer);
				UserDetails userDetails =  usuarioServiceApi.loadUserByUsername(loginUsuario);
				//Crio a estrutura de Usuário para ser salvo no contexto do Spring Framework
				UsernamePasswordAuthenticationToken userAuth =
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			
				userAuth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				//Define na estrutura do springBoot o usuário logado
				SecurityContextHolder.getContext().setAuthentication(userAuth);
			}
			
		}
		//Após o filtro realizar o que deseja ele libera a requisição
		filterChain.doFilter(request, response);
	}

}

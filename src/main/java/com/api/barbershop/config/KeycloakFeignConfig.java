package com.api.barbershop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import feign.RequestInterceptor;

@Configuration
public class KeycloakFeignConfig {

	@Bean
	RequestInterceptor keycloakAuthInterceptor() {
		return template -> {
			var authentication = SecurityContextHolder.getContext().getAuthentication();
			if (authentication instanceof JwtAuthenticationToken jwtAuth) {
				Jwt jwt = jwtAuth.getToken();
				template.header("Authorization", "Bearer " + jwt.getTokenValue());
			} else {
				throw new IllegalStateException("Token JWT não encontrado no contexto de segurança");
			}
		};
	}
}

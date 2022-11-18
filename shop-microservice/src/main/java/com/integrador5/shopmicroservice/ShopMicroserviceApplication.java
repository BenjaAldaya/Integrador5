package com.integrador5.shopmicroservice;

import com.integrador5.shopmicroservice.security.JWTAuthorizationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
public class ShopMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopMicroserviceApplication.class, args);
	}

	@EnableWebSecurity
	@Configuration
	@EnableGlobalMethodSecurity(prePostEnabled = true)
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {
		public static final String SWAGGER_DOCUMENTATION = "/swagger-ui/index.html";
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			//desactiv el metodo por defecto
			http.csrf().disable()
					//agrega el metodo de filtrado q codificamos nosotros
					.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
					.authorizeRequests()
					.antMatchers(HttpMethod.POST, "/user").permitAll()
					.antMatchers("/api/clients/**").authenticated()
					.antMatchers("/api/products/**").authenticated()
					.antMatchers("/api/purchases/**").authenticated()
					.antMatchers(HttpMethod.GET, SWAGGER_DOCUMENTATION).permitAll()
					.anyRequest().permitAll();
		//			.anyRequest().authenticated();

		}
	}
}

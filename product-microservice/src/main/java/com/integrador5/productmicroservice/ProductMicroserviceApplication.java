package com.integrador5.productmicroservice;

import com.integrador5.productmicroservice.security.JWTAuthorizationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
public class ProductMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductMicroserviceApplication.class, args);
	}

	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			//desactiv el metodo por defecto
			http.csrf().disable()
					//agrega el metodo de filtrado q codificamos nosotros
					.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
					.authorizeRequests()
					.antMatchers(HttpMethod.POST, "/user").permitAll()
					.antMatchers(HttpMethod.POST, "/api/products").authenticated()
					.antMatchers(HttpMethod.DELETE, "/api/products").authenticated()
					.antMatchers(HttpMethod.PUT, "/api/products").authenticated()
					//.antMatchers("/api/products/**").authenticated()
					.anyRequest().permitAll();
		}
	}

}

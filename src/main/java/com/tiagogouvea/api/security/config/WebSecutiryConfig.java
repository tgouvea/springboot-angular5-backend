package com.tiagogouvea.api.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.tiagogouvea.api.security.JwtAutenticacaoEntryPoint;
import com.tiagogouvea.api.security.JwtTokenFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecutiryConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAutenticacaoEntryPoint naoAutorizadoHandler;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(senhaEncoder());
	}
	
	@Bean
	public PasswordEncoder senhaEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public JwtTokenFilter jwtTokenFilterBean() throws Exception {
		return new JwtTokenFilter();
	}
	
	@Override
	protected void configure(HttpSecurity httpSecutiry) throws Exception {

		httpSecutiry.csrf().disable()
			.exceptionHandling().authenticationEntryPoint(naoAutorizadoHandler)
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests()
			.antMatchers(
					HttpMethod.GET,
					"/",
					"/*.html",
					"/favicon.ico",
					"/**/*.css",
					"/**/*.js"
				)
			.permitAll()
			.antMatchers("/api/auth/**").permitAll()
			.antMatchers(HttpMethod.POST, "/api/usuarios").permitAll()
			.anyRequest().authenticated();
		
		httpSecutiry.addFilterBefore(jwtTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
		httpSecutiry.headers().cacheControl();
		
	}
}

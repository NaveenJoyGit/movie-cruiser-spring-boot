package com.moviecruiser.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.moviecruiser.dao.JwtFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;

	
//	@Autowired
//	private JwtFilter jwtFilter;
//	
//	@Bean
//	public JwtFilter jwtProvider() {
//		JwtFilter jf = null;
//		try {
//			jf = new JwtFilter(authenticationManager());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return jf;
//	}
	
//	@Bean
//	public AuthenticationProvider authProvider() {
//		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//		provider.setUserDetailsService(userDetailsService);
//		provider.setPasswordEncoder(new BCryptPasswordEncoder());
//		return provider;
//	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/movie-list").hasRole("admin")
		.antMatchers("/favorites").hasRole("admin")
		.antMatchers("/add-favorite/**").hasRole("admin")
		.antMatchers("/edit/**").hasRole("admin")
		.antMatchers("/favorites-delete/**").hasRole("admin")
		.and().formLogin();
		http.addFilter(new JwtFilter(authenticationManager()));
		
		http.cors().disable();
	}

	/*
	 * http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/authenticate").permitAll().anyRequest().authenticated();
		http.addFilter(new JwtFilter(authenticationManager()));
	 */
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	//Authentication
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth
//			.inMemoryAuthentication()
//			.withUser("Naveen")
//			.password("password@123")
//			.roles("USER", "ADMIN");
//	}
	
    //Authorization
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//			.csrf().disable()
//			.authorizeRequests()
//			.antMatchers("/m").hasAnyRole("ADMIN")
//			.anyRequest().hasAnyRole("USER");
//	}
}

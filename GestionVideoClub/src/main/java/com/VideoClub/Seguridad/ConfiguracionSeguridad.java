package com.VideoClub.Seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad  extends WebSecurityConfigurerAdapter {

	@Autowired
	private Validacion validacion;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		DaoAuthenticationProvider provedorIdentificacion = new DaoAuthenticationProvider();
		provedorIdentificacion.setPasswordEncoder(new BCryptPasswordEncoder());
		provedorIdentificacion.setUserDetailsService(validacion);
		auth.authenticationProvider(provedorIdentificacion);
	}

	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	    	http
	        .authorizeRequests()
	        .antMatchers("/**").authenticated()
	        	.antMatchers("/verJuegos").permitAll()
	        	//.antMatchers("/mensajes/**").authenticated()
	        	.antMatchers("/verUsuarios/**").hasAuthority("admin")
	        	.antMatchers("/agregarUsuario/**").hasAuthority("admin")
	        	.antMatchers("/agregarJuego/**").hasAuthority("admin")
		        .and()    	
	        .formLogin()
	            .loginPage("/login").permitAll()
	            .defaultSuccessUrl("/")
	            .failureUrl("/login?error=true")
	            .usernameParameter("username")
	            .passwordParameter("password")
	            .and()
	        .logout()
	        	.permitAll()
	            .logoutUrl("/logout")
	            .logoutSuccessUrl("/login")
	            .and()
	        .csrf().disable();

	    }
    
   
}

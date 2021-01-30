package de.viadee.spring.boot.security.example.baseauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class BaseAuthConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * White-List Ansatz.
		 * 
		 * Alle Endpunkte schützen, die nicht explizit inder White-List eingetragen sind
		 * 
		 * Hier: Nur die Endpunkte / und /public/** sind öffentlich
		 * 
		 */		
		http.httpBasic().and()
        .authorizeRequests()
            .antMatchers("/", "/public/**").permitAll() //White-list --> nicht geschützte Endpunkte
            .anyRequest().authenticated().and();
       
		http.csrf().ignoringAntMatchers("/actuator/shutdown"); //Disable CSRF for actuator/shutdown endpoint
		
		/*
		 * Black-List Ansatz:
		 * Es werden alle Endpunkte als öffentlich eingetragen, außer sie befinden sich in der Black-List
		 * 
		 * Alle Endpunkte außer diejenigen in /private/** sind öffentlich
		 */
		
		/*
		http.httpBasic().and()
        	.authorizeRequests()
            .antMatchers("/private/**").authenticated()
            .antMatchers("/**").permitAll();
		
		*/
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
	 	.inMemoryAuthentication()
	 		.withUser("user").password(passwordEncoder().encode("geheim")).roles("USER").and()
	 		.withUser("admin").password(passwordEncoder().encode("geheim")).roles("USER", "ADMIN");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
}

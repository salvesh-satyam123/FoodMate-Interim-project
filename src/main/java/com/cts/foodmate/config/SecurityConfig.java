package com.cts.foodmate.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.cts.foodmate.filter.JwtAuthFilter;
import com.cts.foodmate.service.UserInfoService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private JwtAuthFilter authFilter;
	
	// User Creation
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserInfoService();
	}

	// Configuring HttpSecurity
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(
				(requests) -> requests.requestMatchers("/auth/addNewUser", "/auth/userdetails", "/auth/generateToken",
						"/auth/loadDummyData/**","/auth/exception/redirect")
				.permitAll()
				.requestMatchers("/product-rest/admin/**","/order-rest/admin/**").hasRole("ADMIN")
				.requestMatchers("/address-rest/**","/cart-rest/**","/auth/user/profile,/product-rest/user/**","/order-rest/user/**").hasAnyRole("USER","ADMIN")
				.anyRequest().authenticated())
		
				.cors(cors -> cors.configurationSource(corsConfigurationSource()))
				.authenticationProvider(authenticationProvider())
 				.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
				.csrf(csrf -> csrf.disable());
				
	
		
		return http.build();

	}
	
	/*
	 * .requestMatchers("/auth/addNewUser", "/auth/userdetails", "/auth/generateToken",
								"/auth/loadDummyData/**","/auth/exception/redirect")
						.permitAll().requestMatchers("/auth/user/Profile","/product-rest/**").authenticated())
	 */
	
	

	// Password Encoding
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public UrlBasedCorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // Allow requests from this origin
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Allowed HTTP
																									// methods
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type")); // Allowed
																											// headers
		configuration.setAllowCredentials(true); // Allow credentials like cookies, authorization headers

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration); // Apply this CORS configuration to all endpoints

		return source;
	}

}

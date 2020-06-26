package api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	@Autowired
	CustomPasswordEncoder passwordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);
	}

	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.POST, "/users/login");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.cors()
				.and()
					.csrf()
						.disable()
						.authorizeRequests()
						.anyRequest()
						.authenticated()
				.and()
					.addFilterBefore(new AuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
		
//		.authorizeRequests()
//		.antMatchers("/", "/home").permitAll()
//		.anyRequest().authenticated()
//		.and()
//	.formLogin()
//		.loginPage("/login")
//		.permitAll()
//		.and()
//	.logout()
//		.permitAll()		
		
		
//		http
//		.authorizeRequests()
//			.antMatchers("/", "/home").permitAll()
//			.anyRequest().authenticated()
//			.and()
//		.formLogin()
//			.loginPage("/login")
//			.permitAll()
//			.and()
//		.logout()
//			.permitAll();		
//			.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
			//.configurationSource(corsConfigurationSource()).and()
//			.csrf().disable().authorizeRequests()
//			.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
//			.anyRequest().authenticated()
			
//			.and().formLogin()
//	          .loginPage("/login.html")
//	          .loginProcessingUrl("/users/login")
//	          .defaultSuccessUrl("/", true)
//	          //.failureUrl("/login.html?error=true")
//	          //.failureHandler(authenticationFailureHandler())
//	          .and()
//	          .logout()
//	          .logoutUrl("/perform_logout")
//	          .deleteCookies("JSESSIONID")
	          //.logoutSuccessHandler(logoutSuccessHandler());

	          //.and().addFilterBefore(new AuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}


	
//	@Bean
//	CorsConfigurationSource corsConfigurationSource() {
//	        CorsConfiguration configuration = new CorsConfiguration();
//	        configuration.setAllowedOrigins(Arrays.asList("*"));
//	        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE", "PUT", "PATCH"));
//	        configuration.setAllowedHeaders(Arrays.asList("X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization"));
//	        configuration.setAllowCredentials(true);
//	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	        source.registerCorsConfiguration("/**", configuration);
//	        return source;
//	    }	
	
//	@Bean
//	CorsConfigurationSource corsConfigurationSource() {
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
//		return source;
//	}
//	@Bean
//	CorsConfigurationSource corsConfigurationSource() {
//		CorsConfiguration configuration = new CorsConfiguration();
//		configuration.setAllowedOrigins(Arrays.asList("/**", "http://www.dev.local:8080",
//				"http://www.dev.local:8080/users/login", "http://www.dev.local/login.html","http://www.dev.local"));
//		configuration.setAllowedMethods(Arrays.asList("OPTION", "GET", "POST", "PUT"));
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", configuration);
//		return source;
//	}

}

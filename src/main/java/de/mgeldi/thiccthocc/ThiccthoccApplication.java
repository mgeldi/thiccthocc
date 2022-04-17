package de.mgeldi.thiccthocc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
public class ThiccthoccApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThiccthoccApplication.class, args);
	}

//	@Configuration
//	protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
//			http
//					.httpBasic()
//					.and()
//					.authorizeRequests()
//					.antMatchers("/index.html", "/", "/home", "/login").permitAll()
//					.anyRequest().authenticated();
//		}
//	}
}

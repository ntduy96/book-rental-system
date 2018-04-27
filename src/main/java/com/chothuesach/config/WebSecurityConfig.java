package com.chothuesach.config;

import com.chothuesach.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private MySimpleUrlAuthenticationSuccessHandler mySimpleUrlAuthenticationSuccessHandler;
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider authProvider
	      = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userDetailsService);
	    authProvider.setPasswordEncoder(encoder());
	    return authProvider;
	}
	 
	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/signup");
    }

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .antMatchers("/", "/home", "/signup", "/authority", "/user", "/resource/**", "/login**", "/html/**").permitAll()
                .antMatchers("/api/sach/**").permitAll()
				.antMatchers("/manage").hasAuthority("ROLE_ADMIN")
				.anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
				.successHandler(mySimpleUrlAuthenticationSuccessHandler)
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403")
                .and()
            .logout()
                .permitAll()
            .and().csrf().disable();
    }
	
}

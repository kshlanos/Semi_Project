package com.semi.project.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.semi.project.login.service.AuthenticationService;


/* 시큐리티 설정 활성화 및 bean 등록 가능 */
@EnableWebSecurity
public class SpringSecurityConfiguration {
	
	private final AuthenticationService authenticationService;
	
	@Autowired
	public SpringSecurityConfiguration (AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	/* 1. 비밀번호 암호화에 사용할 객체 BCryptPasswordEncoder bean 등록 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/* 2. 정적 리소스는 권한이 없어도 요청이 가능하게끔 무시할 경로를 작성한다. */
	@Bean
	public WebSecurityCustomizer configure() {
		return (web) -> web.ignoring().antMatchers("/css/**", "/js/**", "/images/**");
	}
	
	/* 3. HTTP 요청에 대한 설정 */
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
        return http
        		.csrf().disable()
                .authorizeRequests()
                .mvcMatchers("/member/update", "/member/delete", "/board/**").hasAnyAuthority("ROLE_MEMBER", "ROLE_ADMIN")
                .mvcMatchers("/**", "/login/**").permitAll()
                .and()
                    .formLogin()
                    .loginPage("/login/login")             
                    .defaultSuccessUrl("/main")  
                    .failureForwardUrl("/login/loginfail")
                    .usernameParameter("memberId")			// 아이디 파라미터명 설정
                    .passwordParameter("memberPwd")			// 패스워드 파라미터명 설정
                .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/login/logout"))
                    .deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true)
                    .logoutSuccessUrl("/")
    			.and()
    				.build();
    }

	/* 4. 사용자 인증을 위해서 사용할 Service bean 등록, 사용할 비밀번호 인코딩 방식 설정 */
	@Bean
	public AuthenticationManager authManager(HttpSecurity http) throws Exception {
		
		return http
				.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(authenticationService)
				.passwordEncoder(passwordEncoder())
				.and()
				.build();
	}
}

package com.example.demo.config;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.method.configuration.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.*;
import org.springframework.security.web.access.expression.*;

import jakarta.annotation.*;
import jakarta.servlet.*;
import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.regions.*;
import software.amazon.awssdk.services.s3.*;

@Configuration
@EnableMethodSecurity
public class CustomConfiguration {

	@Value("${aws.accessKeyId}")
	private String accessKeyId;
	@Value("${aws.secretAccessKey}")
	private String secretAccessKey;
	
	@Autowired
	private ServletContext application;
	
	@Value("${aws.bucketUrl}")
	private String bucketUrl;
	
	@PostConstruct
	public void init() {
		application.setAttribute("bucketUrl",bucketUrl);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain seFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
//		http.formLogin(Customizer.withDefaults());
		//일단 기본을 보여줌
		
		http.formLogin().loginPage("/member/login");
		//내가 직접 로그인페이지를 명시함
		//이경로로 일하는 컨트롤러가 있어야 할 것이다
		http.logout().logoutUrl("/member/logout");
		
//		http.authorizeHttpRequests().requestMatchers("/add").authenticated();
		// add페이지에는 인증된 사용자만 가도록 하겠음
//		http.authorizeHttpRequests().requestMatchers("/member/signup").anonymous();
		// 회원가입 페이지는 로그인하지 않은 사용자만 들어오도록 해!
//		http.authorizeHttpRequests().requestMatchers("/**").permitAll();
		// 다른 곳은 인증되지 않아도 들어갈 수 있도록 하겠음
		
//		http.authorizeHttpRequests()
//			.requestMatchers("/add")
//			.access(new WebExpressionAuthorizationManager("isAuthenticated()"));
//		http.authorizeHttpRequests()
//			.requestMatchers("/member/signup")
//			.access(new WebExpressionAuthorizationManager("isAnonymous()"));
//		http.authorizeHttpRequests()
//			.requestMatchers("/**")
//			.access(new WebExpressionAuthorizationManager("permitAll"));
		//복잡한 식을 사용하고 싶을 때 expression을 사용할 수 있다
		//여러 경로에 접근제한을 걸면 코드가 길어진다
		
		
		
		return http.build();
	}
	
	@Bean
	public S3Client s3client() {
		AwsBasicCredentials credentials
		= AwsBasicCredentials.create(accessKeyId, secretAccessKey);
		AwsCredentialsProvider provider = StaticCredentialsProvider.create(credentials);
		
		S3Client s3client = S3Client.builder()
				.credentialsProvider(provider)
				.region(Region.AP_NORTHEAST_2)
				.build();
		
		return s3client;
	}
}

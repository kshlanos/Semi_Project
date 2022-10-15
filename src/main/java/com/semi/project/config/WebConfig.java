package com.semi.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //web과 관련한 설정을 위해 만든 WebConfig 클래스
public class WebConfig implements WebMvcConfigurer {
	
	/* WebMvcConfigurer : WebMvc와 관련 된 설정을 할 수 있는 메소드가 default 메소드로 선언 되어 있는 인터페이스
	 * addResourceHandlers : 정적 리소스와 관련하여 static 경로는 기본적으로 설정 되어 있으므로 추가적인 정적 리소스 경로를 설정(ex.그 외 디렉토리를 이용하고 싶을때) */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
			/* upload 경로를 추가하여 업로드 된 이미지를 정적 리소스로 요청할 수 있게 한다. */
		registry.addResourceHandler("/upload/**") //업로드 로 되는 경로로 요청이 온다면 그쪽으로 연결하겠단 뜻 / 업로드라는 시작값으로 요청이 왔을때 업로드 밑에 있는 애들 연결해라
				.addResourceLocations("classpath:/upload/");
	}
	
	
}


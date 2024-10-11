package com.jureureuk.jureureuk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class JureureukApplication {

	public static void main(String[] args) {
		SpringApplication.run(JureureukApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer configurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addViewControllers(ViewControllerRegistry registry) {
				// 메인 페이지
				registry.addViewController("/").setViewName("forward:/UI/html/beforeLogin.html");

				// 기존에 있던 페이지들
				registry.addViewController("/bookmark/main").setViewName("forward:/UI/html/bookmark/main.html");
				registry.addViewController("/cocktail/main").setViewName("forward:/UI/html/cocktail/main.html");
				registry.addViewController("/more/popularMore").setViewName("forward:/UI/html/more/popularMore.html");
				registry.addViewController("/more/randomMore").setViewName("forward:/UI/html/more/randomMore.html");
				registry.addViewController("/profile/main").setViewName("forward:/UI/html/profile/main.html");
				registry.addViewController("/refrigerator/main").setViewName("forward:/UI/html/refrigerator/main.html");
				registry.addViewController("/refrigerator/MaterialInfo")
						.setViewName("forward:/UI/html/refrigerator/MaterialInfo.html");
				registry.addViewController("/refrigerator/materialManagement")
						.setViewName("forward:/UI/html/refrigerator/materialManagement.html");
				registry.addViewController("/refrigerator/shopping")
						.setViewName("forward:/UI/html/refrigerator/shopping.html");
				registry.addViewController("/sharing/main").setViewName("forward:/UI/html/sharing/main.html");
				registry.addViewController("/sharing/write").setViewName("forward:/UI/html/sharing/write.html");
				registry.addViewController("/beforeLogin").setViewName("forward:/UI/html/beforeLogin.html");
				registry.addViewController("/mainPage").setViewName("forward:/UI/html/mainPage.html");
				registry.addViewController("/nickname").setViewName("forward:/UI/html/nickname.html");
			}
		};
	}

}

package sufyan.imagediscern.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/image/**")
	           	.allowedOrigins("http://localhost:3000")
	           	.allowedMethods("GET", "POST")
	           	.allowedHeaders("*")
	           	.allowCredentials(false).maxAge(3600);
	}
}
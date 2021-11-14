package progi.dugonogiprogi.radnovrijeme.backend.rest;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@EnableWebMvc
@Configuration
public class MvcConfig {

    public void addResourceHandlers(
            ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("/build/static/");
        registry.addResourceHandler("/*.js")
                .addResourceLocations("/build/");
        registry.addResourceHandler("/*.json")
                .addResourceLocations("/build/");
        registry.addResourceHandler("/*.ico")
                .addResourceLocations("/build/");
        registry.addResourceHandler("/login.html")
                .addResourceLocations("/build/login.html");
        registry.addResourceHandler("/jobs.html/")
                .addResourceLocations("/build/jobs.html");
    }
}

package ro.siit.airports;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path imageUploadDir = Paths.get("./user-images");
        String imageUploadPath = imageUploadDir.toFile().getAbsolutePath();
        registry.addResourceHandler("/user-images/**").addResourceLocations("file:/" + imageUploadPath + "/");
    }
}

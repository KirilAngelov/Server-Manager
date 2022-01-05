package com.example.servernew;

import com.example.servernew.enumeration.Status;
import com.example.servernew.model.Server;
import com.example.servernew.repo.ServerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@SpringBootApplication
public class ServerNewApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerNewApplication.class, args);
    }


    @Bean
    CommandLineRunner run(ServerRepo serveRepo) {
        return args -> {
            serveRepo.save(new Server(null, "192.168.1.160", "Linux", "64 GB", "PC",
                    "http://localhost:8080/server/image/server1.png", Status.SERVER_UP));
            serveRepo.save(new Server(null, "95.43.99.244", "Windows", "512 GB", "PC",
                    "http://localhost:8080/server/image/server2.png", Status.SERVER_UP));
        };
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:4200"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin","Access-Control-Allow-Origin","Content-Type",
                "Accept","Jwt-Token","Authorization","Origin","Accept","X-Requested-Width",
                "Access-Control-Request-Method","Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type","Accept","Jwt-Token","Authorization",
                "Access-Control-Allow-Origin","Access-Control-Allow-Origin","Access-Control-Allow-Credentials", "Filename"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT","PATCH","DELETE","OPTIONS"));
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);

    }

}

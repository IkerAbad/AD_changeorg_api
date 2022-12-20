package org.jesuitasrioja.ad_changeorg_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@SpringBootApplication
public class AdChangeorgApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdChangeorgApiApplication.class, args);
    }
}

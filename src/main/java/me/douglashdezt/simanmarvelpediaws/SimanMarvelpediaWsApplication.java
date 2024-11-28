package me.douglashdezt.simanmarvelpediaws;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;
import java.util.TimeZone;

@SpringBootApplication
@EnableFeignClients
public class SimanMarvelpediaWsApplication {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(5, new SecureRandom());
    }

    @Bean
    public ModelMapper getMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT-06:00"));
        SpringApplication.run(SimanMarvelpediaWsApplication.class, args);
    }

}

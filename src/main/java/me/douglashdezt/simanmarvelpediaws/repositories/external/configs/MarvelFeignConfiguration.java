package me.douglashdezt.simanmarvelpediaws.repositories.external.configs;

import feign.Logger;
import feign.RequestInterceptor;
import feign.Retryer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Slf4j
public class MarvelFeignConfiguration {
    @Value("${keys.marvel.public}")
    private String publicKey;
    @Value("${keys.marvel.private}")
    private String privateKey;

    @Bean
    public Retryer retryerCM() {
        return new Retryer.Default(100, 1000, 3); // Espera inicial de 100 ms, espera máxima de 1 segundo, 3 intentos
    }

    @Bean
    public Logger.Level feignLoggerLevelCM() {
        return Logger.Level.FULL;
    }

    @Bean
    public RequestInterceptor requestInterceptorCM() {
        return template -> {
                String ts = UUID.randomUUID().toString();
                String toDigest = ts+privateKey+publicKey;

                String hash = generateMD5(toDigest);

                template.query("ts", ts);
                template.query("hash", hash);
                template.query("apikey", publicKey);

//                log.info(template.toString());
        };
    }

    private static String generateMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));
            // Convert byte array to hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("Error generating MD5 hash", e);
        }
    }
}

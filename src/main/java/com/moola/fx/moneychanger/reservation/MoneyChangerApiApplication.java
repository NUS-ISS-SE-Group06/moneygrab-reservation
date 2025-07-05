package com.moola.fx.moneychanger.reservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication(scanBasePackages = "com.moola.fx.moneychanger.reservation")
public class MoneyChangerApiApplication {

    // Set default JVM timezone to Singapore at app startup
    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Singapore"));
    }

    public static void main(String[] args) {
        SpringApplication.run(MoneyChangerApiApplication.class, args);
    }
}
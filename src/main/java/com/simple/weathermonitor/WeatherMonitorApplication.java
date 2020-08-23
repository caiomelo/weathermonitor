package com.simple.weathermonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WeatherMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherMonitorApplication.class, args);
	}

}

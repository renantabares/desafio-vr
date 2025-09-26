package br.com.vr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients(basePackages = "br.com.vr.clients")
public class VrAuthorizationServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(VrAuthorizationServerApplication.class, args);
    }

}

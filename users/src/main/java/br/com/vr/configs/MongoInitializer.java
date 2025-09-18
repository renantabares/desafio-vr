package br.com.vr.configs;

import br.com.vr.models.Role;
import br.com.vr.models.User;
import br.com.vr.repositories.RoleRepository;
import br.com.vr.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MongoInitializer {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.count() == 0 && userRepository.count() == 0) {

                Role readRole = roleRepository.save(new Role("READ"));
                Role writeRole = roleRepository.save(new Role("WRITE"));

                User readAndWrite = User.builder()
                        .username("readAndWrite")
                        .password("readAndWrite123")
                        .roles(List.of(readRole,  writeRole))
                        .build();
                User read = User.builder()
                        .username("read")
                        .password("read123")
                        .roles(List.of(readRole))
                        .build();
                User write = User.builder()
                        .username("write")
                        .password("write123")
                        .roles(List.of(writeRole))
                        .build();

                userRepository.saveAll(List.of(readAndWrite, read, write));

                System.out.println("📌 Banco inicializado com Roles e Users!");
            } else {
                System.out.println("⚠️ Dados já existentes, não inicializado novamente.");
            }
        };
    }
}


package br.com.vr.feignClient;

import br.com.vr.dtos.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "users" , url = "http://localhost:8088/usuarios/")
public interface UserFeignClient {
    @GetMapping(value = "/{username}")
    ResponseEntity<UserDTO> getUser(@PathVariable String username);
}
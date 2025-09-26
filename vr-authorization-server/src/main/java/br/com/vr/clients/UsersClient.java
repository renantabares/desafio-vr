package br.com.vr.clients;

import br.com.vr.dtos.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "users", url = "http://localhost:8084/usuarios")
public interface UsersClient {

    @GetMapping(value = "/{username}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String username);

}

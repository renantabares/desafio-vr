package br.com.vr.services;

import br.com.vr.dtos.UserDTO;
import br.com.vr.feignClient.UserFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class UserService {
    @Autowired
    private UserFeignClient client;

    public UserDTO getUser (String  username) {
        return client.getUser(username).getBody();

    }
}

package br.com.vr.controller;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.interfaces.RSAPublicKey;
import java.util.Map;

@RestController
@AllArgsConstructor
public class JwksController {

    private final RSAPublicKey publicKey;

    @GetMapping("/.well-known/jwks.json")
    public Map<String, Object> keys() {
                RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .keyUse(KeyUse.SIGNATURE)
                .algorithm(JWSAlgorithm.RS256)
                .keyID("1")
                .build();

        return new JWKSet(rsaKey).toJSONObject();
    }

}

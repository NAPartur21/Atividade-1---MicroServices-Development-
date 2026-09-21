package com.playyourlist.api;

import com.playyourlist.musica.MusicaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Aponta para o próprio endpoint /musicas (mesmo projeto, conforme enunciado),
// mas a chamada é feita via HTTP/Open Feign como se fosse um microsserviço à parte.
@FeignClient(name = "musica-service", url = "${services.musica.url}")
public interface MusicaClient {

    @GetMapping("/musicas/{id}")
    MusicaResponse buscarPorId(@PathVariable("id") Long id);

}

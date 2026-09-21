package com.playyourlist.api;

import com.playyourlist.reproducao.ReproducaoRequest;
import com.playyourlist.reproducao.ReproducaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "reproducao-service", url = "${services.reproducao.url}")
public interface ReproducaoClient {

    @PostMapping("/reproducao")
    ReproducaoResponse registrar(@RequestBody ReproducaoRequest request);

    @GetMapping("/reproducao/total/{playlistid}")
    Long total(@PathVariable("playlistid") Long playlistId);

}

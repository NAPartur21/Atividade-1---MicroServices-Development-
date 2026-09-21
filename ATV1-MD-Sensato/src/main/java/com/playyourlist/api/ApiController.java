package com.playyourlist.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @PostMapping("/adicionar/{playlistId}/musicas/{musicaId}")
    public ResponseEntity<MensagemResponse> adicionar(@PathVariable Long playlistId, @PathVariable Long musicaId) {
        return ResponseEntity.ok(apiService.adicionarMusicaAPlaylist(playlistId, musicaId));
    }

    @PutMapping("/executar/{playlistId}")
    public ResponseEntity<MensagemResponse> executar(@PathVariable Long playlistId) {
        return ResponseEntity.ok(apiService.executarPlaylist(playlistId));
    }

}

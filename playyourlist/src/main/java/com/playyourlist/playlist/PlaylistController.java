package com.playyourlist.playlist;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @PostMapping
    public ResponseEntity<PlaylistResponse> criar(@Valid @RequestBody PlaylistRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playlistService.criar(request));
    }

    @GetMapping
    public ResponseEntity<List<PlaylistResponse>> listar() {
        return ResponseEntity.ok(playlistService.listar());
    }

    @GetMapping("/{playlistid}")
    public ResponseEntity<PlaylistResponse> buscarPorId(@PathVariable("playlistid") Long playlistId) {
        return ResponseEntity.ok(playlistService.buscarPorId(playlistId));
    }

    @PutMapping("/{playlistid}")
    public ResponseEntity<PlaylistResponse> atualizar(@PathVariable("playlistid") Long playlistId,
                                                        @Valid @RequestBody PlaylistRequest request) {
        return ResponseEntity.ok(playlistService.atualizar(playlistId, request));
    }

    @DeleteMapping("/{playlistid}")
    public ResponseEntity<Void> excluir(@PathVariable("playlistid") Long playlistId) {
        playlistService.excluir(playlistId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{playlistid}/musicas/{musicaId}")
    public ResponseEntity<Void> adicionarMusica(@PathVariable("playlistid") Long playlistId,
                                                  @PathVariable Long musicaId) {
        playlistService.adicionarMusica(playlistId, musicaId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{playlistid}/musicas/{musicaId}")
    public ResponseEntity<Void> removerMusica(@PathVariable("playlistid") Long playlistId,
                                                @PathVariable Long musicaId) {
        playlistService.removerMusica(playlistId, musicaId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{playlistid}/musicas")
    public ResponseEntity<List<Long>> listarMusicas(@PathVariable("playlistid") Long playlistId) {
        return ResponseEntity.ok(playlistService.listarMusicasIds(playlistId));
    }

}

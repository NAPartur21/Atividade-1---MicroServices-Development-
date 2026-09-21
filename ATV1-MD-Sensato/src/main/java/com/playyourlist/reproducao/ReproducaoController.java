package com.playyourlist.reproducao;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reproducao")
public class ReproducaoController {

    private final ReproducaoService reproducaoService;

    public ReproducaoController(ReproducaoService reproducaoService) {
        this.reproducaoService = reproducaoService;
    }

    @PostMapping
    public ResponseEntity<ReproducaoResponse> registrar(@Valid @RequestBody ReproducaoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reproducaoService.registrar(request));
    }

    @GetMapping("/{playlistid}")
    public ResponseEntity<List<ReproducaoResponse>> listarPorPlaylist(@PathVariable("playlistid") Long playlistId) {
        return ResponseEntity.ok(reproducaoService.listarPorPlaylist(playlistId));
    }

    @GetMapping("/total/{playlistid}")
    public ResponseEntity<Long> total(@PathVariable("playlistid") Long playlistId) {
        return ResponseEntity.ok(reproducaoService.totalPorPlaylist(playlistId));
    }

}

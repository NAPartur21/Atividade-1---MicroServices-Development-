package com.playyourlist.musica;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/musicas")
public class MusicaController {

    private final MusicaService musicaService;

    public MusicaController(MusicaService musicaService) {
        this.musicaService = musicaService;
    }

    @PostMapping
    public ResponseEntity<MusicaResponse> cadastrar(@Valid @RequestBody MusicaRequest request) {
        MusicaResponse response = musicaService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<MusicaResponse>> listar() {
        return ResponseEntity.ok(musicaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MusicaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(musicaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MusicaResponse> atualizar(@PathVariable Long id, @Valid @RequestBody MusicaRequest request) {
        return ResponseEntity.ok(musicaService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        musicaService.excluir(id);
        return ResponseEntity.noContent().build();
    }

}

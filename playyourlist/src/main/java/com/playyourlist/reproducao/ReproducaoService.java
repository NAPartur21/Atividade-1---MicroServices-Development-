package com.playyourlist.reproducao;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReproducaoService {

    private final ReproducaoRepository reproducaoRepository;

    public ReproducaoService(ReproducaoRepository reproducaoRepository) {
        this.reproducaoRepository = reproducaoRepository;
    }

    public ReproducaoResponse registrar(ReproducaoRequest request) {
        Reproducao reproducao = new Reproducao(request.getPlaylistId(), LocalDateTime.now());
        return new ReproducaoResponse(reproducaoRepository.save(reproducao));
    }

    public List<ReproducaoResponse> listarPorPlaylist(Long playlistId) {
        return reproducaoRepository.findByPlaylistId(playlistId).stream()
                .map(ReproducaoResponse::new)
                .toList();
    }

    public long totalPorPlaylist(Long playlistId) {
        return reproducaoRepository.countByPlaylistId(playlistId);
    }

}

package com.playyourlist.api;

import com.playyourlist.playlist.PlaylistResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "playlist-service", url = "${services.playlist.url}")
public interface PlaylistClient {

    @GetMapping("/playlists/{id}")
    PlaylistResponse buscarPorId(@PathVariable("id") Long id);

    @PostMapping("/playlists/{playlistId}/musicas/{musicaId}")
    void adicionarMusica(@PathVariable("playlistId") Long playlistId, @PathVariable("musicaId") Long musicaId);

}

package com.playyourlist.api;

import com.playyourlist.musica.MusicaResponse;
import com.playyourlist.playlist.PlaylistResponse;
import com.playyourlist.reproducao.ReproducaoRequest;
import org.springframework.stereotype.Service;

@Service
public class ApiService {

    private final MusicaClient musicaClient;
    private final PlaylistClient playlistClient;
    private final ReproducaoClient reproducaoClient;

    public ApiService(MusicaClient musicaClient, PlaylistClient playlistClient, ReproducaoClient reproducaoClient) {
        this.musicaClient = musicaClient;
        this.playlistClient = playlistClient;
        this.reproducaoClient = reproducaoClient;
    }

    // Valida os dois recursos via Open Feign (GET /playlists/{id} e GET /musicas/{id});
    // qualquer 404 vira uma exceção Feign tratada pelo GlobalExceptionHandler.
    public MensagemResponse adicionarMusicaAPlaylist(Long playlistId, Long musicaId) {
        PlaylistResponse playlist = playlistClient.buscarPorId(playlistId);
        MusicaResponse musica = musicaClient.buscarPorId(musicaId);

        playlistClient.adicionarMusica(playlistId, musicaId);

        String mensagem = "Música " + musica.getTitulo() + " adicionada com sucesso à playlist " + playlist.getNome();
        return new MensagemResponse(mensagem);
    }

    // Confirma que a playlist existe e registra a execução via POST /reproducao (Open Feign).
    public MensagemResponse executarPlaylist(Long playlistId) {
        PlaylistResponse playlist = playlistClient.buscarPorId(playlistId);

        reproducaoClient.registrar(new ReproducaoRequest(playlistId));
        Long total = reproducaoClient.total(playlistId);

        String mensagem = "Playlist " + playlist.getNome() + " executada com sucesso. Total de execuções: " + total;
        return new MensagemResponse(mensagem);
    }

}

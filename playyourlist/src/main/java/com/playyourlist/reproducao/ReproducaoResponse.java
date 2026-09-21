package com.playyourlist.reproducao;

import java.time.LocalDateTime;

public class ReproducaoResponse {

    private Long id;
    private Long playlistId;
    private LocalDateTime dataHora;

    public ReproducaoResponse() {
    }

    public ReproducaoResponse(Reproducao reproducao) {
        this.id = reproducao.getId();
        this.playlistId = reproducao.getPlaylistId();
        this.dataHora = reproducao.getDataHora();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(Long playlistId) {
        this.playlistId = playlistId;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

}

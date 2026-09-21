package com.playyourlist.reproducao;

import jakarta.validation.constraints.NotNull;

public class ReproducaoRequest {

    @NotNull(message = "playlistId é obrigatório")
    private Long playlistId;

    public ReproducaoRequest() {
    }

    public ReproducaoRequest(Long playlistId) {
        this.playlistId = playlistId;
    }

    public Long getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(Long playlistId) {
        this.playlistId = playlistId;
    }

}

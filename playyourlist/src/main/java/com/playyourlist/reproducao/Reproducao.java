package com.playyourlist.reproducao;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reproducoes")
public class Reproducao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "playlistid", nullable = false)
    private Long playlistId;

    @Column(name = "datahora", nullable = false)
    private LocalDateTime dataHora;

    public Reproducao() {
    }

    public Reproducao(Long playlistId, LocalDateTime dataHora) {
        this.playlistId = playlistId;
        this.dataHora = dataHora;
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

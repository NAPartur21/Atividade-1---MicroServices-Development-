package com.playyourlist.playlist;

import jakarta.persistence.*;

@Entity
@Table(name = "playlist_musicas")
public class PlaylistMusica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "playlistid", nullable = false)
    private Long playlistId;

    @Column(name = "musicaid", nullable = false)
    private Long musicaId;

    public PlaylistMusica() {
    }

    public PlaylistMusica(Long playlistId, Long musicaId) {
        this.playlistId = playlistId;
        this.musicaId = musicaId;
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

    public Long getMusicaId() {
        return musicaId;
    }

    public void setMusicaId(Long musicaId) {
        this.musicaId = musicaId;
    }

}

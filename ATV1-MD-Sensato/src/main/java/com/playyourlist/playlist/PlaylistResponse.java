package com.playyourlist.playlist;

public class PlaylistResponse {

    private Long id;
    private String nome;
    private String descricao;

    public PlaylistResponse() {
    }

    public PlaylistResponse(Playlist playlist) {
        this.id = playlist.getId();
        this.nome = playlist.getNome();
        this.descricao = playlist.getDescricao();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}

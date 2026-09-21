package com.playyourlist.musica;

public class MusicaResponse {

    private Long id;
    private String titulo;
    private String artista;
    private String album;
    private Integer duracao;
    private String genero;

    public MusicaResponse() {
    }

    public MusicaResponse(Musica musica) {
        this.id = musica.getId();
        this.titulo = musica.getTitulo();
        this.artista = musica.getArtista();
        this.album = musica.getAlbum();
        this.duracao = musica.getDuracao();
        this.genero = musica.getGenero();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

}

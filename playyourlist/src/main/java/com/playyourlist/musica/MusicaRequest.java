package com.playyourlist.musica;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class MusicaRequest {

    @NotBlank(message = "titulo é obrigatório e não pode ser vazio ou conter apenas espaços")
    private String titulo;

    @NotBlank(message = "artista é obrigatório e não pode ser vazio ou conter apenas espaços")
    private String artista;

    @Size(max = 150, message = "album deve ter no máximo 150 caracteres")
    private String album;

    @NotNull(message = "duracao é obrigatória")
    @Positive(message = "duracao deve ser maior que zero")
    private Integer duracao;

    @Size(max = 50, message = "genero deve ter no máximo 50 caracteres")
    private String genero;

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

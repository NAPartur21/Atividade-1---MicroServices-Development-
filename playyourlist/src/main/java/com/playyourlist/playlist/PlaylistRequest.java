package com.playyourlist.playlist;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PlaylistRequest {

    @NotBlank(message = "nome é obrigatório e não pode ser vazio ou conter apenas espaços")
    private String nome;

    @Size(max = 255, message = "descricao deve ter no máximo 255 caracteres")
    private String descricao;

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

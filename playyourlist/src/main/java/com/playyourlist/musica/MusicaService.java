package com.playyourlist.musica;

import com.playyourlist.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicaService {

    private final MusicaRepository musicaRepository;

    public MusicaService(MusicaRepository musicaRepository) {
        this.musicaRepository = musicaRepository;
    }

    public MusicaResponse criar(MusicaRequest request) {
        Musica musica = new Musica(
                request.getTitulo(),
                request.getArtista(),
                request.getAlbum(),
                request.getDuracao(),
                request.getGenero());
        return new MusicaResponse(musicaRepository.save(musica));
    }

    public List<MusicaResponse> listar() {
        return musicaRepository.findAll().stream()
                .map(MusicaResponse::new)
                .toList();
    }

    public MusicaResponse buscarPorId(Long id) {
        return new MusicaResponse(buscarEntidadePorId(id));
    }

    public Musica buscarEntidadePorId(Long id) {
        return musicaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Música não encontrada com id " + id));
    }

    public MusicaResponse atualizar(Long id, MusicaRequest request) {
        Musica musica = buscarEntidadePorId(id);
        musica.setTitulo(request.getTitulo());
        musica.setArtista(request.getArtista());
        musica.setAlbum(request.getAlbum());
        musica.setDuracao(request.getDuracao());
        musica.setGenero(request.getGenero());
        return new MusicaResponse(musicaRepository.save(musica));
    }

    public void excluir(Long id) {
        Musica musica = buscarEntidadePorId(id);
        musicaRepository.delete(musica);
    }

}

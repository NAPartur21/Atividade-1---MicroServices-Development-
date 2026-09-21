package com.playyourlist.playlist;

import com.playyourlist.exception.BusinessException;
import com.playyourlist.exception.ResourceNotFoundException;
import com.playyourlist.musica.MusicaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final PlaylistMusicaRepository playlistMusicaRepository;
    private final MusicaRepository musicaRepository;

    public PlaylistService(PlaylistRepository playlistRepository,
                            PlaylistMusicaRepository playlistMusicaRepository,
                            MusicaRepository musicaRepository) {
        this.playlistRepository = playlistRepository;
        this.playlistMusicaRepository = playlistMusicaRepository;
        this.musicaRepository = musicaRepository;
    }

    public PlaylistResponse criar(PlaylistRequest request) {
        Playlist playlist = new Playlist(request.getNome(), request.getDescricao());
        return new PlaylistResponse(playlistRepository.save(playlist));
    }

    public List<PlaylistResponse> listar() {
        return playlistRepository.findAll().stream()
                .map(PlaylistResponse::new)
                .toList();
    }

    public PlaylistResponse buscarPorId(Long id) {
        return new PlaylistResponse(buscarEntidadePorId(id));
    }

    public Playlist buscarEntidadePorId(Long id) {
        return playlistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Playlist não encontrada com id " + id));
    }

    public PlaylistResponse atualizar(Long id, PlaylistRequest request) {
        Playlist playlist = buscarEntidadePorId(id);
        playlist.setNome(request.getNome());
        playlist.setDescricao(request.getDescricao());
        return new PlaylistResponse(playlistRepository.save(playlist));
    }

    @Transactional
    public void excluir(Long id) {
        Playlist playlist = buscarEntidadePorId(id);
        playlistMusicaRepository.deleteByPlaylistId(id);
        playlistRepository.delete(playlist);
    }

    @Transactional
    public void adicionarMusica(Long playlistId, Long musicaId) {
        buscarEntidadePorId(playlistId);
        if (!musicaRepository.existsById(musicaId)) {
            throw new ResourceNotFoundException("Música não encontrada com id " + musicaId);
        }
        if (playlistMusicaRepository.findByPlaylistIdAndMusicaId(playlistId, musicaId).isPresent()) {
            throw new BusinessException("Música " + musicaId + " já está na playlist " + playlistId);
        }
        playlistMusicaRepository.save(new PlaylistMusica(playlistId, musicaId));
    }

    @Transactional
    public void removerMusica(Long playlistId, Long musicaId) {
        buscarEntidadePorId(playlistId);
        playlistMusicaRepository.findByPlaylistIdAndMusicaId(playlistId, musicaId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Música " + musicaId + " não está associada à playlist " + playlistId));
        playlistMusicaRepository.deleteByPlaylistIdAndMusicaId(playlistId, musicaId);
    }

    public List<Long> listarMusicasIds(Long playlistId) {
        buscarEntidadePorId(playlistId);
        return playlistMusicaRepository.findByPlaylistId(playlistId).stream()
                .map(PlaylistMusica::getMusicaId)
                .toList();
    }

}

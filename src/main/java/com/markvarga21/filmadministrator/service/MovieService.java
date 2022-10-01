package com.markvarga21.filmadministrator.service;

import com.markvarga21.filmadministrator.dto.MovieDTO;
import com.markvarga21.filmadministrator.entity.Movie;
import com.markvarga21.filmadministrator.mapping.MovieMapper;
import com.markvarga21.filmadministrator.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    @Transactional
    public void saveMovie(final @Valid MovieDTO movie) {
        Movie movieEntity = this.movieMapper.mapMovieDtoToEntity(movie);
        this.movieRepository.save(movieEntity);
    }

    public List<MovieDTO> getMovies() {
        return this.movieRepository
                .findAll()
                .stream()
                .map(this.movieMapper::mapMovieToDto)
                .toList();
    }

    @Transactional
    public int deleteMovie(String title) {
        return this.movieRepository.deleteByTitle(title);
    }
}

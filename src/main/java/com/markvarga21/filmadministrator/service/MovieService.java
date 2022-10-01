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

    @Transactional
    public String updateMovie(final MovieDTO movieDTO) {
        var movieOptional = this.movieRepository.findById(movieDTO.getTitle());

        if (movieOptional.isEmpty()) {
            return String.format("Cannot update, because movie '%s' not found!", movieDTO.getTitle());
        }

        Movie movieToUpdate = movieOptional.get();
        movieToUpdate.setGenre(movieDTO.getGenre());
        movieToUpdate.setLength(movieDTO.getLength());
        this.movieRepository.save(movieToUpdate);

        return String.format("Movie '%s' updated successfully!", movieDTO.getTitle());
    }
}

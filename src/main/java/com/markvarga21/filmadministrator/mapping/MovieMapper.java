package com.markvarga21.filmadministrator.mapping;

import com.markvarga21.filmadministrator.dto.MovieDTO;
import com.markvarga21.filmadministrator.entity.Movie;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovieMapper {
    private final ModelMapper modelMapper;

    public MovieDTO mapMovieToDto(Movie movie) {
        return this.modelMapper.map(movie, MovieDTO.class);
    }

    public Movie mapMovieDtoToEntity(MovieDTO movieDTO) {
        return this.modelMapper.map(movieDTO, Movie.class);
    }
}

package com.cwcastro.store.service;


import com.cwcastro.store.domain.Movie;
import com.cwcastro.store.exception.BadRequestException;
import com.cwcastro.store.mapper.MovieMapper;
import com.cwcastro.store.repository.MovieRepository;
import com.cwcastro.store.requests.MoviePostRequestBody;
import com.cwcastro.store.requests.MoviePutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public Page<Movie> listAll(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    public List<Movie> findByName(String name) {
        return movieRepository.findByName(name);
    }

    public Movie findById(long id) {
        return movieRepository.findById(id)
                // .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Movie not Found"));
                .orElseThrow(() -> new BadRequestException("Movie not Found"));
    }

    @Transactional // allow rollback when there is exception
    public Movie save(MoviePostRequestBody moviePostRequestBody) {
        //TODO: Bugfix mapstruct return null
     // return movieRepository.save(MovieMapper.INSTANCE.toMovie(moviePostRequestBody));
        return movieRepository.save(Movie.builder()
                        .name(moviePostRequestBody.getName())
                        .build());
    }

    public void delete(long id) {
        movieRepository.delete(findById(id));
    }

    public void replace(MoviePutRequestBody animePutRequestBody) {
        Movie savedMovie = findById(animePutRequestBody.getId());
        Movie movie = Movie.builder()
                .id(savedMovie.getId())
                .name(animePutRequestBody.getName())
                .build();
        movieRepository.save(movie);
    }

    public String findByDetailsMovies(String name) {
        String obj = new RestTemplate().getForObject("https://www.googleapis.com/books/v1/volumes?q={name}", String.class, name);
        return  obj;
    }
}

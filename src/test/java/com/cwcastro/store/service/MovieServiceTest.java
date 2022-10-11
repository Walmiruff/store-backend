package com.cwcastro.store.service;

import com.cwcastro.store.domain.Movie;
import com.cwcastro.store.repository.MovieRepository;
import com.cwcastro.store.util.MovieCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
public class MovieServiceTest {
    @InjectMocks
    private MovieService movieService;
    @Mock
    private MovieRepository movieRepositoryMock;

    @BeforeEach
    void setUp() {
        PageImpl<Movie> moviePage = new PageImpl<>(List.of(MovieCreator.createValidMovie()));

        BDDMockito.when(movieRepositoryMock.findAll(
                ArgumentMatchers.any(PageRequest.class))
                )
                .thenReturn(moviePage);
    }
    @Test
    @DisplayName("list returns list of movies when successful")
    void list_ReturnListOfMovies_WhenSucceful() {
        String expectName = MovieCreator.createValidMovie().getName();

        Page<Movie> moviePage = movieService.listAll(PageRequest.of(1,1));

        Assertions.assertThat(moviePage).isNotNull();

        Assertions.assertThat(moviePage.toList())
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(moviePage.toList().get(0).getName()).isEqualTo(expectName);
    }

}

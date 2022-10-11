package com.cwcastro.store.controller;

import com.cwcastro.store.domain.Movie;
import com.cwcastro.store.service.MovieService;
import com.cwcastro.store.util.MovieCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
public class MovieControllerTest {
    @InjectMocks
    private  MovieController movieController;
    @Mock
    private MovieService movieServiceMock;

    @BeforeEach
    void setUp() {
        PageImpl<Movie> moviePage = new PageImpl<>(List.of(MovieCreator.createValidMovie()));

        BDDMockito.when(movieServiceMock.listAll(ArgumentMatchers.any()))
                .thenReturn(moviePage);
    }

    @Test
    @DisplayName("list returns list of movies when successful")
    void list_ReturnListOfMovies_WhenSucceful() {
        String expectName = MovieCreator.createValidMovie().getName();

        Page<Movie> moviePage = movieController.list(null).getBody();

        Assertions.assertThat(moviePage).isNotNull();

        Assertions.assertThat(moviePage.toList())
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(moviePage.toList().get(0).getName()).isEqualTo(expectName);
    }
}

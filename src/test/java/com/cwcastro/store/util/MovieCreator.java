package com.cwcastro.store.util;


import com.cwcastro.store.domain.Movie;

public class MovieCreator {

    public static Movie createValidMovie() {
        return Movie.builder()
                .name("Matrix")
                .id(1L)
                .build();
    }
}

package com.cwcastro.store.mapper;

import com.cwcastro.store.domain.Movie;
import com.cwcastro.store.requests.MoviePostRequestBody;
import com.cwcastro.store.requests.MoviePutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class MovieMapper {
    public static final MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    public abstract Movie toMovie(MoviePostRequestBody moviePostRequestBody);

    public abstract Movie toMovie(MoviePutRequestBody moviePostRequestBody);
}

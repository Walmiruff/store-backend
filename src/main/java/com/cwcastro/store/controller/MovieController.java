package com.cwcastro.store.controller;


import com.cwcastro.store.domain.Movie;
import com.cwcastro.store.requests.MoviePostRequestBody;
import com.cwcastro.store.requests.MoviePutRequestBody;
import com.cwcastro.store.service.MovieService;
import com.cwcastro.store.util.DateUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

import java.util.List;

@RestController
@RequestMapping("movies")
@Log4j2
@RequiredArgsConstructor
public class MovieController {
    private final DateUtil dateUtil;
    private final MovieService movieService;

    @GetMapping
    @Operation(summary = "List all movies paginated", description = "The default size is 10, use the parameter size to change the default value",
            tags = {"movie"})
    public ResponseEntity<Page<Movie>> list(@ParameterObject Pageable pageable) {
        // log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(movieService.listAll(pageable));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Movie> findById(@PathVariable long id){
        return ResponseEntity.ok(movieService.findById(id));
    }

    @GetMapping(path = "/find")
    public ResponseEntity<List<Movie>> findByName(@RequestParam String name){
        return ResponseEntity.ok(movieService.findByName(name));
    }

    @GetMapping(path = "/details")
    public String detailsMovies(@RequestParam String name){
        return movieService.findByDetailsMovies(name);
    }

    @PostMapping
    public ResponseEntity<Movie> save(@RequestBody @Valid MoviePostRequestBody moviePostRequestBody){
        return new ResponseEntity<>(movieService.save(moviePostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful Operation"),
            @ApiResponse(responseCode = "400", description = "When Movie Does Not Exist in The Database")
    })
    public ResponseEntity<Void> delete(@PathVariable long id) {
        movieService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody MoviePutRequestBody moviePutRequestBody) {
        movieService.replace(moviePutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

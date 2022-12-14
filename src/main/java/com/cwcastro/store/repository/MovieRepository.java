package com.cwcastro.store.repository;

import com.cwcastro.store.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByName(String name);
}

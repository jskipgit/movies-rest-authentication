package com.ironyard.repo;

import com.ironyard.data.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by jasonskipper on 2/6/17.
 */
public interface MovieRepo extends PagingAndSortingRepository<Movie, Long> {
}

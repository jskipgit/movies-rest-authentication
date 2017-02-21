package com.ironyard.controller.rest;

import com.ironyard.data.Movie;
import com.ironyard.dto.FormMovie;
import com.ironyard.repo.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.ironyard.data.Permission.KEY_PERM_CREATE_MOVIE;

/**
 * Created by jasonskipper on 2/20/17.
 */
@RestController
public class RestMovieController {
    @Autowired
    MovieRepo movieRepo;

    @RequestMapping(path = "/rest/movies", method = RequestMethod.GET)
    public Page<Movie> listMovies(@RequestParam(value = "page", required = false) Integer page,
                             @RequestParam(value = "size", required = false) Integer size,
                                  @RequestParam(value = "desc", required = false)Sort.Direction dir
                            ){
        if(page == null){
            page = 0;
        }
        if(size == null){
            size = 2;
        }
        if(dir == null){
            dir = Sort.Direction.DESC;
        }
        Sort s = new Sort(dir, "name");
        PageRequest pr = new PageRequest(page, size, s);

        Page<Movie> found = movieRepo.findAll(pr);
        // put list into model
        return found;
    }

    @RequestMapping(path = "/rest/movie/create", method = RequestMethod.POST)
    public Movie createMovie(@RequestBody Movie saveToDB) throws Exception {
        // save to database
        movieRepo.save(saveToDB);

        return saveToDB;
    }

    @RequestMapping(path = "/rest/movie/{id}",  method = RequestMethod.DELETE)
    public void deleteMovie(@PathVariable Long id) throws Exception {
        // save to database
        movieRepo.delete(id);

        return;
    }

    @RequestMapping(path = "/rest/movie/{id}",  method = RequestMethod.GET)
    public Movie findByMovieId(@PathVariable Long id) throws Exception {
        // save to database
        Movie found = movieRepo.findOne(id);

        return found;
    }

    @RequestMapping(path = "/rest/movie",  method = RequestMethod.PATCH)
    public Movie editMovie(@RequestBody Movie saveToDB) throws Exception {
        // save to database
        if(saveToDB.getId() == 0){
            throw new Exception("Patch update must specify movie id.");
        }
        movieRepo.save(saveToDB);

        return saveToDB;
    }
}

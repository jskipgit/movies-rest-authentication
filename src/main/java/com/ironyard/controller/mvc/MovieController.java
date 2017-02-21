package com.ironyard.controller.mvc;

import com.ironyard.data.Movie;
import com.ironyard.data.MovieUser;
import com.ironyard.dto.FormMovie;
import com.ironyard.repo.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.ironyard.data.Permission.KEY_PERM_CREATE_MOVIE;

/**
 * Created by jasonskipper on 2/7/17.
 */
@Controller
public class MovieController {

    @Autowired
    MovieRepo movieRepo;

    @Value("${upload.location}")
    private String uploadLocation;


    @RequestMapping(path = "/secure/movie/delete", method = RequestMethod.GET)
    public String deleteMovie(Model mapOfDataForJsp, @RequestParam Long id){
        movieRepo.delete(id);
        // find by
        mapOfDataForJsp.addAttribute("success_msg", "Movie Deleted.");
        return "forward:/secure/movies";
    }


    @RequestMapping(path = "/secure/movie/select", method = RequestMethod.GET)
    public String selectMovieForEdit(Model mapOfDataForJsp, @RequestParam Long id){
        Movie selectedMovie = movieRepo.findOne(id);
        mapOfDataForJsp.addAttribute("selectedMovie", selectedMovie);
        String dest = "/secure/create";
        return dest;
    }
//@RequestParam("file") MultipartFile file
    @RequestMapping(path = "/secure/movie/create", method = RequestMethod.POST,
            consumes = MediaType.ALL_VALUE)
    public String createMovie(HttpSession session, Model dataToJsp, FormMovie myFavoriteMove) throws Exception {
        String dest = "/secure/create";
        // check permissions
        if(!((List)session.getAttribute("user_perms")).contains(KEY_PERM_CREATE_MOVIE)){
            throw new Exception("401 permission denied");
        }
        Movie saveToDB = null;
        // only do copy work if local file was uploaded
        if(!myFavoriteMove.getMovieFile().getOriginalFilename().isEmpty()) {
            String uploadedFileName = System.currentTimeMillis() + "_" + myFavoriteMove.getMovieFile().getOriginalFilename();
            // copy from input stream to computer disk
            Files.copy(myFavoriteMove.getMovieFile().getInputStream(), Paths.get(uploadLocation + uploadedFileName));
            saveToDB = new Movie(myFavoriteMove, uploadedFileName);

        }else{
            // no copy work, could be edit or just new with poster URL
            saveToDB = new Movie(myFavoriteMove);
            // we need to re-fetch poster file name (local file name) if this
            // is an edit AND they have not provided a poster URL
            if(saveToDB.getId() != 0 &&
                    (saveToDB.getPosterUrl()==null || saveToDB.getPosterUrl().isEmpty())){
                // need to ensure uploaded file name is not overridden
                Movie tmp = movieRepo.findOne(saveToDB.getId());
                saveToDB.setPosterFileName(tmp.getPosterFileName());
            }
        }
        // save to database
        movieRepo.save(saveToDB);
        // if successful save, add message
        if(saveToDB.getId()!=0) {
            dataToJsp.addAttribute("succes_movie_create_msg",
                    String.format("Movie '%s' was created!", saveToDB.getName()));
        }
        return dest;
    }

    @RequestMapping(path = "/secure/movies")
    public String listMovies(Model xyz){
        String destination = "home";
        Iterable found = movieRepo.findAll();
        // put list into model
        xyz.addAttribute("mList", found);

        // go to jsp
        return destination;
    }
}

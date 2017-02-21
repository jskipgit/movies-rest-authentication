package com.ironyard.repo;

import com.ironyard.data.Movie;
import com.ironyard.data.MovieUser;
import com.ironyard.data.Permission;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by jasonskipper on 2/6/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepoTest {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MovieRepo movieRepo;

    @Test
    public void tryme(){
        Permission p = new Permission();
        p.setKey("test");
        List<Permission> tmp = new ArrayList();
        tmp.add(p);
        if(tmp.stream().anyMatch(t -> t.getKey().equals("test"))){
            System.out.println("it works");
        }
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void testMovieFavorites() {

        // create a movie
        Movie movieA = new Movie();
        movieA.setName("Matrix");
        movieRepo.save(movieA);

        Movie movieB = new Movie();
        movieB.setName("Matrix Reloaded");
        movieRepo.save(movieB);

        // save a user
        MovieUser userA = new MovieUser();
        userA.setDisplayName("Jason");
        userA.setUsername("test");
        userA.setPassword("pass");

        List<Movie> userAFavoritesList = new ArrayList();
        userAFavoritesList.add(movieA);
        userAFavoritesList.add(movieB);
        userA.setFavorites(userAFavoritesList);
        userRepo.save(userA);

        // save another user
        // save a user
        MovieUser userB = new MovieUser();
        userB.setDisplayName("Copy Cat");
        userB.setUsername("test2");
        userB.setPassword("pass2");
        List<Movie> userBFavoritesList = new ArrayList();
        userBFavoritesList.add(movieA);
        userBFavoritesList.add(movieB);
        userB.setFavorites(userBFavoritesList);
        userRepo.save(userB);


        // fetch user make sure he has our movies
        MovieUser fetchedUser = userRepo.findByUsernameAndPassword("test", "pass");
        Assert.assertEquals("incorrect fav count", 2, fetchedUser.getFavorites().size());

        // fetch user make sure he has our movies
        MovieUser fetchedUserB = userRepo.findByUsernameAndPassword("test2", "pass2");
        Assert.assertEquals("incorrect fav count", 2, fetchedUserB.getFavorites().size());


    }

    @Test
    @Transactional
    public void testUserOperations() {
        MovieUser usr = new MovieUser();
        usr.setPassword("pass");
        usr.setDisplayName("Jason Skipper");
        usr.setUsername("skipper.jason@gmail.com");
        userRepo.save(usr);

        long savedUserId = usr.getId();

        MovieUser found = userRepo.findOne(savedUserId);

        assertNotNull("Can't find user after saving!", found);
        assertEquals("Displayname miss-match after save", usr.getDisplayName(), found.getDisplayName());
        // test update
        found.setPassword("pass2");
        userRepo.save(found);
        MovieUser found2 = userRepo.findOne(savedUserId);
        assertEquals("Update pass failed", "pass2", found2.getPassword());
        // test delete
        //userRepo.delete(found2);
        //assertNull("Delete failed", userRepo.findOne(found2.getId()));
    }
}
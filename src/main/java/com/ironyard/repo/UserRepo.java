package com.ironyard.repo;

import com.ironyard.data.MovieUser;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jasonskipper on 2/6/17.
 */
public interface UserRepo extends CrudRepository<MovieUser,Long> {
    public MovieUser findByUsernameAndPassword(String username, String password);
}

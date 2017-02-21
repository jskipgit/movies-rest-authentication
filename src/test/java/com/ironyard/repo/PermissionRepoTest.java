package com.ironyard.repo;

import com.ironyard.data.MovieUser;
import com.ironyard.data.Permission;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by jasonskipper on 2/13/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PermissionRepoTest {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private PermissionRepo permissionRepo;

    @Test
    @Transactional
    @Rollback(value = false)
    public void testPermissionLogic() {
        MovieUser tmpUser = new MovieUser();
        tmpUser.setUsername("jskipper");
        tmpUser.setDisplayName("Jason Skipper");
        tmpUser.setPassword("pass");
        userRepo.save(tmpUser);

        Permission p = permissionRepo.findByKey("PERM_CREATE_USR");
        tmpUser.setPermissions(new ArrayList<>());
        tmpUser.getPermissions().add(p);
        userRepo.save(tmpUser);

        tmpUser = userRepo.findOne(tmpUser.getId());

        assertEquals(tmpUser.getPermissions().get(0).getKey(), "PERM_CREATE_USR");
        if(tmpUser.getPermissions().contains("PERM_CREATE_USR")){
            // display create user menu
        }
    }
}
package com.ironyard.secure;

import com.ironyard.data.MovieUser;
import com.ironyard.repo.UserRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by jasonskipper on 2/21/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TokenMasterTest {
    @Autowired
    private UserRepo userRepo;

    @Test
    public void testValidTokenShouldPass() throws Exception {
        TokenMaster tm = new TokenMaster();
        MovieUser user = userRepo.findAll().iterator().next();

        String myCoolToken = tm.generateToken(user);
        System.out.println("ENCRYPTED:"+myCoolToken);
        boolean isValid = tm.validate(myCoolToken);
        Assert.assertTrue("Token should be valid", isValid);

        // assert user lookup from token works
        Long userId = tm.getUserIdFromToken(myCoolToken);
        Assert.assertEquals(user.getId(), userId.longValue());
    }

    @Test
    public void testFakeTokenShouldFail() throws Exception {
        TokenMaster tm = new TokenMaster();
        String myCoolToken = "this is not real";
        boolean isValid = tm.validate(myCoolToken);
        Assert.assertFalse("Token should NOT be valid", isValid);
    }

    @Test
    public void testExpiredTokenFails() throws Exception{
        TokenMaster tm = new TokenMaster();
        Assert.assertFalse("Expired token should fail.",tm.validate("3ddeOvh1ReY/sXJ6oQw6BE6gnGh2IT+V"));
    }
}
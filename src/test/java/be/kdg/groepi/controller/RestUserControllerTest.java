package be.kdg.groepi.controller;

import be.kdg.groepi.model.User;
import be.kdg.groepi.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.Calendar;

import static be.kdg.groepi.utils.DateUtil.dateToLong;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class RestUserControllerTest {
    RestUserController restUserController;
    User user;

    @Before
    public void beforeEachTest() {
        restUserController = new RestUserController();
        user = new User("TIMMEH", "TIM@M.EH", "hemmit", dateToLong(4,5,2011,15,32,0));
        UserService.createUser(user);
    }

    @After
    public void afterEachTest() {
        UserService.deleteUser(user);
    }

    @Test
    public void testGetUser() {
        ModelAndView modelAndView = restUserController.getUser(String.valueOf(user.getId()));
        assertEquals("profile/user", modelAndView.getViewName());
        assertNotNull("Model should not be null", modelAndView.getModel());
        User returnedUser = (User) modelAndView.getModel().get("userObject");
        assertNotNull("User with ID 1 should be present in the ModelAndView", returnedUser);
        
        modelAndView = restUserController.getUser(String.valueOf(user.getId()+1)); // + 1 om een user te krijgen die niet bestaat.
        assertEquals("This user does not exist, so getUser should return \"" + (user.getId()+1) + "\".",
                String.valueOf(user.getId()+1), modelAndView.getModel().get("userId"));
    }

    @Test
    public void testCreateUser(){
        ModelAndView modelAndView = restUserController.createUser(user,null);
        assertNotNull("User object should exist", modelAndView.getModel().get("userObject"));
        User createdUser = (User) modelAndView.getModel().get("userObject");
        assertNotNull("User should have a name", createdUser.getName());
        assertNotNull("User not found in DB",UserService.getUserById(createdUser.getId()));
    }
}

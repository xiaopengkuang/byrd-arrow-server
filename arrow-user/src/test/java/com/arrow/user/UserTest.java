package com.arrow.user;

import com.arrow.user.config.GlobalConfig;
import com.arrow.user.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * DESCRIPTION:
 * Created by BYRD on 26/10/2017
 * Version 0.1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {GlobalConfig.class})
@ActiveProfiles("dev")
public class UserTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void addUser() throws Exception {
        assert userService.addUser("");
    }

}
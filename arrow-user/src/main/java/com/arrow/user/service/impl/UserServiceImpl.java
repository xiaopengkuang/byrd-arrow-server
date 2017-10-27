package com.arrow.user.service.impl;

import com.arrow.user.dao.IUserDao;
import com.arrow.user.facade.service.UserService;
import com.arrow.user.model.User;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * DESCRIPTION:
 * Created by BYRD on 26/10/2017
 * Version 0.1
 */
@Service("userService")
public class UserServiceImpl implements UserService.Iface {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IUserDao userDao;

    @Override
    public boolean addUser(String data) throws TException {
        logger.info("insert user...");
        User user = new User();
        user.setName("byrd");
        return userDao.insert(user) > 0;
    }
}

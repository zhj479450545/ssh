package com.zhj.service;

import com.zhj.vo.User;
import com.zhj.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/2/11.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public void addUser(User user) {
        userDao.saveObject(user);
    }
}

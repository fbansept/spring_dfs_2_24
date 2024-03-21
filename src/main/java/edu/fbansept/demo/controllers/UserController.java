package edu.fbansept.demo.controllers;

import edu.fbansept.demo.dao.UserDao;
import edu.fbansept.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserDao userDao;

    @PostMapping("/sign-in")
    public void signIn(@RequestBody User user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userDao.save(user);

    }

}

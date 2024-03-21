package edu.fbansept.demo.security;

import edu.fbansept.demo.dao.UserDao;
import edu.fbansept.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> optionalUser = userDao.findByEmail(email);

        if(optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("email introuvable");
        }

        return new AppUserDetails(optionalUser.get());
    }
}

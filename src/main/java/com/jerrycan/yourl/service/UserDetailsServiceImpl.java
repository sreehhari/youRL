package com.jerrycan.yourl.service;

import com.jerrycan.yourl.models.User;
import com.jerrycan.yourl.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //this is gonna get the userdetails from our db and then convert the user to the format of the jwt userdetails using the UserDetailsImpl we defined
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("user not found with username : " + username));
        return UserDetailsImpl.build(user);
    }
}

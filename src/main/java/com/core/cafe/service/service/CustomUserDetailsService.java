package com.core.cafe.service.service;

import com.core.cafe.service.beans.CafeUserDetails;
import com.core.cafe.service.model.User;
import com.core.cafe.service.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserDetailsRepository userDetailsRepository;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User userDetails = userDetailsRepository.findByEmail(userName);

        if(userDetails == null){
            throw new UsernameNotFoundException("Invalid Username provided");
        }

        return new CafeUserDetails(userDetails);
    }
}

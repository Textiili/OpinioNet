package com.opinionet.opinionetservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.opinionet.opinionetservice.domain.User;
import com.opinionet.opinionetservice.domain.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User curruser = userRepository.findByUsername(username);

		if (curruser == null || curruser.getPasswordHash() == null) {
			throw new CustomExceptionMessage("Invalid credentials");
		}
        UserDetails user = new org.springframework.security.core.userdetails.User(
			username, 
			curruser.getPasswordHash(), 
        	AuthorityUtils.createAuthorityList(curruser.getRole())
		);
        return user;
    }
}
// User user = userRepository.findByUsername(username);
        // if (user == null) {
        //     throw new CustomExceptionMessage("Invalid credentials");
        // }
        // String[] roles = user.isAdmin() ? new String[]{"ROLE_ADMIN"} : new String[]{"ROLE_USER"};
        // return new org.springframework.security.core.userdetails.User(
        //     user.getUsername(),
        //     user.getPassword(),
        //     AuthorityUtils.createAuthorityList(roles)
        // );

package com.opinionet.opinionetservice.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.opinionet.opinionetservice.domain.User;
import com.opinionet.opinionetservice.domain.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User curruser = userRepository.findByUsername(username);

		if (curruser == null || curruser.getPasswordHash() == null) {
			throw new CustomExceptionMessage("Bad credentials");
		}
        UserDetails user = new org.springframework.security.core.userdetails.User(
			username, 
			curruser.getPasswordHash(), 
        	AuthorityUtils.createAuthorityList(curruser.getRole())
		);
        return user;
    }
}


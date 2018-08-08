package com.ahi.security;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ahi.entity.AhiUser;
import com.ahi.repository.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
    	Optional<AhiUser> user = userRepository.findByLoginIdIgnoreCase(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        /*for (Role role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }*/
    	if(user.isPresent() && user.get().isActive()){
    		return new User(user.get().getLoginId(), user.get().getPassword(), grantedAuthorities);
    	}else{
    		throw new UsernameNotFoundException(username);
    	}
    }
}
package com.GreenStitch.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.GreenStitch.repository.UserRepository;
import com.GreenStitch.model.User;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> opt = userRepo.findByEmail(username);

		if (opt.isPresent()) {

			User customer = opt.get();

			List<GrantedAuthority> authorities = new ArrayList<>();
			SimpleGrantedAuthority sga = new SimpleGrantedAuthority(customer.getRole());
			authorities.add(sga);

			return new org.springframework.security.core.userdetails.User(customer.getEmail(), customer.getPassword(),authorities);

		} else
			throw new BadCredentialsException("User Details not found with this username: " + username);

	}

}

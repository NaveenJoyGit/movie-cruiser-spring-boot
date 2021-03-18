package com.moviecruiser.jpa;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.moviecruiser.dao.JwtFilter;
import com.moviecruiser.model.MovieList;
import com.moviecruiser.model.User;
import com.moviecruiser.repository.MovieRepository;
import com.moviecruiser.repository.UserRepository;
import com.moviecruiser.service.UserService;

@SpringBootTest
class UserTest {
	
	@Autowired
	JwtFilter jwt;

	@Autowired
	UserRepository userrepo;
	
	@Autowired
	MovieRepository movieRep;
	
	@Autowired
	UserService userService;
	
	@Test
	void testUserRepository() {
		List<User> userList = userrepo.findAll();
	}
	
  
	
	@Test
	void testMovieRep() {
		List<MovieList> mList = movieRep.findAll();
	}

}

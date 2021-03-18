package com.moviecruiser.jpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.moviecruiser.controller.MovieCruiserController;
import com.moviecruiser.model.Favorite;
import com.moviecruiser.model.MovieList;
import com.moviecruiser.service.FavoriteService;
import com.moviecruiser.service.MovieService;

@SpringBootTest
@AutoConfigureMockMvc
class MovieListTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	MovieCruiserController controller;
	
	@Autowired
	MovieService movieService;
	
	@Autowired
	FavoriteService favService;
	
	@Test
	void testGetAllMovies() {
		List<MovieList> mList = movieService.getAllMovies();
		assertEquals("Avatar", mList.get(0).getName());
	}
	
	@Test
	void testFavorite() {
		List<Favorite> fList = favService.getAllFavorites();
		assertEquals("Titanic", fList.get(1).getMovies().getName());
	}

	@Test
	void testAddFavorite() {
		MovieList movie = movieService.getMovieById(4);
		favService.addToFavorite(movie);
	}
	
	@Test
	void testDeleteFavorite() {
		favService.deleteFavorite(48);
	}
	
	//controller testing
	@Test
	public void contextLoads() throws Exception {	
		assertThat(controller).isNotNull();
	}
	
	@Test
	void testMockMvc() throws Exception {
		this.mockMvc.perform(get("/favorites")).andDo(print()).andExpect(status().isOk());
	}
	
}

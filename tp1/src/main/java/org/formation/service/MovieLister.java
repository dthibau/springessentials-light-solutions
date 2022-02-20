package org.formation.service;

import java.util.List;
import java.util.stream.Collectors;

import org.formation.dao.MovieDAO;
import org.formation.model.Movie;


public class MovieLister {

	private MovieDAO movieDao;

	public void setMovieDao(MovieDAO movieDao) {
		this.movieDao = movieDao;
	}

	public List<Movie> moviesDirectedBy(String director) {
		
		return movieDao.findAll().stream().filter(m -> m.getDirector().equalsIgnoreCase(director)).collect(Collectors.toList());
	}
}

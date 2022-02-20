package org.formation.dao;

import java.util.List;

import org.formation.model.Movie;

public interface MovieDAO {
	public List<Movie> findAll();
}

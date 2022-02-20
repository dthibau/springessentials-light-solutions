package org.formation.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.formation.dao.MovieDAO;
import org.formation.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.java.Log;


@Service
@Log
public class MovieLister 
{

	@Autowired
	private MovieDAO movieDao;

	public List<Movie> moviesDirectedBy(String director) {
		
		return movieDao.findAll().stream().filter(m -> m.getDirector().equalsIgnoreCase(director)).collect(Collectors.toList());
	}
	
	@PostConstruct
	public void init() {
		StackTraceElement[] stackTrace = Thread.currentThread()
			      .getStackTrace();
		log.info(stackTrace[1].getMethodName());
	}

	
}

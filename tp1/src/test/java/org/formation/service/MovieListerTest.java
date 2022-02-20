package org.formation.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.formation.model.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MovieListerTest {

	@Test
	public void testDirectedBy() {
		ApplicationContext context = new ClassPathXmlApplicationContext("test.xml");
		

		MovieLister movieLister =
			(MovieLister) context.getBean("movieLister");

	
		List<Movie> hitchcock = movieLister.moviesDirectedBy("Hitchcock");
		List<Movie> HITCHCOCK = movieLister.moviesDirectedBy("HITCHCOCK");
		assertEquals(hitchcock.size(), 2);
		assertEquals(HITCHCOCK.size(), 2);
		List<Movie> empty = movieLister.moviesDirectedBy("");
		assertNotNull(empty);
		assertEquals(0, empty.size());
		
		
	}
}

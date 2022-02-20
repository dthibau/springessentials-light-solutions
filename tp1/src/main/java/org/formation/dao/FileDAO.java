package org.formation.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.formation.model.Movie;

public class FileDAO implements MovieDAO {

private String file;
	private List<Movie> data;
	
	public List<Movie> findAll() {
		try  {
			if ( data == null ) {
				loadFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	private void loadFile() throws IOException {
		data = new ArrayList<Movie>();;
		InputStream in = this.getClass().getResourceAsStream(file);

		BufferedReader br = new BufferedReader (new InputStreamReader(in));

		String line=null;
		while ((line=br.readLine()) != null) {
			String [] tokens = line.split(";");
			Movie m = new Movie();
			m.setDirector(tokens[0]);
			m.setTitle(tokens[1]);
			m.setDuration(Integer.parseInt(tokens[2]));
			data.add(m);
		}
	}
}

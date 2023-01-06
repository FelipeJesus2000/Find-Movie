package main;


public class Main {
	public static String[] main(String movieName) throws Exception {
		Movie movie = TMDBRequest.searchMovie(movieName);
		String [] response = { movie.getName(), movie.getDesc(), movie.getAverage(), movie.getCover() };
		return response;    
	}
}


package main;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JOptionPane;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.google.gson.Gson;




public class TMDBRequest {
	static String keyAPI = "9ae0e3a6eacfd655a2763cd9460a3946";
	static String webService = "https://api.themoviedb.org/3/search/movie?api_key=";

	
	public static Movie searchMovie(String movieName)  throws Exception {
		movieName = movieName.replaceAll(" ", "+");
		String urlCall = webService + keyAPI + "&query=" + movieName + "&language=pt-BR";

		try {
			URL url = new URL(urlCall);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			if(connection.getResponseCode() != 200)
				throw new RuntimeException("HTTP error code : " + connection.getResponseCode());
			
			BufferedReader  response = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String jsonString = BufferedReaderToString.convert(response);
			
			StringFunctions sf = new StringFunctions();
			String [] arrayMovies;
			arrayMovies = sf.getArrayMovies(jsonString);
			
			Gson gson = new Gson();
			Movie movie = new Movie();
			String[] itens = new String[sf.wordCount(jsonString)];
			for(int i = 0; i < sf.wordCount(jsonString); i++) {
				movie = gson.fromJson(arrayMovies[i], Movie.class);
				itens[i] = movie.getName() + " ID:" + movie.getId();
			}
			movie = null;
			if (itens[0] == null ) return movie;
			Object selectedValue = JOptionPane.showInputDialog(null,
			          "Escolha um filme", "Opçao",
			              JOptionPane.INFORMATION_MESSAGE, null,
			                  itens, null);
			
			String movieSelected = selectedValue.toString();
			int idIndex = movieSelected.indexOf("ID:")+3;
			int movieID = Integer.parseInt(movieSelected.substring(idIndex));
			
			
			System.out.printf("Buscando filme :, %s... \n", movieID);
			for(int i = 0; i < sf.wordCount(jsonString); i++) {
				movie = gson.fromJson(arrayMovies[i], Movie.class);			
				if(movie.getId() == movieID) { 
					System.out.println("Filme encontrado!!!");
					System.out.printf("Nome: %s, ID: %s", movie.getName(), movie.getId());
					break;
				}
				
			}
			
			
            return movie;
		}
		catch(Exception e) {
			 throw new Exception("ERRO: " + e);
		}
	}

}

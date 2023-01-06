package main;

public class StringFunctions {
	public  String getResults(String jsonString) {
		String start = "\"results\":[";
		int positionStart = jsonString.indexOf(start) + start.length();
		int positionEnd = jsonString.indexOf('}')+1;
		String results = jsonString.substring(positionStart, positionEnd);
		return results;
	}
	public  int getTotalResults(String jsonString) {
		String start = "\"total_results\":";
		int positionStart = jsonString.indexOf(start) + start.length();
		return getStringNumber(jsonString.substring(positionStart));
	}
	public int getStringNumber(String string) {
		int i = 0;
		String number = "";
		boolean end = false;
		do {
			if(Character.isDigit(string.charAt(i))) {
				//System.out.println(i);
				number += string.charAt(i);
				i++;
			}else {
				end = true;
			}
				
		}while(!end);
		return Integer.parseInt(number);
	}
	public String[] getArrayMovies(String jsonString) {
		int totalResults = wordCount(jsonString);
		String [] arrayMovies = new String[totalResults];
		int positionStart;
		int positionEnd = 0;
		for(int i = 0; i < totalResults; i++) {
			if(i == 0) {
				String start = "\"results\":[";
				positionStart = jsonString.indexOf(start) + start.length();
			}else {
				positionStart = positionEnd +1;
			}
			positionEnd = jsonString.indexOf('}', positionStart) +1;
			arrayMovies[i] = getResults(jsonString, positionStart, positionEnd);
			//System.out.println("Filme " + i + ": " + arrayMovies[i]);
		}
		return arrayMovies;
	}
	public  String getResults(String jsonString, int start, int end) {
		String results = jsonString.substring(start, end);
		return results;
	}
	public  int wordCount (String str){
		String word = "adult";
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
		    if (str.substring(i).startsWith(word)) {
		        count ++;
		    }
		}
		return count;
	}
}

package main;

public class Movie {
	String original_title;
	String overview;
	String vote_average;
	String poster_path;
	int id;

	public int getId() {
		return id;
	}
	public String getName() {
		return original_title;
	}
	public String getDesc() {
		return overview;
	}
	public String getAverage() {
		return vote_average;
	}
	public String getCover() {
		return poster_path;
	}

	@Override
    public String toString() {
		return "{" + "title=" + original_title + ", overview=" + overview + ", vote_average=" + vote_average + '}';
	}
}

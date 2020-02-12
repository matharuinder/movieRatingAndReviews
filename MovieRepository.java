package ca.sheridancollege.matinder.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.matinder.beans.Movie;


public interface MovieRepository extends JpaRepository<Movie, Long>{

	
	public List<Movie> findByTitleContainingAndDirectorContainingAndGenreContaining(String title,String director,String genre);
	
	public List<Movie> findByTitleContainingAndDirectorContainingAndGenreContainingOrderByDirector(String title,String director,String genre);
	public List<Movie> findByTitleContainingAndDirectorContainingAndGenreContainingOrderByRelease(String title,String director,String genre);
	public List<Movie> findByTitleContainingAndDirectorContainingAndGenreContainingOrderByTitle(String title,String director,String genre);
	public List<Movie> findByTitleContainingAndDirectorContainingAndGenreContainingOrderByRating(String title,String director,String genre);
	public List<Movie> findByTitleContainingAndDirectorContainingAndGenreContainingOrderByGenre(String title,String director,String genre);
}

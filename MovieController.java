package ca.sheridancollege.matinder.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.matinder.beans.Movie;
import ca.sheridancollege.matinder.beans.Review;
import ca.sheridancollege.matinder.logic.Logic;
import ca.sheridancollege.matinder.repositories.MovieRepository;
import ca.sheridancollege.matinder.repositories.ReviewRepository;
import lombok.AllArgsConstructor;


@Controller
@AllArgsConstructor
public class MovieController {

	private MovieRepository movieRepository;
	private ReviewRepository reviewRepository;
	
	@GetMapping("/")
	public String index(Model model) {

		Logic.director = "";
		Logic.title = "";
		Logic.genre = "";
		model.addAttribute("movie",new Movie());

		List<Movie> movieList = movieRepository.findAll();
		model.addAttribute("movieList",movieList);
		return "index";
	}
	
	@PostMapping("/search")
	public String search(Model model, @RequestParam String title, @RequestParam String director, @RequestParam String genre) {
		
		
		List<Movie> movieList = movieRepository.findByTitleContainingAndDirectorContainingAndGenreContaining(title, director, genre);
		Logic.director = director;
		Logic.title = title;
		Logic.genre = genre;
		model.addAttribute("movieList",movieList);
		return "index";
	}
	
	@PostMapping("/sort")
	public String sort(Model model,@RequestParam String sortby) {
		List<Movie> movieList=null;
		
		if(sortby.equals("rating"))
			movieList = movieRepository.findByTitleContainingAndDirectorContainingAndGenreContainingOrderByRating(Logic.title, Logic.director, Logic.genre);
		if(sortby.equals("title"))
			movieList = movieRepository.findByTitleContainingAndDirectorContainingAndGenreContainingOrderByTitle(Logic.title, Logic.director, Logic.genre);
		if(sortby.equals("director"))
			movieList = movieRepository.findByTitleContainingAndDirectorContainingAndGenreContainingOrderByDirector(Logic.title, Logic.director, Logic.genre);
		if(sortby.equals("genre"))
			movieList = movieRepository.findByTitleContainingAndDirectorContainingAndGenreContainingOrderByGenre(Logic.title, Logic.director, Logic.genre);
		if(sortby.equals("release"))
			movieList = movieRepository.findByTitleContainingAndDirectorContainingAndGenreContainingOrderByRelease(Logic.title, Logic.director, Logic.genre);
		
		model.addAttribute("movieList",movieList);
		return "index";
	}
	
	@GetMapping("/reviewList/{id}")
	public String addReview(Model model, @PathVariable Long id) {
		//reviewRepository.save(review);
		//Movie movie = new Movie();
		
		Movie movie = movieRepository.findById(id).get();
		
		List<Review> reviewList = reviewRepository.findByMovie_Id(id);
		model.addAttribute("reviewList",reviewList);
		model.addAttribute("movie", movie);		
		return "reviews";
	}
	
	@GetMapping("/reviewList/addComment/{id}")
	public String newComment(Model model, @RequestParam String user, @RequestParam String comment, @PathVariable Long id) {
		//reviewRepository.save(review);
		//Movie movie = new Movie();
		
		Review review = Review.builder().username(user).comment(comment).build();
		//reviewRepository.save(review);
		
		Movie movie = movieRepository.findById(id).get();
		review.setMovie(movie);
		
		reviewRepository.save(review);
		
		List<Review> reviewList = reviewRepository.findByMovie_Id(id);
		model.addAttribute("reviewList",reviewList);
		model.addAttribute("movie", movie);		
		return "reviews";
	}
	
}

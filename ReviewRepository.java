package ca.sheridancollege.matinder.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.matinder.beans.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{
	
	public List<Review> findByMovie_Id(Long id); 

}

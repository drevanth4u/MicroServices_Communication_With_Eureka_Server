package com.example.demo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	//@Autowired
	//private WebClient.Builder webClientBuilder;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable String userId){
		
		
		//get all movie Id's
		UserRating ratings = restTemplate.getForObject("http://rating-data-service/ratingsdata/users/"+userId, UserRating.class);
		
		
		return ratings.getUserRating()
				.stream().map(rating -> {
			//for each movie Id, call movie info service to get details
			Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);
			
			//put them all together
			return new CatalogItem(movie.getName(), "Test", rating.getRating());
		})
				.collect(Collectors.toList());
		
	}

}


/*
Movie movie = webClientBuilder.build()

		     .get()
		     .uri("http://localhost:8082/movies/"+rating.getMovieId())
		     .retrieve()
		     .bodyToMono(Movie.class)
		     .block();
*/

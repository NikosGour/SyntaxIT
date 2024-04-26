package gr.dit.hua.nikosgourn;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {
	public static void main(String[] args) {
		final int[] MOVIE_DURATIONS = { 90 , 85 , 75 , 60 , 120 , 150 , 125 };
		final int FLIGHT_DURATION = 250;
		Optional<int[]> result = recommend_two_movies(MOVIE_DURATIONS , FLIGHT_DURATION);
		if (result.isPresent()) {
			System.out.println(Arrays.toString(result.get()));
		}
		else{
			System.out.println("No movie pair is short enough for this flight");
		}
	}
	
	/**
	 * Given a list of movie duration, this function return a pair of indexes that represent the pair of movies , with the longest combined
	 * duration, constrained but the flight duration - 30
	 *
	 * @param movie_durations a list of movie durations to pick a pair from
	 * @param flight_duration the duration of the flight which is the constraint of the problem
	 * @return a pair of indexes of the `movie_duration` param, which have the biggest sum of duration
	 */
	private static Optional<int[]> recommend_two_movies(int[] movie_durations , int flight_duration) {
		int[] return_value;
		List<int[]> pairs_found = new ArrayList<>();
		final int MAX_ALLOWED = flight_duration - 30;
		
		// Iterate over the movie array
		// we do i -> 0..length-1 and j -> i+1..length for optimization
		// there is no reason for j to be lower than i because those pairs have already
		// been checked. Also, j has no reason to start from cause we don't want to recommend
		// a user to watch the same movie twice.
		// Also, i has no reason to go up to the length of the array as all the
		// pairs of the last item have been checked by the previous iterations
		for (int i = 0; i < movie_durations.length - 1; i++) {
			// Initializing movie_a here, for optimization, no need to initialize inside j loop
			int movie_a = movie_durations[i];
			for (int j = i + 1; j < movie_durations.length; j++) {
				// Checking if pair of movies satisfy constraint and add them to candidates
				int movie_b = movie_durations[j];
				if (movie_a + movie_b <= MAX_ALLOWED) {
					pairs_found.add(new int[]{ i , j });
				}
			}
		}
		
		// Finding the pair with the longest combined duration
		int max = 0;
		int max_index = 0;
		for (int i = 0; i < pairs_found.size(); i++) {
			int[] pair = pairs_found.get(i);
			int total_duration = movie_durations[pair[0]] + movie_durations[pair[1]];
			if (total_duration > max) {
				max       = total_duration;
				max_index = i;
			}
		}
		if (pairs_found.isEmpty()) {
			return Optional.empty();
		}
		return_value = pairs_found.get(max_index);
		return Optional.of(return_value);
	}
}
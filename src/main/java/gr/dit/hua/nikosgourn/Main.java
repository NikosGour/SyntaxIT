package gr.dit.hua.nikosgourn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		final int[] MOVIE_DURATIONS = { 90 , 85 , 75 , 60 , 120 , 150 , 125 };
		final int FLIGHT_DURATION = 250;
		System.out.println(Arrays.toString(recommend_two_movies(MOVIE_DURATIONS , FLIGHT_DURATION)));
	}
	
	private static int[] recommend_two_movies(int[] movie_durations , int flight_duration) {
		int[] result;
		List<int[]> pairs_found = new ArrayList<>();
		final int MAX_ALLOWED = flight_duration - 30;
		
		for (int i = 0; i < movie_durations.length - 1; i++) {
			int movie_a = movie_durations[i];
			for (int j = i + 1; j < movie_durations.length; j++) {
				//				if(i == j) { continue;}
				int movie_b = movie_durations[j];
				if (movie_a + movie_b <= MAX_ALLOWED) {
					pairs_found.add(new int[]{ i , j });
				}
			}
		}
		
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
		result = pairs_found.get(max_index);
		return result;
	}
}
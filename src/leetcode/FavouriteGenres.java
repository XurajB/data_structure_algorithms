package leetcode;

import java.util.*;

/**
 * Given a map Map<String, List<String>> userSongs with user names as keys and a list of all the songs that the user has listened to as values.
 * Also given a map Map<String, List<String>> songGenres, with song genre as keys and a list of all the songs within that genre as values. The song can only belong to only one genre.
 * The task is to return a map Map<String, List<String>>, where the key is a user name and the value is a list of the user's favorite genre(s).
 * Favorite genre is the most listened to genre. A user can have more than one favorite genre if he/she has listened to the same number of songs per each of the genres.
 * https://leetcode.com/discuss/interview-question/373006
 */
public class FavouriteGenres {
    public static void main(String[] args) {
        Map<String, List<String>> userMap = new HashMap<>();
        List<String> list1 = Arrays.asList("song1", "song2", "song3", "song4", "song8");
        List<String> list2 = Arrays.asList("song5", "song6", "song7");
        userMap.put("David", list1);
        userMap.put("Emma", list2);

        Map<String, List<String>> genreMap = new HashMap<>();
        List<String> list3 = Arrays.asList("song1", "song3");
        List<String> list4 = Arrays.asList("song7");
        List<String> list5 = Arrays.asList("song2", "song4");
        List<String> list6 = Arrays.asList("song5", "song6");
        List<String> list7 = Arrays.asList("song8", "song9");
        genreMap.put("Rock", list3);
        genreMap.put("Dubstep", list4);
        genreMap.put("Techno", list5);
        genreMap.put("Pop", list6);
        genreMap.put("Jazz", list7);

        // test case 2
        Map<String, List<String>> userMap2 = new HashMap<>();
        List<String> list11 = Arrays.asList("song1", "song2");
        List<String> list21 = Arrays.asList("song3", "song4");
        userMap2.put("David", list11);
        userMap2.put("Emma", list21);

        Map<String, List<String>> genreMap2 = new HashMap<>();

        Map<String, List<String>> answer = getFavouriteGenres(userMap, genreMap);
        for (String s: answer.keySet()) {
            System.out.print(s + ": ");
            System.out.print(answer.get(s));
            System.out.println();
        }
    }

    // Time: O(U * (S + G), Space: O(S + U * G), S = total number of songs, G = total number of genre, U = total number of users
    private static Map<String, List<String>> getFavouriteGenres(Map<String, List<String>> userSongs, Map<String, List<String>> songsGenres) {
        Map<String, String> songsToGenreMap = new HashMap<>();
        for (String genre: songsGenres.keySet()) {
            List<String> songs = songsGenres.get(genre);
            for (String song: songs) {
                songsToGenreMap.put(song, genre);
            }
        }

        Map<String, List<String>> answer = new HashMap<>();
        int max = 0;
        for (String user: userSongs.keySet()) {
            answer.put(user, new ArrayList<>());
            Map<String, Integer> genreCount = new HashMap<>(); // for each user
            List<String> songs = userSongs.get(user);
            for (String song: songs) {
                String genre = songsToGenreMap.get(song);
                if (genre != null) {
                    int count = genreCount.getOrDefault(genre, 0) + 1;
                    genreCount.put(genre, count);
                    max = Math.max(max, count);
                }
            }

            // append to answer if genre count is equal to max
            for (String genre : genreCount.keySet()) {
                if (genreCount.get(genre) == max) {
                    answer.get(user).add(genre);
                }
            }
        }

        return answer;
    }
}

package MusicStreaming_Prac80;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Song {
    private String title;
    private String artist;

    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    @Override
    public String toString() {
        return title + " by " + artist;
    }
}

class Playlist {
    private String name;
    private List<Song> songs;

    public Playlist(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Song> getSongs() {
        return new ArrayList<>(songs);
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void removeSong(Song song) {
        songs.remove(song);
    }

    public void displayPlaylist() {
        System.out.println("Playlist: " + name);
        for (Song song : songs) {
            System.out.println("- " + song);
        }
    }
}

class User {
    private String username;
    private List<Playlist> playlists;

    public User(String username) {
        this.username = username;
        this.playlists = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public List<Playlist> getPlaylists() {
        return new ArrayList<>(playlists);
    }

    public void createPlaylist(String playlistName) {
        Playlist playlist = new Playlist(playlistName);
        playlists.add(playlist);
    }

    public void deletePlaylist(Playlist playlist) {
        playlists.remove(playlist);
    }

    public void recommendSongs() {
        // For simplicity, generate random songs as recommendations
        List<Song> recommendedSongs = generateRandomSongs(5);
        System.out.println("Recommended Songs:");
        for (Song song : recommendedSongs) {
            System.out.println("- " + song);
        }
    }

    private List<Song> generateRandomSongs(int numSongs) {
        List<Song> randomSongs = new ArrayList<>();
        String[] titles = { "Song1", "Song2", "Song3", "Song4", "Song5", "Song6", "Song7" };
        String[] artists = { "Artist1", "Artist2", "Artist3", "Artist4" };

        Random random = new Random();
        for (int i = 0; i < numSongs; i++) {
            String title = titles[random.nextInt(titles.length)];
            String artist = artists[random.nextInt(artists.length)];
            randomSongs.add(new Song(title, artist));
        }

        return randomSongs;
    }
}

public class Prac80_MusicStreamingService {
    public static void main(String[] args) {
        // Create a user
        User user = new User("JohnDoe");

        // Create playlists
        user.createPlaylist("Favorites");
        user.createPlaylist("Workout");

        // Add songs to playlists
        Playlist favoritesPlaylist = user.getPlaylists().get(0);
        favoritesPlaylist.addSong(new Song("Believer", "Imagine Dragons"));
        favoritesPlaylist.addSong(new Song("Shape of You", "Ed Sheeran"));

        Playlist workoutPlaylist = user.getPlaylists().get(1);
        workoutPlaylist.addSong(new Song("Eye of the Tiger", "Survivor"));
        workoutPlaylist.addSong(new Song("Uptown Funk", "Mark Ronson ft. Bruno Mars"));

        // Display playlists
        System.out.println("User: " + user.getUsername());
        for (Playlist playlist : user.getPlaylists()) {
            playlist.displayPlaylist();
        }

        // Recommend songs to the user
        user.recommendSongs();
    }
}

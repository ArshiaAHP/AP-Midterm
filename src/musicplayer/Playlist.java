package musicplayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Playlist {
    private String title;
    private ArrayList<Music> playlist;
    private User owner;

    public Playlist(String title, User owner){
        if(title.isEmpty() || owner == null){
            throw new InvalidOperationException("Title or owner can't be null or empty.");
        }

        this.title = title;
        this.owner = owner;
        this.playlist = new ArrayList<>();
    }

    private void authenticate(String password) throws InvalidOperationException { //for checking if pass is correct, clean code :)
        if (password == null || !password.equals(owner.getPassword())) {
            throw new InvalidOperationException("Invalid password");
        }
    }

    public void editTitle(String newTitle, String password){
        authenticate(password);

        if (newTitle == null || newTitle.trim().isEmpty()) {
            throw new InvalidOperationException("New title cannot be null or empty");
        }

        this.title = newTitle;
    }

    public void addMusic(Music music, String password){
        authenticate(password);

        if (music == null) {
            throw new InvalidOperationException("Music cannot be null");
        }

        if (playlist.contains(music)) {
            throw new InvalidOperationException("Music already exists in playlist");
        }else{
            playlist.add(music);
        }
    }

    public void removeMusic(Music music, String password){
        authenticate(password);

        if (music == null) {
            throw new InvalidOperationException("Music cannot be null");
        }

        if(playlist.contains(music)){
            playlist.remove(music);
        }else {
            throw new InvalidOperationException("Music is not in playlist.");
        }

    }

    public ArrayList<Music> searchInPlaylist(String title) {
        if (title == null) {
            throw new InvalidOperationException("Title cannot be null");
        }

        ArrayList<Music> result = new ArrayList<>();
        for (Music music : playlist) {
            if (music.getTitle().equalsIgnoreCase(title)) {
                result.add(music);
            }
        }
        if(result.isEmpty()){
            return null;
        }else{
            return result;
        }
    }

    public Music searchInPlaylist(String title, User singer) {
        if (title == null || singer == null) {
            throw new IllegalArgumentException("Title and singer cannot be null");
        }

        for (Music music : playlist) {
            if (music.getTitle().equalsIgnoreCase(title) && music.getSinger().equals(singer)) {
                return music;
            }
        }
        return null;
    }

    public void playPlaylist() {
        if (playlist.isEmpty()) {
            System.out.println("Playlist is empty!");
            return;
        }

        System.out.println("Playing playlist: " + this.title);
        for (Music music : playlist) {
            music.play();
        }
    }

    public void shufflePlaylist() {
        if (playlist.isEmpty()) {
            System.out.println("Playlist is empty!");
            return;
        }

        ArrayList<Music> newPlaylist = new ArrayList<>(playlist);
        Collections.shuffle(newPlaylist, new Random()); // used collections to randomize the sequence.

        System.out.println("Shuffling playlist: " + this.title);
        for (Music music : newPlaylist) {
            music.play();
        }
    }
}

import musicplayer.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        User user1 = new User("john", "12341234");
        User user2 = new User("arthur", "43214321");
        User artist1 = new User("eminem", "menime123");
        User artist2 = new User("weekend", "starboy123");

        Music song1 = new Music("8 mile", artist1);
        Music song2 = new Music("godzilla", artist1);
        Music song3 = new Music("blinding lights", artist2);
        Music song4 = new Music("levitating", artist2);
        Music song5 = new Music("godzilla", artist2); // for search

        System.out.println("\n### Regular user test ###");
        user1.playMusic(song1);
        user1.playMusic(song3);
        user1.playMusic(song1);
        user1.playMusic(song3);
        user1.playMusic(song1);
        try{
            user1.playMusic(song4); //number of plays for regular user
        } catch (RuntimeException e) {
            System.out.println("*ERROR: " + e.getMessage());
        }

        try{
            user1.createPlaylist("test playlist");
        }catch (InvalidOperationException e){
            System.out.println("*ERROR: " + e.getMessage());
        }
        user1.follow(artist1);
        user2.follow(artist1);

        System.out.println("\n$$$ Upgrade to premium and Test $$$");
        user1.buyPremium(user1, 3);
        user1.createPlaylist("Premium playlist");
        try {
            user1.createPlaylist("Premium playlist"); //duplicate playlist.
        }catch (InvalidOperationException e){
            System.out.println("*ERROR: " + e.getMessage());
        }
        user1.playMusic(song1);
        user1.playMusic(song3);
        user1.playMusic(song1);
        user1.playMusic(song3);
        user1.playMusic(song2);
        user1.playMusic(song1);
        user1.playMusic(song2); //more than 5 plays

        System.out.println("\n%%% Testing Playlist %%%");
        Playlist playlist = new Playlist("mix", user1);
        playlist.addMusic(song2, "12341234");
        playlist.addMusic(song1, "12341234");
        playlist.addMusic(song3,"12341234");
        System.out.println("successfully added playlist: " + playlist.getTitle() + " and songs");
        try{
            playlist.addMusic(song1, "12341234"); //duplicate song
        } catch (InvalidOperationException e) {
            System.out.println("*ERROR: " + e.getMessage());
        }
        try {
            playlist.addMusic(song4, "44444444"); // wrong pass
        } catch (InvalidOperationException e) {
            System.out.println("*ERROR: " + e.getMessage());
        }
        playlist.playPlaylist();
        playlist.shufflePlaylist();

        System.out.println("\n*** Testing Search ***");
        ArrayList<Music> result = Music.search("godzilla");
        System.out.println("found " + result.size() + " songs, name = godzilla");
        System.out.println();
        Music music = Music.search("godzilla", artist1);
        System.out.println("exact match " + music.getTitle() + " by " + music.getSinger().getUsername());

        System.out.println("\n=== Testing Error Cases ===");
        try {
            User invalidUser = new User("ali", "short"); //short pass
        } catch (InvalidOperationException e) {
            System.out.println("*ERROR: " + e.getMessage());
        }

        try {
            User invalidUser = new User("", "1234_1234_1234"); //invalid name
        } catch (InvalidOperationException e) {
            System.out.println("*ERROR: " + e.getMessage());
        }

        try {
            User invalidUser = new User("arthur", "1234_1234_1234"); //duplicate name
        } catch (InvalidOperationException e) {
            System.out.println("*ERROR: " + e.getMessage());
        }

        System.out.println("\n=+=+= Final Stats =+=+=");
        System.out.println("Total users: " + User.getAllUsers().size());
        System.out.println("Total songs: " + Music.getAllMusics().size());
        System.out.println(user1.getUsername() + "'s playlists: " + user1.getPlaylists().size());
        System.out.println(song1.getTitle() + " streams: " + song1.getNumberOfStream());

    }
}
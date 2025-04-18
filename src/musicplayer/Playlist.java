package musicplayer;

import java.util.ArrayList;

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

    public void editTitle(String newTitle, String password){


    }
}

package musicplayer;

import java.util.ArrayList;
import java.util.List;

public class Music {
    private String title;
    private User singer;
    private int numberOfStream = 0;
    private static ArrayList<Music> allMusics = new ArrayList<>();

    public Music(String title, User singer) {
        if (title == null || title.trim().isEmpty()) {
            throw new InvalidOperationException("Title cannot be null or empty");
        }
        if (singer == null) {
            throw new InvalidOperationException("Singer cannot be null");
        }

        this.title = title;
        this.singer = singer;
        this.numberOfStream = 0;
        allMusics.add(this);
    }

    public void play(){
        System.out.println("Now playing: " + this.title + " by " + this.singer.getUsername() + " (Total streams: " + this.numberOfStream + ")");
        this.numberOfStream++;
    }

    public static List<Music> search(String title) {
        if (title == null) {
            throw new InvalidOperationException("Title cannot be null");
        }

        ArrayList<Music> result = new ArrayList<>();
        for (Music music : allMusics) {
            if (music.title.equalsIgnoreCase(title)) {
                result.add(music);
            }
        }
        if(result.isEmpty()){
            return null;
        }else{
            return result;
        }
    }

    public static Music search(String title, User singer) {
        if (title == null || singer == null || title.isEmpty()) {
            throw new InvalidOperationException("Title and singer cannot be null");
        }

        for (Music music : allMusics) {
            if (music.title.equalsIgnoreCase(title) && music.singer.equals(singer)) {
                return music;
            }
        }
        return null;
    }

    public String getTitle() {
        return title;
    }

}

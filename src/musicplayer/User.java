package musicplayer;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private ArrayList<User> followerList;
    private ArrayList<User> followingList;
    private UserBehavior behavior;
    private ArrayList<Playlist> playlists;
    private static ArrayList<User> allUsers = new ArrayList<>();

    public User(String username, String password) throws InvalidOperationException {
        for(User user : allUsers){
            if(user.getUsername().equals(username)){
                throw new InvalidOperationException("Username already exists.");
            }
        }
        if (password == null || password.length() < 8) {
            throw new InvalidOperationException("Password must be at least 8 characters long");
        }
        this.username = username;
        this.password = password;
        this.followerList = new ArrayList<>();
        this.followingList = new ArrayList<>();
        this.behavior = new RegularBehavior();
        this.playlists = new ArrayList<>();

        allUsers.add(this);
    }

    public void follow(User user) {
        if (user == null || user == this) {
            throw new InvalidOperationException("Invalid user to follow");
        }

        if (!followingList.contains(user)) {
            followingList.add(user);
            user.addFollower(this);
        }
    }

    private void addFollower(User user) { // method to add follower to another user or self, clean code :)
        if (!followerList.contains(user)) {
            followerList.add(user);
        }
    }

    public void createPlaylist(String title, User owner) {
        this.behavior.createPlaylist(title, owner);
    }

    public void playMusic(Music music) {
        this.behavior.playMusic(music);
    }

    public void buyPremium(User owner, int month) {
        this.behavior.buyPremium(owner, month);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setBehavior(UserBehavior behavior) {
        this.behavior = behavior;
    }

}

package musicplayer;

public class PremiumBehavior implements UserBehavior{
    private int month;

    public PremiumBehavior(int month){
        this.month = month;
    }

    @Override
    public void createPlaylist(String title, User owner) {
        if (title == null || title.trim().isEmpty()) {
            throw new InvalidOperationException("Playlist title cannot be empty");
        }else if (owner == null) {
            throw new InvalidOperationException("Owner cannot be null");
        }

        for(Playlist playlist : owner.getPlaylists()){
            if(title.equals(playlist.getTitle())){
                throw new InvalidOperationException("Playlist with this name already exists.");
            }
        }

        Playlist newPlaylist = new Playlist(title, owner);
    }

    @Override
    public void playMusic(Music music) {
        if (music == null) {
            throw new InvalidOperationException("Music cannot be null");
        }

        music.play();
    }

    @Override
    public void buyPremium(User owner, int month) {
        if (month <= 0) {
            throw new InvalidOperationException("Month must be positive");
        }

        this.month += month;
        System.out.println("Premium extended for " + month + " months. Total remaining: " + this.month);
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getMonth() {
        return month;
    }
}

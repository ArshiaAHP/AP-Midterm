package musicplayer;

public class RegularBehavior implements UserBehavior{
    private int playingLimit = 5;

    @Override
    public void createPlaylist(String Title, User Owner) {
        throw new InvalidOperationException("Regular User can't make playlist.");
    }

    @Override
    public void playMusic(Music music) {
        if(playingLimit > 0){
            music.play();
            playingLimit--;
        }else{
            throw new InvalidOperationException("Playing limit reached.");
        }
    }

    @Override
    public void buyPremium(User owner, int month) {
        owner.setBehavior(new PremiumBehavior(month));
    }
}

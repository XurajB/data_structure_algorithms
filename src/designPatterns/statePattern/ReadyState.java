package designPatterns.statePattern;

public class ReadyState extends State {

    public ReadyState(AudioPlayer audioPlayer) {
        super(audioPlayer);
    }

    @Override
    void clickPlay() {
        audioPlayer.startPlayback();
        audioPlayer.changeState(new PlayingState(audioPlayer));
    }

    @Override
    void clickLock() {
        audioPlayer.changeState(new LockedState(audioPlayer));
    }

    @Override
    void clickNext() {
        audioPlayer.nextSong();
    }

    @Override
    void clickPrevious() {
        audioPlayer.previousSong();
    }
}

package designPatterns.statePattern;

public class PlayingState extends State {
    public PlayingState(AudioPlayer audioPlayer) {
        super(audioPlayer);
    }

    @Override
    void clickPlay() {
        // already playing, pause
        audioPlayer.stopPlayback();
        audioPlayer.changeState(new ReadyState(audioPlayer));
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

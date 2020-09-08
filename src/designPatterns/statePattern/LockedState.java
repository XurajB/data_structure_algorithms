package designPatterns.statePattern;

// concrete state with various behavior associated with a state of the context
public class LockedState extends State {
    public LockedState(AudioPlayer audioPlayer) {
        super(audioPlayer);
    }

    @Override
    void clickPlay() {
        // locked, do nothing
    }

    @Override
    void clickLock() {
        // currently we are at locked state. we pressed unlock
        if (audioPlayer.isPlaying()) {
            audioPlayer.changeState(new PlayingState(audioPlayer));
        } else {
            audioPlayer.changeState(new ReadyState(audioPlayer));
        }
    }

    @Override
    void clickNext() {
        // locked
    }

    @Override
    void clickPrevious() {
        // locked
    }
}

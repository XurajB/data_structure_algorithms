package designPatterns.statePattern;

/**
 * The state pattern allows an object to alter its behavior when its internal state changes. This pattern is close to the concept of a finite-state machine.
 * The state pattern can be interpreted as a strategy pattern which is able to switch a strategy through invocations of methods defined in the pattern’s interface.
 *
 * This class acts as a context. It also maintains a reference to one of the state classes that represent the current state of audio player
 */
public class AudioPlayer {
    private State state;
    private boolean isPlaying;

    public AudioPlayer() {
        state = new ReadyState(this);
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void changeState(State state) {
        this.state = state;
    }

    public void startPlayback() {
        isPlaying = true;
        System.out.println("starting playback..");
    }

    public void stopPlayback() {
        isPlaying = false;
        System.out.println("stopping playback..");
    }

    public void nextSong() {
        System.out.println("changing to next song..");
    }

    public void previousSong() {
        System.out.println("changing to prev song..");
    }

    /// ui/test methods
    public void clickLock() {
        state.clickLock();
    }
    public void clickPlay() {
        state.clickPlay();
    }
    public void clickNext() {
        state.clickNext();
    }
    public void clickPrevious() {
        state.clickPrevious();
    }
}

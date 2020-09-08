package designPatterns.statePattern;

// base state class that all concrete states should implement
// it also provides a backreference to the context object associated with the state
public abstract class State {
    protected AudioPlayer audioPlayer;
    public State(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
    }

    abstract void clickPlay();
    abstract void clickLock();
    abstract void clickNext();
    abstract void clickPrevious();
}

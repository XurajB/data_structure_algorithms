package designPatterns.statePattern;

public class Test {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.clickLock();
        audioPlayer.clickPlay();
        audioPlayer.clickPlay();
        audioPlayer.clickLock();
        audioPlayer.clickNext();
        audioPlayer.clickPrevious();
        audioPlayer.clickPlay();
    }
}

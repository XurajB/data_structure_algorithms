package designPatterns.commandPattern;

public class CeilingFanOffCommand implements Command {
    private Fan fan;

    public CeilingFanOffCommand(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        fan.off();
    }
}

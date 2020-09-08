package designPatterns.commandPattern;

public class CeilingFanOnCommand implements Command {
    private Fan fan;

    public CeilingFanOnCommand(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        fan.on();
    }
}

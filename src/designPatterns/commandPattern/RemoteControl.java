package designPatterns.commandPattern;

/**
 * A simple home automation remote control with multiple buttons with different actions
 */
public class RemoteControl {

    int buttons;
    private Command[] onCommands;
    private Command[] offCommands;

    public RemoteControl(int buttons) {
        this.buttons = buttons; // zero index

        onCommands = new Command[buttons];
        offCommands = new Command[buttons];
    }

    public void setCommand(int slot, Command onCommand, Command offCommand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    public void onButtonPushed(int slot) {
        onCommands[slot].execute();
    }

    public void offButtonPushed(int slot) {
        offCommands[slot].execute();
    }
}

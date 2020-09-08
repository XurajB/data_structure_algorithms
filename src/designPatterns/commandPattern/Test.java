package designPatterns.commandPattern;

public class Test {
    public static void main(String[] args) {
        RemoteControl remoteControl = new RemoteControl(3);

        Light bedroom = new Light();
        LightOnCommand bedroomLightOn = new LightOnCommand(bedroom);
        LightOffCommand bedroomLightOff = new LightOffCommand(bedroom);
        remoteControl.setCommand(0, bedroomLightOn, bedroomLightOff);

        Light outside = new Light();
        LightOnCommand outsideLightOn = new LightOnCommand(outside);
        LightOffCommand outsideLightOff = new LightOffCommand(outside);
        remoteControl.setCommand(1, outsideLightOn, outsideLightOff);

        Fan ceilingFan = new Fan();
        CeilingFanOnCommand ceilingFanOnCommand = new CeilingFanOnCommand(ceilingFan);
        CeilingFanOffCommand ceilingFanOffCommand = new CeilingFanOffCommand(ceilingFan);
        remoteControl.setCommand(2, ceilingFanOnCommand, ceilingFanOffCommand);

        remoteControl.onButtonPushed(0);
        remoteControl.offButtonPushed(0);
        remoteControl.onButtonPushed(1);
        remoteControl.onButtonPushed(2);
        remoteControl.offButtonPushed(2);
    }
}

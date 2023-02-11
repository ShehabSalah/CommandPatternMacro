import commands.*;
import invoker.RemoteControl;
import receivers.Light;
import receivers.TV;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // initialize receivers
        Light livingRoomlight = new Light("Living Room");
        TV tv = new TV();

        // initialize Light commands
        LightOnCommand lightOnCommand = new LightOnCommand(livingRoomlight);
        LightOffCommand lightOffCommand = new LightOffCommand(livingRoomlight);

        // initialize TV commands
        TVOnCommand tvOnCommand = new TVOnCommand(tv);
        TVOffCommand tvOffCommand = new TVOffCommand(tv);

        // setup macro commands
        MacroCommand onMacroCommands = new MacroCommand(new Command[]{lightOnCommand, tvOnCommand});
        MacroCommand offMacroCommands = new MacroCommand(new Command[]{lightOffCommand, tvOffCommand});

        // initialize the remote controller
        RemoteControl remoteControl = new RemoteControl();
        remoteControl.setCommand(0, onMacroCommands, offMacroCommands);

        // run on macro commands
        remoteControl.onButtonPressed(0);

        // wait 3 seconds
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // run off macro commands
        remoteControl.offButtonPressed(0);
    }
}
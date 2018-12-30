package xyz.auguwu.hamakaze.bot.core.managers;

import xyz.auguwu.hamakaze.bot.core.ICommand;
import java.util.HashMap;

public class CommandManager {
    public HashMap<String, ICommand> commands = new HashMap<>();

    public HashMap<String, ICommand> getCommands() {
        return commands;
    }
    public void registerCommands() {

    }
    public ICommand getCommand(String c) {
        return commands.get(c);
    }
}
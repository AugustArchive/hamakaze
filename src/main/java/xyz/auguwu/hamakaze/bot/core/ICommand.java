package xyz.auguwu.hamakaze.bot.core;

public interface ICommand {
    void execute(CommandContext ctx);
    default Command get() {
        return this.getClass().getAnnotation(Command.class);
    }
}
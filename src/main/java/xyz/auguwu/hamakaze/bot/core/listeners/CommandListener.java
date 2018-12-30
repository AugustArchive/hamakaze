package xyz.auguwu.hamakaze.bot.core.listeners;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import xyz.auguwu.hamakaze.bot.core.CommandContext;
import xyz.auguwu.hamakaze.bot.Hamakaze;
import xyz.auguwu.hamakaze.bot.core.ICommand;

import java.util.Arrays;

public class CommandListener extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot() || event.getAuthor().isFake())
            return;

        String prefix = "=>";
        boolean isMentioned = event.getMessage().getContentRaw().startsWith(event.getGuild().getSelfMember().getAsMention());
        int t = isMentioned ? event.getGuild().getSelfMember().getAsMention().length() + 1 : prefix.length();

        if (!event.getMessage().getContentRaw().startsWith(prefix) || !isMentioned)
            return;

        String content = event.getMessage().getContentRaw();
        String[] args = content.split(" +");
        String commandName = args[0].toLowerCase();
        ICommand command = Hamakaze.getInstance().getCommandManager().getCommand(commandName);

        if (command == null) {
            for (ICommand cmd: Hamakaze.getInstance().getCommandManager().getCommands().values()) {
                if (Arrays.asList(cmd.get().aliases()).contains(commandName)) {
                    cmd = Hamakaze.getInstance().getCommandManager().getCommand(cmd.get().command());
                    break;
                }
            }
        }

        CommandContext ctx = new CommandContext(event, Hamakaze.getInstance(), args);
        command.execute(ctx);
    }
}
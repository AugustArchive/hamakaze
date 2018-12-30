package xyz.auguwu.hamakaze.bot.core;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import xyz.auguwu.hamakaze.bot.Hamakaze;

import java.util.function.Consumer;

public class CommandContext {
    public TextChannel channel;
    public Message message;
    public User sender;
    public Guild guild;
    public Member member;
    public GuildMessageReceivedEvent event;
    public JDA jda;
    public String[] args;
    public Hamakaze bot;

    public CommandContext(GuildMessageReceivedEvent event, Hamakaze bot, String[] args) {
        this.event = event;
        this.channel = event.getChannel();
        this.message = event.getMessage();
        this.sender = event.getAuthor();
        this.guild = event.getGuild();
        this.member = event.getMember();
        this.jda = event.getJDA();
        this.args = args;
        this.bot = bot;
    }

    public void reply(String content) {
        reply(content, null);
    }

    public void reply(String content, Consumer<Message> success) {
        event.getChannel().sendMessage(content).queue(success);
    }

    public void embed(MessageEmbed em) {
        embed(em, null);
    }

    public void embed(MessageEmbed e, Consumer<Message> succ) {
        event.getChannel().sendMessage(e).queue(succ);
    }
}
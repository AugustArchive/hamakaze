package xyz.auguwu.hamakaze.bot.audio.handlers;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import xyz.auguwu.hamakaze.bot.Hamakaze;
import xyz.auguwu.hamakaze.bot.audio.GuildMusicManager;
import xyz.auguwu.hamakaze.bot.core.CommandContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AudioVoiceHandler {
    private String httpRegex = "((http:\\/\\/|https:\\/\\/)?(www.)?(([a-zA-Z0-9-]){2,}\\.){1,4}([a-zA-Z]){2,6}(\\/([a-zA-Z-_\\/\\.0-9#:?=&;,]*)?)?)";
    public void loadSong(CommandContext ctx, String input) {
        GuildMusicManager manager = get(ctx.guild);
        if (input.startsWith("<") && input.endsWith(">"))
            input = input.replace("<", "").replace(">", "");

        Pattern p = Pattern.compile(httpRegex);
        Matcher match = p.matcher(input);
        if (!match.find())
            input = "ytsearch:" + input;

        Hamakaze.getInstance().playerManager.loadItemOrdered(manager, input, new SongResultHandler(ctx.event));
    }

    public void enqueueTrack(AudioTrack track, GuildMessageReceivedEvent event) {
        GuildMusicManager guildMusicManager = get(event.getGuild());
        guildMusicManager.trackScheduler.queue(track);
    }

    public GuildMusicManager get(Guild guild) {
        String ID = guild.getId();
        GuildMusicManager manager = Hamakaze.getInstance().players.get(ID);
        if (manager == null) {
            manager = new GuildMusicManager(Hamakaze.getInstance().playerManager);
            Hamakaze.getInstance().players.put(ID, manager);
        }

        return manager;
    }
}
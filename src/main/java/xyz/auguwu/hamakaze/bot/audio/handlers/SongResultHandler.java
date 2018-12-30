package xyz.auguwu.hamakaze.bot.audio.handlers;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class SongResultHandler implements AudioLoadResultHandler {
    private GuildMessageReceivedEvent event;
    public SongResultHandler(GuildMessageReceivedEvent event) {
        this.event = event;
    }

    @Override
    public void trackLoaded(AudioTrack t) {
        event
                .getChannel()
                .sendMessage(":informational_source: **|** Enqueued: `" + t.getInfo().title + "`.")
                .queue();
        t.setUserData(event.getAuthor());
        // Hamakaze.getAudioVoiceHandler().enqueueTrack(t, event);
    }

    @Override
    public void playlistLoaded(AudioPlaylist playlist) {
        event.getChannel().sendMessage(":warning: **|** Found **" + playlist.getTracks().size() + "** songs! Enqueueing...").queue((m) -> {
            for (AudioTrack t: playlist.getTracks()) {
                // Hamakaze.getAudioVoiceHandler().enqueueTrack(t, event);
            }
            m.editMessage(":ok_hand: **|** Admiral, all " + playlist.getTracks().size() + " songs were added to the queue.").queue();
        });
    }

    @Override
    public void noMatches() {
        event.getChannel().sendMessage(":x: **|** Nothing found.").queue();
    }

    @Override
    public void loadFailed(FriendlyException ex) {
        event.getChannel().sendMessage(":x: **|** Admiral, I couldn't load the song: **`" + ex.getMessage() + "`**.").queue();
    }
}
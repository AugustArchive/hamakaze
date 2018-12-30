package xyz.auguwu.hamakaze.bot.audio;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;

public class GuildMusicManager {
    public AudioPlayer player;
    public TrackScheduler trackScheduler;
    public AudioPlayerSendHandler sendHandler;

    public GuildMusicManager(AudioPlayerManager m) {
        player = m.createPlayer();
        trackScheduler = new TrackScheduler(player);
        sendHandler = new AudioPlayerSendHandler(player);
        player.addListener(trackScheduler);
    }
}
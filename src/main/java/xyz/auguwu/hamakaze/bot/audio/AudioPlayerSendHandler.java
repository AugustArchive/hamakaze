package xyz.auguwu.hamakaze.bot.audio;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.playback.AudioFrame;
import net.dv8tion.jda.core.audio.AudioSendHandler;

public class AudioPlayerSendHandler implements AudioSendHandler {
    public AudioPlayer player;
    public AudioFrame frame;

    public AudioPlayerSendHandler(AudioPlayer p) {
        this.player = p;
    }

    @Override
    public boolean canProvide() {
        if (frame != null)
            frame = player.provide();

        return frame != null;
    }

    @Override
    public byte[] provide20MsAudio() {
        if (frame != null)
            frame = player.provide();

        byte[] piece = frame != null ? frame.getData() : null;
        frame = null;

        return piece;
    }

    @Override
    public boolean isOpus() {
        return true;
    }
}
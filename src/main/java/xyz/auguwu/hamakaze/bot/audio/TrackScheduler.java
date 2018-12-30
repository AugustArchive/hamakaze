package xyz.auguwu.hamakaze.bot.audio;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import net.dv8tion.jda.core.entities.User;

import java.util.LinkedList;
import java.util.Queue;

public class TrackScheduler extends AudioEventAdapter {
    public final AudioPlayer player;
    public final Queue<AudioTrack> queue;
    public AudioTrack last;
    private boolean isRepeat = false;
    private boolean isRepeatingQueue = false;

    public TrackScheduler(AudioPlayer p) {
        this.player = p;
        this.queue = new LinkedList<>();
    }

    public void queue(AudioTrack t) {
        if (!player.startTrack(t, true))
            queue.offer(t);
    }

    public void next() {
        player.startTrack(queue.poll(), false);
    }

    @Override
    public void onTrackEnd(AudioPlayer audioPlayer, AudioTrack t, AudioTrackEndReason reason) {
        this.last = t;
        User u = (User) t.getUserData();
        if (reason.mayStartNext) {
            AudioTrack l = t.makeClone();
            l.setUserData(u);
            if (isRepeat) {
                player.startTrack(l, false);
            }
            if (isRepeatingQueue) {
                queue.add(l);
                next();
            } else
                next();
        }
    }

    @Override
    public void onTrackStuck(AudioPlayer audioPlayer, AudioTrack t, long threshold) {
        next();
    }

    public boolean isRepeating() {
        return isRepeat;
    }

    public boolean isRepeatingQueue() {
        return isRepeatingQueue;
    }

    public void setRepeat(boolean i) {
        this.isRepeat = i;
    }

    public void setRepeatQueue(boolean u) {
        this.isRepeatingQueue = u;
    }
}
package xyz.auguwu.hamakaze.bot;

import com.sedmelluq.discord.lavaplayer.jdaudp.NativeAudioSendFactory;
import com.sedmelluq.discord.lavaplayer.player.AudioConfiguration;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.source.bandcamp.BandcampAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.beam.BeamAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.soundcloud.SoundCloudAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.twitch.TwitchStreamAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.vimeo.VimeoAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioSourceManager;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import xyz.auguwu.hamakaze.bot.audio.GuildMusicManager;
import xyz.auguwu.hamakaze.bot.core.listeners.*;
import xyz.auguwu.hamakaze.bot.core.managers.CommandManager;
import xyz.auguwu.hamakaze.bot.core.managers.ServiceManager;
import org.slf4j.*;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Properties;

public class Hamakaze {
    public Logger logger = LoggerFactory.getLogger(Hamakaze.class);
    public JDA jda;
    public static Hamakaze instance;
    public DefaultAudioPlayerManager playerManager = new DefaultAudioPlayerManager();
    public HashMap<String, GuildMusicManager> players = new HashMap<>();
    public Properties config;
    public CommandManager manager = new CommandManager();
    public ServiceManager services = new ServiceManager();

    public Hamakaze() throws Exception {
        instance = this;
        Thread.currentThread().setName("Hamakaze");
        logger.info("Admiral, I am booting to Discord!");
        config = loadConfiguration();
        playerManager.setPlayerCleanupThreshold(30000);
        playerManager.getConfiguration().setFilterHotSwapEnabled(true);
        loadSourceManagers();
        jda = new JDABuilder(AccountType.BOT)
                .setToken(config.getProperty("discord.token"))
                .setStatus(OnlineStatus.IDLE)
                .setGame(Game.listening("Admiral's words..."))
                .addEventListener(new EventListener(), new CommandListener())
                .setAudioSendFactory(new NativeAudioSendFactory())
                .build();
        manager.registerCommands();
        services.post();
    }

    private Properties loadConfiguration() throws Exception {
        FileReader fileReader = new FileReader("config.properties");
        config.load(fileReader);
        return config;
    }

    private void loadSourceManagers() {
        YoutubeAudioSourceManager yt = new YoutubeAudioSourceManager();
        playerManager.registerSourceManager(yt);
        playerManager.registerSourceManager(new SoundCloudAudioSourceManager());
        playerManager.registerSourceManager(new BandcampAudioSourceManager());
        playerManager.registerSourceManager(new TwitchStreamAudioSourceManager());
        playerManager.registerSourceManager(new BeamAudioSourceManager());
    }

    public static Hamakaze getInstance() {
        return instance;
    }
    public JDA getJDA() {
        return jda;
    }
    public HashMap<String, GuildMusicManager> getPlayers() {
        return players;
    }
    public CommandManager getCommandManager() {
        return manager;
    }
}
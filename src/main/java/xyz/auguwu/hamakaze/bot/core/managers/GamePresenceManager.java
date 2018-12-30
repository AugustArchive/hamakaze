package xyz.auguwu.hamakaze.bot.core.managers;

import xyz.auguwu.hamakaze.bot.Hamakaze;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.OnlineStatus;

public class GamePresenceManager {
    public static void setPresence() {
        Hamakaze
                .getInstance()
                .jda
                .getPresence()
                .setStatus(OnlineStatus.ONLINE);
        Hamakaze.getInstance().jda.getPresence().setGame(Game.playing("=>help | " + Hamakaze.getInstance().jda.getGuilds().size() + " Guilds"));
    }
}
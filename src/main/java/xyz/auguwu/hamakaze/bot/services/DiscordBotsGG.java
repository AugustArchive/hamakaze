package xyz.auguwu.hamakaze.bot.services;

import xyz.auguwu.hamakaze.bot.core.annotations.IService;
import xyz.auguwu.hamakaze.bot.core.interfaces.Service;
import xyz.auguwu.http.factories.RequestFactory;
import xyz.auguwu.hamakaze.bot.Hamakaze;
import net.dv8tion.jda.core.JDA;
import org.json.JSONObject;

@IService(enabled = false)
public class DiscordBotsGG implements Service {
    @Override
    public void run(JDA api) {
        JSONObject obj = new JSONObject().put("server_count", api.getGuilds().size());
        RequestFactory
                .post("https://bots.discord.gg/api/bots/" + api.getSelfUser().getId() + "/stats")
                .addHeader("Authorization", Hamakaze.getInstance().config.getProperty("botlist.mew"))
                .executePOST(obj,
                        (s) -> Hamakaze.getInstance().logger.info("Posted statistics to bots.discord.gg"),
                        (e) -> Hamakaze.getInstance().logger.error("Unable to post to bots.discord.gg: " + e.getMessage()));
    }
}
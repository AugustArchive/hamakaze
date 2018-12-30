package xyz.auguwu.hamakaze.bot.services;

import xyz.auguwu.hamakaze.bot.core.annotations.IService;
import xyz.auguwu.hamakaze.bot.core.interfaces.Service;
import xyz.auguwu.http.factories.RequestFactory;
import xyz.auguwu.hamakaze.bot.Hamakaze;
import net.dv8tion.jda.core.JDA;
import org.json.JSONObject;

@IService(enabled = false)
public class DiscordBoats implements Service {
    public void run(JDA api) {
        JSONObject obj = new JSONObject().put("server_count", api.getGuilds().size());
        RequestFactory
                .post("https://discord.boats/api/bots/" + api.getSelfUser().getId())
                .addHeader("Authorization", Hamakaze.getInstance().config.getProperty("botlists.unto"))
                .executePOST(obj,
                        (resp) -> Hamakaze.getInstance().logger.info("Posted " + api.getGuilds().size() + " guilds to discord.boats!"),
                        (err) -> Hamakaze.getInstance().logger.error("Unable to post to discord.boats: " + err.getMessage()));
    }
}
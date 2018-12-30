package xyz.auguwu.hamakaze.bot.services;

import xyz.auguwu.hamakaze.bot.core.annotations.IService;
import xyz.auguwu.hamakaze.bot.core.interfaces.Service;
import xyz.auguwu.http.factories.RequestFactory;
import xyz.auguwu.hamakaze.bot.Hamakaze;
import net.dv8tion.jda.core.JDA;
import org.json.JSONObject;

@IService(enabled = false)
public class BotsOnDiscord implements Service {
    public void run(JDA api) {
        JSONObject json = new JSONObject().put("server_count", api.getGuilds().size());
        RequestFactory
                .post("https://bots.ondiscord.xyz/bot-api/bots/" + api.getSelfUser().getId() + "/guilds")
                .addHeader("Authorization", Hamakaze.getInstance().config.getProperty("botlists.brussell"))
                .executePOST(json,
                        (resp) -> Hamakaze.getInstance().logger.info("Posted " + api.getGuilds().size() + " guilds to Bots on Discord!"),
                        (e) -> Hamakaze.getInstance().logger.error("Unable to post to bots.ondiscord.xyz: " + e.getMessage())
                );
    }
}
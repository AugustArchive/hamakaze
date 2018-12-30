package xyz.auguwu.hamakaze.bot.services;

import xyz.auguwu.hamakaze.bot.core.annotations.IService;
import xyz.auguwu.hamakaze.bot.core.interfaces.Service;
import xyz.auguwu.http.factories.RequestFactory;
import xyz.auguwu.hamakaze.bot.Hamakaze;
import net.dv8tion.jda.core.JDA;
import org.json.JSONObject;

@IService(enabled = false)
public class DiscordBotIndex implements Service {
    public void run(JDA api) {
        JSONObject obj = new JSONObject().put("server_count", api.getGuilds().size());
        RequestFactory
                .post("https://discordbotindex.com/apiv1/bots/" + api.getSelfUser().getId())
                .addHeader("Authorization", Hamakaze.getInstance().config.getProperty("botlists.derpy"))
                .executePOST(obj,
                        (resp) -> Hamakaze.getInstance().logger.info("Posted " + api.getGuilds().size() + " to discordbotindex.com!"),
                        (error) -> Hamakaze.getInstance().logger.error("Unable to post to discordbotindex.com: " + error.getMessage()));
    }
}
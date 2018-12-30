package xyz.auguwu.hamakaze.bot.services;

import xyz.auguwu.hamakaze.bot.core.annotations.IService;
import xyz.auguwu.hamakaze.bot.core.interfaces.Service;
import xyz.auguwu.http.factories.RequestFactory;
import net.dv8tion.jda.core.JDA;
import org.json.JSONObject;

@IService(enabled = false)
public class BotsOnDiscord implements Service {
    public void run(RequestFactory http, JDA api) {
        JSONObject awau = new JSONObject()
                .put("server_count", api.getGuilds().size());
    }
}
package xyz.auguwu.hamakaze.bot.core.managers;

import xyz.auguwu.hamakaze.bot.core.interfaces.Service;
import xyz.auguwu.hamakaze.bot.services.*;
import xyz.auguwu.hamakaze.bot.Hamakaze;

import java.util.HashMap;

public class ServiceManager {
    public HashMap<String, Service> services = new HashMap<>();
    public Hamakaze bot;

    public ServiceManager(Hamakaze bot) {
        this.bot = bot;
        this.registerService("discord.boats", new DiscordBoats());
        this.registerService("bots.ondiscord.xyz", new BotsOnDiscord());
        this.registerService("discordbotindex.com", new DiscordBotIndex());
        this.registerService("bots.discord.gg", new DiscordBotsGG());
    }

    public void registerService(String name, Service service) {
        if (service.get().enabled())
            services.put(name, service);
    }

    public void post() {
        services
                .entrySet()
                .stream()
                .forEach(s -> s.getValue().run(bot.getJDA()));
    }
}
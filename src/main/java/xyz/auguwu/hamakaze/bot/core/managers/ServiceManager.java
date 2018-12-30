package xyz.auguwu.hamakaze.bot.core.managers;

import xyz.auguwu.hamakaze.bot.core.interfaces.Service;

import java.util.HashMap;

public class ServiceManager {
    public HashMap<String, Service> services = new HashMap<>();

    /*
    public ServiceManager(Hamakaze bot) {
        this.bot = bot;
    }
     */

    public void registerService(String name, Service service) {
        services.put(name, service);
    }

    public void post() {
        services
                .entrySet()
                .stream();
                //.forEach(s -> s.getValue().run(bot.getJDA());
    }
}
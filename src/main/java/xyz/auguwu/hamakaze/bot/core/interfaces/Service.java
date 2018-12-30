package xyz.auguwu.hamakaze.bot.core.interfaces;

import xyz.auguwu.hamakaze.bot.core.annotations.IService;
import xyz.auguwu.http.factories.RequestFactory;
import net.dv8tion.jda.core.JDA;

public interface Service {
    void run(RequestFactory http, JDA api);
    default IService get() {
        return this.getClass().getAnnotation(IService.class);
    }
}
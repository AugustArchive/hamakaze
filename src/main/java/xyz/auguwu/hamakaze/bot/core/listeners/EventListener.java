package xyz.auguwu.hamakaze.bot.core.listeners;

import xyz.auguwu.hamakaze.bot.core.managers.GamePresenceManager;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.events.ReadyEvent;
import xyz.auguwu.hamakaze.bot.Hamakaze;

public class EventListener extends ListenerAdapter {
    @Override
    public void onReady(ReadyEvent e) {
        Hamakaze.getInstance().logger.info(e.getJDA().getSelfUser().getName() + "#" + e.getJDA().getSelfUser().getDiscriminator() + " is ready for duty!");
        GamePresenceManager.setPresence();

    }
}
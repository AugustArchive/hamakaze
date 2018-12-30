package xyz.auguwu.hamakaze.bot.core;

public enum CommandCategory {
    GENERIC("Generic"),
    MUSIC("Music"),
    SETTINGS("Settings");

    String key;
    CommandCategory(String k) {
        this.key = k;
    }

    public String toString() {
        return key;
    }
}
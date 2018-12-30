package xyz.auguwu.hamakaze.bot.util;

public class StringUtil {
    public static String elipisis(String str, int len) {
        return (str.length() > len ? str.substring(0, len - 3) + "..." : str);
    }
}
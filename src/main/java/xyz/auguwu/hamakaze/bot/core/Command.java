package xyz.auguwu.hamakaze.bot.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Command {
    String command();
    String description();
    String usage() default "";
    String[] aliases() default {};
    CommandCategory category() default CommandCategory.GENERIC;
}
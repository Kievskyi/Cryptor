package service;

import util.ColorEnum;
import util.EmojiD;

public class Logger {

    private static Logger logger;

    private Logger() {
    }

    public static Logger getInstance() {
        if (logger == null) {
            logger = new Logger();
        }
        return logger;
    }

    public void error(String incomingText) {
        System.out.println(ColorEnum.RED.getColor() + EmojiD.WARNING.getEmoji() + " " + incomingText + " " + EmojiD.WARNING.getEmoji() + ColorEnum.RESET.getColor());
    }

    public void info(String incomingText) {
        System.out.println(ColorEnum.GREEN.getColor() + EmojiD.INFO.getEmoji() + " " + incomingText + " " + EmojiD.INFO.getEmoji() + ColorEnum.RESET.getColor());
    }
}
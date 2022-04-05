package util;

public enum EmojiD {

    ROCKET("\uD83D\uDE80"),
    DEVIL("\uD83D\uDE08"),
    LOCK("\uD83D\uDD12"),
    UNLOCK("\uD83D\uDD13"),
    KEY("\uD83D\uDD11"),
    LOUPE("\uD83D\uDD0D"),
    STOP("\u274C"),
    BYE("\uD83D\uDE4B"),
    KEY_LOCK("\uD83D\uDD10"),
    WARNING("\u2757"),
    CLOCK("\uD83D\uDD58"),
    INFO("\uD83D\uDD35"),
    ZZZ("\uD83D\uDCA4");


    private String emoji;

    EmojiD(String emoji) {
        this.emoji = emoji;
    }

    public String getEmoji() {
        return emoji;
    }
}
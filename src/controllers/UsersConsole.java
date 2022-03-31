package controllers;

import dao.FileDataDao;
import service.BruteForce;
import service.CaesarCipher;
import service.Logger;
import util.ColorEnum;
import util.EmojiD;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;

public class UsersConsole {

    private final FileDataDao fileDataDao = new FileDataDao();
    private final CaesarCipher caesarCipher = new CaesarCipher();
    private final BruteForce bruteForce = new BruteForce();
    private final Logger logger = Logger.getInstance();

    public void startTheProgram() {
        printMainMenu();
    }

    private void printMainMenu() {
        makeYourChoice();
    }

    private void makeYourChoice() {
        do {
            printWelcomePage();
            Scanner scanner = new Scanner(System.in);
            int inputChoice = scanner.nextInt();

            int exit = 0;
            if (inputChoice == exit) {
                closeTheProgram(scanner);
                break;
            }

            switch (inputChoice) {
                case 1 -> {
                    encryptData();
                }
                case 2 -> {
                    decryptData();
                }
                case 3 -> {
                    decryptDataByBruteForce();
                }
            }
        } while (true);
    }

    private void decryptDataByBruteForce() {
        Map<Integer, String> decryptionOptions;
        Path path = getPath();
        String data = fileDataDao.getData(path);
        decryptionOptions = bruteForce.decrypt(data);
        String decryptedTextFromBF = getDecryptedTextFromBF(decryptionOptions);
        String dateAndTime = getDateAndTime();
        String formattedName = getFormattedNameOfFile(path);
        Path finalPath = Path.of(path.getParent().toString(), formattedName + " - decrypted by BF (" + " " + dateAndTime + ").txt");
        fileDataDao.writeData(decryptedTextFromBF, finalPath);
        logger.info(EmojiD.INFO.getEmoji() + " " + ColorEnum.BLUE.getColor() + "File with decrypted text successfully created"
                + ColorEnum.RESET.getColor() + " " + EmojiD.INFO.getEmoji());
        System.out.println();
        System.out.println();
    }

    private void decryptData() {
        Path path = getPath();
        int key = getKey();
        String data = fileDataDao.getData(path);
        String decryptedText = caesarCipher.decrypt(data, key);
        String dateAndTime = getDateAndTime();
        String formattedName = getFormattedNameOfFile(path);
        Path finalPath = Path.of(path.getParent().toString(), formattedName + " - decrypted (" + " " + dateAndTime + ").txt");
        fileDataDao.writeData(decryptedText, finalPath);
        logger.info(EmojiD.INFO.getEmoji() + " " + ColorEnum.BLUE.getColor() + "Your text has been successfully decrypted"
                + ColorEnum.RESET.getColor() + " " + EmojiD.INFO.getEmoji());
        System.out.println();
        System.out.println();
    }

    private void encryptData() {
        Path path = getPath();
        int key = getKey();
        String data = fileDataDao.getData(path);
        String encryptedText = caesarCipher.encrypt(data, key);
        String dateAndTime = getDateAndTime();
        String fileNameWithoutExtention = getFileNameWithoutExtention(path);
        Path finalPath = Path.of(path.getParent().toString(), fileNameWithoutExtention + " - encrypted (" + " " + dateAndTime + ").txt");
        fileDataDao.writeData(encryptedText, finalPath);
        System.out.println();
        logger.info(EmojiD.INFO.getEmoji() + " " + ColorEnum.BLUE.getColor() + "Your text has been successfully encrypted"
                + ColorEnum.RESET.getColor() + " " + EmojiD.INFO.getEmoji());
        System.out.println();
        System.out.println();
    }


    private String getDecryptedTextFromBF(Map<Integer, String> decryptionOptions) {
        System.out.println(ColorEnum.BLACK.getColor() + "__________________________________" + ColorEnum.RESET.getColor());
        System.out.println();
        System.out.println(EmojiD.KEY.getEmoji() + " " + ColorEnum.BG_WHITE.getColor()
                + "Chose the right key" + ColorEnum.RESET.getColor() + " " + EmojiD.KEY.getEmoji());
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        int key = scanner.nextInt();
        return decryptionOptions.get(key);
    }

    private String getFormattedNameOfFile(Path path) {
        String nameOfFile = Path.of(String.valueOf(path)).getFileName().toString();
        int i = nameOfFile.indexOf('-');
        if (i != -1) {
            nameOfFile = nameOfFile.substring(0, i);
        }
        return nameOfFile;
    }


    private String getFileNameWithoutExtention(Path path) {
        String formattedNameOfFile = Path.of(String.valueOf(path)).getFileName().toString();
        int i = formattedNameOfFile.lastIndexOf('.');
        if (i != -1) {
            formattedNameOfFile = formattedNameOfFile.substring(0, i);
        }
        return formattedNameOfFile;
    }

    private String getDateAndTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yy | HH-mm-ss");
        return dtf.format(LocalDateTime.now());
    }

    private void closeTheProgram(Scanner scanner) {
        System.out.println();
        printGoodBye();
        scanner.close();
    }

    private void printGoodBye() {
        int key = 0;
        String[] arr = {"G", "O", "O", "D", "B", "Y", "E"};
        int height = 3;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j <= 10; j++) {
                if (i == 0 && j < 8 || i == 2 && j < 8) {
                    System.out.print(EmojiD.ZZZ.getEmoji());
                } else if ((i == 1 && j < 2) || (i == 1 && j >= 9)) {
                    System.out.print(EmojiD.ZZZ.getEmoji());
                } else if (i == 1) {
                    System.out.print(ColorEnum.PURPLE.getColor() + arr[key] + ColorEnum.RESET.getColor());
                    key++;
                }
            }
            System.out.println();
        }
    }

    private Path getPath() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println(EmojiD.LOUPE.getEmoji() + " " + ColorEnum.BG_WHITE.getColor()
                + "Write below a path to the file" + ColorEnum.RESET.getColor() + " " + EmojiD.LOUPE.getEmoji());
        System.out.println();
        return Path.of(scanner.nextLine());
    }

    private int getKey() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println(EmojiD.KEY.getEmoji() + " " + ColorEnum.BG_WHITE.getColor()
                + "Write below yours secret number of key (from 0 till 73)" + ColorEnum.RESET.getColor() + " " + EmojiD.KEY.getEmoji());
        System.out.println();
        int dataKey = 0;
        try {
            dataKey = scanner.nextInt();
        } catch (Exception e) {
            System.out.println();
            System.out.println(EmojiD.WARNING.getEmoji() + ColorEnum.RED.getColor() + " Incorrect key "
                    + ColorEnum.RESET.getColor() + EmojiD.WARNING.getEmoji());
        }
        return dataKey;
    }

    private void printWelcomePage() {
        System.out.println();
        int height = 5;
        for (int i = 0; i < height; i++) {
            int length = 20;
            for (int j = 0; j < length; j++) {
                if (i == 0 || (i == 1 && j < 5) || (i == 1 && j > 14) || (i == 3 && j < 5) || (i == 3 && j > 14) || i == 4) {
                    System.out.print(EmojiD.DEVIL.getEmoji());
                } else if ((i == 2 && j < 5) || (i == 2 && j > 14)) {
                    System.out.print(EmojiD.DEVIL.getEmoji());
                } else if (i == 2 && j == 5) {
                    System.out.print(EmojiD.ROCKET.getEmoji() + ColorEnum.BG_BLACK.getColor() + "CRYPTOGRAPHER"
                            + ColorEnum.RESET.getColor() + EmojiD.ROCKET.getEmoji());
                } else if (i == 1 || i == 3) {
                    System.out.print(EmojiD.ROCKET.getEmoji());
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println(ColorEnum.BG_WHITE.getColor() + "Please, make your choice : " + ColorEnum.RESET.getColor());
        System.out.println();
        System.out.println(ColorEnum.BLACK.getColor() + "__________________________________________________" + ColorEnum.RESET.getColor());
        System.out.println();
        System.out.println(EmojiD.LOCK.getEmoji() + " " + ColorEnum.BG_BLACK.getColor() + " 1 - Encrypt the file "
                + ColorEnum.RESET.getColor() + " " + EmojiD.LOCK.getEmoji());
        System.out.println(EmojiD.UNLOCK.getEmoji() + " " + ColorEnum.BG_BLACK.getColor() + " 2 - Decrypt the file "
                + ColorEnum.RESET.getColor() + " " + EmojiD.UNLOCK.getEmoji());
        System.out.println(EmojiD.KEY_LOCK.getEmoji() + " " + ColorEnum.BG_BLACK.getColor() + " 3 - Search the key by brute force method "
                + ColorEnum.RESET.getColor() + " " + EmojiD.KEY_LOCK.getEmoji());
        System.out.println();
        System.out.println(EmojiD.STOP.getEmoji() + " " + ColorEnum.BG_BLACK.getColor() + " 0 - Exit "
                + ColorEnum.RESET.getColor() + " " + EmojiD.STOP.getEmoji());
        System.out.println(ColorEnum.BLACK.getColor() + "__________________________________________________" + ColorEnum.RESET.getColor());
        System.out.println();
        System.out.println(ColorEnum.BG_WHITE.getColor() + "Write below the number of your choice :" + ColorEnum.RESET.getColor());
        System.out.println();
    }
}
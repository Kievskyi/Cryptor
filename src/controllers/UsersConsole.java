package controllers;

import dao.FileDataDao;
import service.BruteForce;
import service.CaesarCipher;
import util.ConsoleColour;
import util.Emoji;

import java.util.Scanner;

public class UsersConsole {

    private Emoji emoji = new Emoji();
    private FileDataDao fileDataDao = new FileDataDao();
    private ConsoleColour color = new ConsoleColour();
    private CaesarCipher caesarCipher = new CaesarCipher();
    private BruteForce bruteForce = new BruteForce();

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

            if (inputChoice == 0) {
                closeTheProgram(scanner);
                break;
            }

            switch (inputChoice) {
                case 1 -> {
                    String path = getPath();
                    int key = getKey();
                    String data = fileDataDao.getData(path);
                    caesarCipher.encrypt(data, key, path);
                }
                case 2 -> {
                    String path = getPath();
                    int key = getKey();
                    String data = fileDataDao.getData(path);
                    caesarCipher.decrypt(data, key, path);
                }
                case 3 -> {
                    String path = getPath();
                    String data = fileDataDao.getData(path);
                    bruteForce.decrypt(data, path);
                }
            }
        } while (true);
    }

    private void closeTheProgram(Scanner scanner) {
        System.out.println();
        System.out.println(emoji.BYE + " " + color.BG_BLACK + "Goodbye!" + color.RESET + " " + emoji.BYE);
        scanner.close();
        return;
    }

    private String getPath() {
        Scanner scanner1 = new Scanner(System.in);
        System.out.println();
        System.out.println(emoji.LOUPE + " " + color.BG_WHITE + "Write below a path to the file" + color.RESET + " " + emoji.LOUPE);
        System.out.println();
        return scanner1.nextLine();
    }

    private int getKey() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println(emoji.KEY + " " + color.BG_WHITE + "Write below yours secret number of key (from 0 till 73)" + color.RESET + " " + emoji.KEY);
        System.out.println();
        int dataKey = 0;
        try {
            dataKey = scanner.nextInt();
        } catch (Exception e) {
            System.out.println();
            System.out.println(emoji.WARNING + color.RED + " Incorrect key " + color.RESET + emoji.WARNING);
        }
        return dataKey;
    }

    private void printWelcomePage() {
        System.out.println();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 20; j++) {
                if (i == 0 || (i == 1 && j < 5) || (i == 1 && j > 14) || (i == 3 && j < 5) || (i == 3 && j > 14) || i == 4) {
                    System.out.print(emoji.DEVIL);
                } else if ((i == 2 && j < 5) || (i == 2 && j > 14)) {
                    System.out.print(emoji.DEVIL);
                } else if (i == 2 && j == 5) {
                    System.out.print(emoji.ROCKET + color.BG_BLACK + "CRYPTOGRAPHER" + color.RESET + emoji.ROCKET);
                } else if (i == 1 || i == 3) {
                    System.out.print(emoji.ROCKET);
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println(color.BG_WHITE + "Please, make your choice : " + color.RESET);
        System.out.println();
        System.out.println(color.BLACK + "__________________________________________________" + color.RESET);
        System.out.println();
        System.out.println(emoji.LOCK + " " + color.BG_BLACK + " 1 - Encrypt the file " + color.RESET + " " + emoji.LOCK);
        System.out.println(emoji.UNLOCK + " " + color.BG_BLACK + " 2 - Decrypt the file " + color.RESET + " " + emoji.UNLOCK);
        System.out.println(emoji.KEY_LOCK + " " + color.BG_BLACK + " 3 - Search the key by brute force method " + color.RESET + " " + emoji.KEY_LOCK);
        System.out.println();
        System.out.println(emoji.STOP + " " + color.BG_BLACK + " 0 - Exit " + color.RESET + " " + emoji.STOP);
        System.out.println(color.BLACK + "__________________________________________________" + color.RESET);
        System.out.println();
        System.out.println(color.BG_WHITE + "Write below the number of your choice :" + color.RESET);
        System.out.println();
    }
}

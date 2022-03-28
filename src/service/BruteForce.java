package service;

import util.ConsoleColour;
import util.Emoji;

public class BruteForce {
    private static String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя,.!:?-\" АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    private Emoji emoji = new Emoji();
    private ConsoleColour color = new ConsoleColour();

    class charsPosition {

        int indexOfChar(char c) {
            for (int i = 0; i < ALPHABET.length(); i++) {
                if (ALPHABET.charAt(i) == c)
                    return i;
            }
            return -1;
        }

        char charAtIndex(int pos) {
            return ALPHABET.charAt(pos);
        }
    }

    public void decrypt(String data, String path) {

        charsPosition cp = new charsPosition();
        for (int k = 0; k < ALPHABET.length(); k++) {
            StringBuilder decryptedText = new StringBuilder();
            int key = k;
            for (int i = 0; i < data.length(); i++) {
                int index = cp.indexOfChar(data.charAt(i));

                if (index == -1) {
                    decryptedText.append(data.charAt(i));
                    continue;
                }
                if ((index - key) >= 0) {
                    decryptedText.append(cp.charAtIndex(index - key));
                } else {
                    decryptedText.append(cp.charAtIndex((index - key) + ALPHABET.length()));
                }
            }
            printDecryptedText(decryptedText.toString(), key);
        }
    }

    private void printDecryptedText(String decryptedText, int key) {
        if (decryptedText.length() < 40) {
            System.out.println(color.YELLOW + "Decrypted text using key " + color.RESET + emoji.KEY + " " + color.RED + key + color.RESET + " : " + decryptedText.substring(0, decryptedText.length()));
        } else {
            System.out.println(color.YELLOW + "Decrypted text using key " + color.RESET + emoji.KEY + " " + color.RED + key + color.RESET + " : " + decryptedText.substring(0, 40));
        }
    }
}
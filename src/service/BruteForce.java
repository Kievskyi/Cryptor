package service;

import util.ColorEnum;
import util.EmojiD;

import java.util.HashMap;
import java.util.Map;

public class BruteForce {
    private static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя,.!:?-\" АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    Map<Integer, String> decryptionOptions = new HashMap<>();


    public Map<Integer, String> decrypt(String data, String path) {

        charsPosition cp = new charsPosition();
        for (int i = 0; i < ALPHABET.length(); i++) {
            StringBuilder decryptedText = new StringBuilder();
            int key = i;
            for (int j = 0; j < data.length(); j++) {
                int index = cp.indexOfChar(data.charAt(j));

                if (index == -1) {
                    decryptedText.append(data.charAt(j));
                    continue;
                }
                if ((index - key) >= 0) {
                    decryptedText.append(cp.charAtIndex(index - key));
                } else {
                    decryptedText.append(cp.charAtIndex((index - key) + ALPHABET.length()));
                }
            }
            decryptionOptions.put(key, decryptedText.toString());
            printDecryptedText(decryptedText.toString(), key);
        }
        return decryptionOptions;
    }

    private static class charsPosition {

        int indexOfChar(char incomingChar) {
            for (int i = 0; i < ALPHABET.length(); i++) {
                if (ALPHABET.charAt(i) == incomingChar)
                    return i;
            }
            return -1;
        }

        char charAtIndex(int position) {
            return ALPHABET.charAt(position);
        }
    }

    private void printDecryptedText(String decryptedText, int key) {
        if (decryptedText.length() < 40) {
            System.out.println(ColorEnum.YELLOW.getColor() + "Decrypted text using key " + ColorEnum.RESET.getColor()
                    + EmojiD.KEY.getEmoji() + " " + ColorEnum.RED.getColor() + key + ColorEnum.RESET.getColor() + " : " + decryptedText);
        } else {
            System.out.println(ColorEnum.YELLOW.getColor() + "Decrypted text using key " + ColorEnum.RESET.getColor()
                    + EmojiD.KEY.getEmoji() + " " + ColorEnum.RED.getColor() + key + ColorEnum.RESET.getColor() + " : " + decryptedText.substring(0, 40));
        }
    }
}
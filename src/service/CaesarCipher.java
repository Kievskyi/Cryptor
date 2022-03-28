package service;

import dao.FileDataDao;

public class CaesarCipher implements Cryptographer {

    FileDataDao fileDataDao = new FileDataDao();
    private static String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя,.!:?-\" АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";

    @Override
    public void encrypt(String incomingFile, int key, String path) {

        char[] chars = incomingFile.toCharArray();
        char[] chars1 = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            int alphabetCharPosition = ALPHABET.indexOf(chars[i]);
            if (alphabetCharPosition >= 0) {
                if (alphabetCharPosition + key > ALPHABET.length()) {
                    chars1[i] = ALPHABET.charAt(alphabetCharPosition + key - ALPHABET.length());
                } else if (alphabetCharPosition + key == ALPHABET.length()) {
                    chars1[i] = ALPHABET.charAt(0);
                } else {
                    chars1[i] = ALPHABET.charAt(alphabetCharPosition + key);
                }
            } else {
                chars1[i] = chars[i];
            }
            fileDataDao.writeData(new String(chars1), path);
        }
    }

    @Override
    public void decrypt(String incomingFile, int key, String path) {
        char[] chars = incomingFile.toCharArray();
        char[] chars1 = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            int alphabetCharPosition = ALPHABET.indexOf(chars[i]);
            if (alphabetCharPosition >= 0) {
                if (alphabetCharPosition - key < 0) {
                    chars1[i] = ALPHABET.charAt(alphabetCharPosition - key + ALPHABET.length());
                } else {
                    chars1[i] = ALPHABET.charAt(alphabetCharPosition - key);
                }
            } else {
                chars1[i] = chars[i];
            }
            fileDataDao.writeData(new String(chars1), path);
        }
    }
}
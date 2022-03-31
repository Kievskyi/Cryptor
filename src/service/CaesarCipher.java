package service;

public class CaesarCipher implements Cryptographer {

    private static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя,.!:?-\" АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";

    @Override
    public String encrypt(String incomingFile, int key) {

        char[] incomingData = incomingFile.toCharArray();
        char[] encryptedData = new char[incomingData.length];
        for (int i = 0; i < incomingData.length; i++) {
            int alphabetCharPosition = ALPHABET.indexOf(incomingData[i]);
            if (alphabetCharPosition >= 0) {
                if (alphabetCharPosition + key > ALPHABET.length()) {
                    encryptedData[i] = ALPHABET.charAt(alphabetCharPosition + key - ALPHABET.length());
                } else if (alphabetCharPosition + key == ALPHABET.length()) {
                    encryptedData[i] = ALPHABET.charAt(0);
                } else {
                    encryptedData[i] = ALPHABET.charAt(alphabetCharPosition + key);
                }
            } else {
                encryptedData[i] = incomingData[i];
            }
        }
        return new String(encryptedData);
    }

    @Override
    public String decrypt(String incomingFile, int key) {
        char[] incomingData = incomingFile.toCharArray();
        char[] encryptedData = new char[incomingData.length];
        for (int i = 0; i < incomingData.length; i++) {
            int alphabetCharPosition = ALPHABET.indexOf(incomingData[i]);
            if (alphabetCharPosition >= 0) {
                if (alphabetCharPosition - key < 0) {
                    encryptedData[i] = ALPHABET.charAt(alphabetCharPosition - key + ALPHABET.length());
                } else {
                    encryptedData[i] = ALPHABET.charAt(alphabetCharPosition - key);
                }
            } else {
                encryptedData[i] = incomingData[i];
            }
        }
        return new String(encryptedData);
    }
}
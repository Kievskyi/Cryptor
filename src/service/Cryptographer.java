package service;

public interface Cryptographer {

    String encrypt(String incomingFile, int key);

    String decrypt(String incomingFile, int key);

}

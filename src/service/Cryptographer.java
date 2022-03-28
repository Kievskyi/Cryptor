package service;

public interface Cryptographer {

    void encrypt(String incomingFile, int key, String path);

    void decrypt(String incomingFile, int key, String path);

}

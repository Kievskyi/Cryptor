package dao;

public interface dataDao {

    String getData(String incomingFile);

    void writeData(String incomingFile, String path);
}

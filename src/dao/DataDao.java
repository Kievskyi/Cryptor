package dao;

public interface DataDao {

    String getData(String incomingFile);

    void writeData(String incomingFile, String path);
}
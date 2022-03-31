package dao;

import java.nio.file.Path;

public interface DataDao {

    String getData(Path incomingFile);

    void writeData(String incomingFile, Path path);
}
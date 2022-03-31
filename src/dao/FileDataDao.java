package dao;

import service.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileDataDao implements DataDao {
    private final Logger log = Logger.getInstance();

    @Override
    public String getData(String incomingFilePath) {
        byte[] data = new byte[0];
        try {
            data = Files.readAllBytes(Path.of(incomingFilePath));
        } catch (IOException e) {
            System.out.println();
            log.error(e.getMessage());
        }
        return new String(data);
    }

    @Override
    public void writeData(String incomingFile, String path) {
        try {
            Files.write(Path.of(path), incomingFile.getBytes());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
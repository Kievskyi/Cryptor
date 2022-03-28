package dao;

import util.ConsoleColour;
import util.Emoji;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

public class FileDataDao implements dataDao {
    private ConsoleColour color = new ConsoleColour();
    private Emoji emoji = new Emoji();

    @Override
    public String getData(String incomingFilePath) {
        byte[] data = new byte[0];
        try {
            data = Files.readAllBytes(Path.of(incomingFilePath));
        } catch (FileNotFoundException | NoSuchFileException e) {
            System.out.println();
            System.out.println(emoji.WARNING + color.RED + " File not found! Try again " + color.RESET + emoji.WARNING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(data);
    }

    @Override
    public void writeData(String incomingFile, String path) {
        try {
            Files.write(Path.of(path), incomingFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

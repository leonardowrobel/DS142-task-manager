package ufpr.lw.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import ufpr.lw.model.User;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    private final String USER_FILE_NAME = "task-manager-data.json";

    private File dataFile;

    public FileService() {
        this.dataFile = new File(USER_FILE_NAME);
    }

    public String readAllCharactersOneByOne(Reader reader) throws IOException {
        StringBuilder content = new StringBuilder();
        int nextChar;
        while ((nextChar = reader.read()) != -1) {
            content.append((char) nextChar);
        }
        return String.valueOf(content);
    }

    public boolean fileExists() {
        return (dataFile.exists() && !dataFile.isDirectory());
    }


    public void createFile() throws IOException {
        if (fileExists()) {
            return;
        }

        if(!dataFile.createNewFile())
            return;

        ObjectMapper objectMapper = new ObjectMapper();
        List<User> users = new ArrayList<>();

        objectMapper.writeValue(dataFile, users);
    }

    public void saveUsersIntoFile(List<User> users) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(dataFile, users);
    }
    public File getDataFile(){
        return dataFile;
    }

}

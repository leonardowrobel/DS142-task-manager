package ufpr.lw.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import ufpr.lw.exception.SessionException;
import ufpr.lw.model.User;
import ufpr.lw.service.FileService;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserDataRepository {

    private FileService fileService;

    public UserDataRepository() {
        this.fileService = new FileService();
    }

    List<User> getUsers() throws IOException {
        if (!fileService.fileExists()) {
            throw new IOException("File not valid!");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        Reader reader = new FileReader(fileService.getDataFile());

        return objectMapper.readValue(fileService.readAllCharactersOneByOne(reader), new TypeReference<>() {});
    }

    public User getUserById(UUID uuid) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Reader reader = new FileReader(fileService.getDataFile());
        List<User> users = objectMapper.readValue(fileService.readAllCharactersOneByOne(reader), new TypeReference<>() {
        });

        Optional<User> optional = users.stream().filter(user -> user.getId().compareTo(uuid) == 0).findFirst();

        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new IOException("User not found");
        }
    }

    public User getUserByUsername(String username) throws IOException, SessionException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        Reader reader = new FileReader(fileService.getDataFile());
        List<User> users = objectMapper.readValue(fileService.readAllCharactersOneByOne(reader), new TypeReference<>() {
        });

        Optional<User> optional = users.stream().filter(user -> user.getUsername().compareTo(username) == 0).findFirst();

        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new SessionException("User not found");
        }
    }

    public User insertUser(User user) throws IOException, NoSuchAlgorithmException {
        List<User> users = this.getUsers();
        user.setPassword(toHexString(getSHA(user.getPassword())));
        users.add(user);
        fileService.saveUsersIntoFile(users);
        return user;
    }

    public User saveUser(User user) throws IOException {
        List<User> users = this.getUsers();
        users = users.stream().map(item->item.getId().equals(user.getId()) ? user : item).collect(Collectors.toList());
        fileService.saveUsersIntoFile(users);
        return user;
    }

    // TODO: move to proper class
    public byte[] getSHA(String input) throws NoSuchAlgorithmException {
        // Static getInstance method is called with hashing SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // digest() method called
        // to calculate message digest of an input
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    // TODO: move to proper class
    public String toHexString(byte[] hash) {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 64) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

}

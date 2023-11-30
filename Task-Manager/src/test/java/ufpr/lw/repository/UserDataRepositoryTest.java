package ufpr.lw.repository;

import org.junit.jupiter.api.Test;
import ufpr.lw.model.User;
import ufpr.lw.service.FileService;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

class UserDataRepositoryTest {
    UserDataRepository userDataRepository;
    FileService fileService;

//    public UserDataRepositoryTest() {
//        this.userDataRepository = new UserDataRepository();
//        this.fileService = new FileService();
//    }
//
//    @Test
//    public void createNewFile() {
//        try {
//            fileService.createFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Test
//    public void insertUser() throws IOException, NoSuchAlgorithmException {
//        User user = new User();
//        user.setUsername("nickname");
//
//        userDataRepository.insertUser(user);
//        List<User> users = userDataRepository.getUsers();
//        assert !users.isEmpty();
//    }
//
//    @Test
//    public void insertUsers() throws IOException, NoSuchAlgorithmException {
//        User userA = new User(
//                "John02",
//                userDataRepository.toHexString(userDataRepository.getSHA("passworda"))
//        );
//        User userB = new User(
//                "Bob16",
//                userDataRepository.toHexString(userDataRepository.getSHA("passwordb"))
//        );
//        User userC = new User(
//                "Maria_1998",
//                userDataRepository.toHexString(userDataRepository.getSHA("passwordc"))
//        );
//        userDataRepository.insertUser(userA);
//        userDataRepository.insertUser(userB);
//        userDataRepository.insertUser(userC);
//        List<User> users = userDataRepository.getUsers();
//        assert !users.isEmpty();
//    }
//    @Test
//    public void getUserByUsername() throws IOException {
//        String username = "Maria_1998";
////        User user = userDataRepository.getUserByUsername(username);
////        assert user != null;
//    }
//
//    @Test
//    public void getData() throws IOException {
//        List<User> users = userDataRepository.getUsers();
//        assert !users.isEmpty();
//    }
//
//    @org.junit.jupiter.api.Test
//    void name() {
//    }
}
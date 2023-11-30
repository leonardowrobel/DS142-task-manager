package ufpr.lw.service;

import ufpr.lw.exception.SessionException;
import ufpr.lw.model.User;
import ufpr.lw.repository.UserDataRepository;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class SessionService {

    private UserService userService;
    private UserDataRepository userDataRepository;

    public SessionService() {
        this.userService = new UserService();
        this.userDataRepository = new UserDataRepository();
    }

    public User login(String username, String password) throws IOException, SessionException, NoSuchAlgorithmException {
        User user = userService.getUserByUsername(username);
        if(user.getPassword().compareTo(userDataRepository.toHexString(userDataRepository.getSHA(password))) == 0){
            user.setActive(true);
            return user;
        }
        System.out.println("Password is incorrect!");
        return new User();
    }

}

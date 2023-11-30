package ufpr.lw.service;

import ufpr.lw.exception.SessionException;
import ufpr.lw.model.Task;
import ufpr.lw.model.User;
import ufpr.lw.repository.UserDataRepository;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private UserDataRepository userDataRepository;

    public UserService() {
        this.userDataRepository = new UserDataRepository();
    }

    public User getUserByUsername(String username) throws IOException, SessionException {
        return userDataRepository.getUserByUsername(username);
    }

    public boolean usernameAlreadyInUse(String username){
        try {
            return userDataRepository.getUserByUsername(username) != null;
        } catch (SessionException | IOException e) {
            return false;
        }
    }

    public User updateUser(User user) throws IOException {
        return userDataRepository.saveUser(user);
    }

    public User createUser(User user) throws IOException, NoSuchAlgorithmException {
        return userDataRepository.insertUser(user);
    }

    public User createTask(Task task, User user) throws IOException {
        List<Task> userTasks = user.getTasks();
//        User.TaskList userTasks = user.getMyTasks();
        if(userTasks == null){
            userTasks = new ArrayList<>();
        }
        userTasks.add(task);
        user.setTasks(userTasks);
//        user.setMyTasks(userTasks);
        return userDataRepository.saveUser(user);
    }
}
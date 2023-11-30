package ufpr.lw.controller;

import ufpr.lw.exception.SessionException;
import ufpr.lw.model.Task;
import ufpr.lw.model.User;
import ufpr.lw.service.SessionService;
import ufpr.lw.service.UserService;
import ufpr.lw.view.Menu;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MenuController {

    private UserService userService;
    private SessionService sessionService;

    public MenuController() {
        this.userService = new UserService();
        this.sessionService = new SessionService();
    }

    public User processMenuEntry(int option, User user, Scanner scanner) throws IOException, NoSuchAlgorithmException {
        if (user.isActive()) {
            switch (option) {
                case 1:
                    return createNewTask(scanner, user);
                case 2:
                    return listAllTasks(user);
                case 3:
                    return doTask(user, scanner);
                case 4:
                    return deleteTask(user, scanner);
                case 5:
                    return logoutUser();
                default:
                    System.out.println("Option not available.");
                    return user;
            }
        } else {
            switch (option) {
                case 1:
                    return loginUser(scanner);
                case 2:
                    return createNewUser(scanner);
                case 3:
                    exitProgram();
                    break;
                default:
                    System.out.println("Option not available.");
                    return user;
            }
        }
        return user;
    }

    private User deleteTask(User user, Scanner scanner) throws IOException {
        if(user.getTasks() == null || user.getTasks().isEmpty()){
            System.out.println("Theres no tasks!");
            return user;
        }
        int num = 1;
        for (Task task : user.getTasks()){
            System.out.println(num + ". " + task.getDescription() + " - " + (task.getDone() ? "Done" : "To do"));
            num++;
        }
        System.out.println("Witch one?");
        scanner.nextLine();
        int op = scanner.nextInt();
        if(op > 0 && op <= num){
            List<Task> tasks = user.getTasks();
            tasks.remove(op-1);
            user.setTasks(tasks);
            return userService.updateUser(user);
        } else {
            System.out.println("Invalid task number!");
        }

        return user;
    }

    private User doTask(User user, Scanner scanner) throws IOException {
        if(user.getTasks() == null){
            System.out.println("Theres no tasks!");
            return user;
        }
        int num = 1;
        for (Task task : user.getTasks()){
            if(!task.getDone()){
                System.out.println(num + ". " + task.getDescription());
                num++;
            }
        }
        if(num == 1){
            System.out.println("Everything is done!");
            return user;
        }
        System.out.println("Witch one?");
        scanner.nextLine();
        int op = scanner.nextInt();
        if(op > 0 && op <= num){
            List<Task> tasks = user.getTasks();
            Task taskToChange = user.getTasks().stream().filter(task -> !task.getDone()).collect(Collectors.toList()).get(op-1);
            taskToChange.setDone(true);
            ListIterator<Task> iterator = tasks.listIterator();
            while (iterator.hasNext()) {
                Task next = iterator.next();
                if (next.equals(taskToChange)) {
                    iterator.set(taskToChange);
                }
            }
            user.setTasks(tasks);
            return userService.updateUser(user);
        } else {
            System.out.println("Invalid task number!");
        }

        return user;
    }

    private User logoutUser() {
        return new User();
    }

    private User loginUser(Scanner scanner) {
        String username;
        System.out.print("User: ");
        scanner.nextLine();
        username = scanner.nextLine();
        System.out.print("\nPassword: ");
        String password = scanner.nextLine();
        try {
            return sessionService.login(username, password);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SessionException e) {
            System.out.println(e.getMessage() != null ? e.getMessage() : "User not found!!");
            return new User();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private void exitProgram() {
        System.out.println("Good bye!");
        System.exit(0);
    }

    public void showMainMenu(User user) {
        if (user.isActive()) {
            Menu.printMenu(Menu.LOGGED_IN_OPTIONS);
        } else {
            Menu.printMenu(Menu.OPTIONS);
        }
    }

    public User createNewUser(Scanner scanner) throws IOException, NoSuchAlgorithmException {
        System.out.println("Creating new user:");
        String username;
        while (true) {
            System.out.print("User: ");
            scanner.nextLine();
            username = scanner.nextLine();
            if (userService.usernameAlreadyInUse(username)) {
                System.out.println("This user name is already in use, please try another one.");
            } else {
                break;
            }
        }
        System.out.print("\nPassword: ");
        String password = scanner.nextLine();
        User user = new User(username, password);
        return userService.createUser(user);
    }

    public User createNewTask(Scanner scanner, User user) throws IOException, NoSuchAlgorithmException {
        System.out.println("Creating new task:");
        System.out.print("Description: ");
        scanner.nextLine();
        String description = scanner.nextLine();
        Task newTask = new Task(description);

        return userService.createTask(newTask, user);
    }

    public User listAllTasks(User user){
        if(user.getTasks() != null || !user.getTasks().isEmpty()){
            int num = 1;
            for (Task task : user.getTasks()){
                System.out.println(num + ". " + task.getDescription() + " - " + (task.getDone() ? "Done" : "To do"));
                num++;
            }
        } else {
            System.out.println("There's no tasks!");
        }
        return user;
    }

}

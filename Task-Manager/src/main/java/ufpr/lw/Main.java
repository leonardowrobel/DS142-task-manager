package ufpr.lw;

import ufpr.lw.controller.MenuController;
import ufpr.lw.model.User;
import ufpr.lw.view.Menu;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        User myUser = new User();
        MenuController menuController = new MenuController();

        Scanner scanner = new Scanner(System.in);
        int option = 0;
        while (true) {
            menuController.showMainMenu(myUser);
            try {
                option = scanner.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Please enter an integer value between 1 and " + Menu.LOGGED_IN_OPTIONS.length);
                scanner.next();
            } catch (Exception ex) {
                System.out.println("An unexpected error happened. Please try again");
                scanner.next();
            }

            try {
                myUser = menuController.processMenuEntry(option, myUser, scanner);
            } catch (IOException | NoSuchAlgorithmException e) {
                System.out.println("ERROR: ");
                System.out.println(e.fillInStackTrace());
                throw new RuntimeException(e);
            }

            System.out.println();
        }
    }
}
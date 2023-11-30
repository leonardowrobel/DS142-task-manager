package ufpr.lw.view;

public class Menu {
    public static final String[] LOGGED_IN_OPTIONS = {"Create task", "List tasks", "Do task", "Remove task", "Logout"};
    public static final String[] OPTIONS = {"Login", "Create new account", "Exit"};

    public static void printMenu(String[] options) {
        int optN = 1;
        for (String option : options) {
            System.out.println(optN + ". " + option);
            optN++;
        }
        System.out.print("Choose your option : ");
    }
}

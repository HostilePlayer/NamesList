import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class NamesList {

    private ArrayList<String> names;

    public NamesList() {
        names = new ArrayList<>();
    }

    public void startUserInterface() throws IOException {

        System.out.println("""
                Welcome to the NamesList - enterprise edition.
                ----------------------------------------------
                """);

        Scanner sc = new Scanner(System.in);
        int choice = 99;
        while( choice != 0) {
            showMenu();
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> displayListOfNames();
                case 2 -> loadListOfNames();
                case 3 -> saveListOfNames();
                case 4 -> enterNames();
                case 0 -> exit();
                default -> System.out.println("Unknown command - please use 0-4");
            }

        }
    }

    private void showMenu() {
        System.out.println("""
                1) Display list of names
                2) Load list of names 
                3) Save list of names (not implemented)
                4) Enter names
                0) Exit
                """);
    }

    private void enterNames() {
        System.out.println("""
                Enter names
                -----------
                Enter each name you want to add to the list. End by entering an empty name.
                """);
        Scanner sc = new Scanner(System.in);
        String name = "-nothing yet-";
        while(!name.isBlank() && sc.hasNextLine()) {
            name = sc.nextLine();
            if(!name.isBlank()) {
                names.add(name);
                System.out.println(name + " added to the list, enter another, or empty to quit");
            }
        }
        System.out.println("Done");
    }

    private void saveListOfNames() throws IOException {
        FileWriter writer = new FileWriter("ListOfNames.txt");
        int len = names.size();

        for (int i = 0; i < len; i++) {
            writer.write(names.get(i) + "\n");
        }
        writer.close();
    }

    private void loadListOfNames(){
        File file = new File("names.txt");

        Scanner sc = null;
        try {
            sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                names.add(line);
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
    }

    private void displayListOfNames() {
        for(String name : names) {
            System.out.println(name);
        }
        String isAre = "are";
        String s = "s";
        if(names.size() == 1) {
            isAre = "is";
            s = "";
        }
        System.out.println("There " + isAre + " " + names.size() + " name"+s+" in the system");
    }

    private void exit() {
        System.out.println("""
                ...
                Thank you for using NamesList - enterprise edition.
                Don't forget to subscribe to our newsletter!""");

    }


    public static void main(String[] args) throws IOException {
        NamesList app = new NamesList();
        app.startUserInterface();
    }
}

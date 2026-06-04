import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AppSystem {

    public void startMainMenu(Scanner sc, ArrayList<User> user, Cafetaria cafe, ArrayList<Cafetaria> cafeList) {
        boolean isloged = true;
        while (isloged) {
            System.out.println("                                                                                    ^    ^");
            System.out.println("                         -- WELCOME TO THE --                                      / \\--/ |");
            System.out.println("¸.·´¯`·.¸.·´¯`·.¸.·´¯`·.¸.·´¯`·.¸.·´¯`·.¸.·´¯`·.¸.·´¯`·.¸.·´¯`·.¸.·´¯`·.¸.·´¯`·.¸  (  o  o)");
            System.out.println("_.-.              _   |/-._   ..          ,..  ,.        _____ ___  ___ .   .       \\  ^ /\n" +
                    ".|'     ,\\  /     /\\.  ||  '|  ||        /''   ,/. |\\  |   |   |    |    |\\. |      /'-\"-|\n" +
                    " '\"..   /\\\\//\\   // \\  ||_,,'--||--'    .'     / \\ |'\\ |   |   |___ |,__ | \\ |    _/ < ; (;\n" +
                    "    |  //+\\/ \\\\ //---\\ ||-(.   ||       |.    |,,.||  \\|   |   |    |    |  \\|   / ,_ |_|_\\\n" +
                    "`,_,' -/     '|//    `\\|'  '\\. `'-==     ''-.'|   \\|   \\   |   |,__ |,__ |   \\   ( _,,)\\,,),)");
            System.out.println("¸.·´¯`·.¸.·´¯`·.¸.·´¯`·.¸.·´¯`·.¸.·´¯`·.¸.·´¯`·.¸.·´¯`·.¸.·´¯`·.¸.·´¯`·..·´¯`·.¸ \\ '.___,");
            System.out.println("1. Register                                                                        '-----'");
            System.out.println("2. Login");
            System.out.println("3. Switch cafeteria");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            try {
                int n = sc.nextInt();
                switch (n) {
                    case 1:
                        register(sc, user, cafe);
                        break;

                    case 2:
                        login(sc, user, cafe);
                        break;

                    case 3:
                        cafe = switchCafe(sc, cafeList);
                        break;

                    case 0:
                        System.out.println("╔────────────────────╗\n" +
                                           "│      Goodbye!      │\n" +
                                           "╚────────────────────╝");
                        isloged = false;
                        break;

                    default:
                        System.out.println("⚠ - Wrong input! - ");
                }
            } catch (InputMismatchException e) {
                System.out.println("⚠ - Input with number! - ");
                sc.next(); // tanpa perintah ini maka infinite loop
            }

        }
    }

    private Cafetaria switchCafe(Scanner sc, ArrayList<Cafetaria> cafeList) {
        boolean isOn = true;
        while (isOn) {
            System.out.println("¸,ø¤°`°¤ø,¸¸,ø¤°`°¤ø,¸   PICK CAFETERIA   ¸,ø¤°`°¤ø,¸¸,ø¤°`°¤ø,¸");
            for (int i = 0; i < cafeList.size(); i++) { // display all cafeteria
                System.out.println(i+1+". " + cafeList.get(i).getNamaCafeteria());
            }
            System.out.println("0. Back");
            System.out.print("Choice: ");
            try {
                int n = sc.nextInt();

                if (n > 0 && n <= cafeList.size()) {
                    return cafeList.get(n-1);

                } else if (n == 0){
                    isOn = false;

                } else {
                    System.out.println("⚠ - Invalid Input! - ");
                }


            } catch (InputMismatchException e) {
                System.out.println("⚠ - Input with number! - ");
                sc.next();
            }
        }
        return cafeList.get(0);
    }


    public static void register(Scanner sc, ArrayList<User> user, Cafetaria cafe) {
        sc.nextLine();

        System.out.print("\n - username: ");
        String n = sc.nextLine();
        System.out.print(" - password: ");
        String n2 = sc.nextLine();
        System.out.print(" - Confirm password: ");
        String n3 = sc.nextLine();

        if (n2.equals(n3)) {

            if (!n.trim().isEmpty() && !n2.isEmpty() && !n3.isEmpty()) {
                System.out.println("\nRoles: ");
                System.out.println("1. siswa SMP");
                System.out.println("2. Siswa SMA");

                int n4 = 0; //roles
                int n5 = 0; //berat badan
                boolean n4Filled = true;
                while (n4Filled) {
                    System.out.print("choose: ");
                    try {
                        n4 = sc.nextInt(); //choose roles
                        if (n4 == 1 || n4 == 2) {

                            while (n4Filled) {
                                System.out.print(" - Berat badan: ");
                                try {
                                    n5 = sc.nextInt(); //berat badan input
                                    if (n5 > 0) {
                                        n4Filled = false;
                                        break;
                                    }

                                } catch (InputMismatchException e) {
                                    System.out.println("⚠ - Input with number! -");
                                    sc.next();
                                }
                            }

                        } else {
                            System.out.println("⚠ - Wrong input! choose between 1 or 2. -");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("⚠ - Input with number! - ");
                        sc.next();
                    }
                }

                if (n4Filled == false) {
                    System.out.println(" _._     _,-'\"\"`-._\n" +
                            "(,-.`._,'(       |\\`-/|\n" +
                            "    `-.-' \\ )-`( , o o)\n" +
                            "          `-    \\`_`\"'-");

                    boolean usernameExist = false;

                    for (User u : user) {
                        if (u.getNama().equalsIgnoreCase(n)) {
                            usernameExist = true;
                            break;
                        }
                    }

                    if (usernameExist) {
                        System.out.println("⚠ Username already exists!");
                        return;
                    }

                    if (n4 == 1) {
                        User newUser = new User(n, n3, n5, 999900, Main.Roles.SMP.getRoleName());
                        user.add(newUser);
                        System.out.println("=== Created new account! ===");
                        newUser.menuUtama(user, sc, cafe);

                    } else if (n4 == 2) {
                        User newUser = new User(n, n3, n5, 999990, Main.Roles.SMA.getRoleName());
                        user.add(newUser);
                        System.out.println("=== Created new account! ===");
                        newUser.menuUtama(user, sc, cafe);
                    }
                }
            }
        } else {
            System.out.println("⚠ === match the password! ===");
        }
    }

    public static void login(Scanner sc, ArrayList<User> user, Cafetaria cafe) {
        sc.nextLine();
        System.out.print("\n - Enter username: ");
        String n = sc.nextLine();
        System.out.print(" - Enter password: ");
        String n2 = sc.nextLine();

        boolean accFound = false;

        if (!n.isEmpty() && !n2.isEmpty()) {
            for (int i = 0; i < user.size(); i++) {
                if (user.get(i).getNama().equals(n) && user.get(i).getPassword().equals(n2)) {
                    System.out.println(" _._     _,-'\"\"`-._\n" +
                            "(,-.`._,'(       |\\`-/|\n" +
                            "    `-.-' \\ )-`( , o o)\n" +
                            "          `-    \\`_`\"'-");
                    System.out.println("=== Logged in successfully! ===");
                    user.get(i).menuUtama(user, sc, cafe);
                    accFound = true;
                    break;
                }
            }
            if (accFound == false) {
                System.out.println("⚠ === Invalid Username or password! ===");
            }
        } else {
            System.out.println("⚠ === username or password cannot be empty! ===");
        }
    }
}

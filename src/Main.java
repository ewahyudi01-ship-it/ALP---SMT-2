//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

    enum Roles {
        OWNER ("Owner"),
        SMA ("SMA"),
        SMP ("SMP");

        private  String name;

        Roles (String name){
            this.name = name;
        }

        String getRoleName(){
            return name;
        }
    }

    // bahan baku
    public static ArrayList<BahanBaku> bahanBakuList = new ArrayList<>(); // static karena dipakai 2 file dan banyak objek

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<User> user = new ArrayList<>(); // <--- user saat ini
        user.add(new User("Owner", "smart121", 65, 40000,"Owner"));

        ArrayList<FoodItem> foodList = new ArrayList<>();
        // food jadi
        foodList.add(new FoodJadi("Apple", 50, 3000, 20, 0, 25));
        foodList.add(new FoodJadi("Snack beng-beng (regular)", 100, 9000, 11, 1, 10));
        foodList.add(new FoodJadi("Pringles (small)", 98, 11000, 1, 16, 6));
        foodList.add(new FoodJadi("Bottle Aqua", 0, 6000, 0, 0, 30));
        foodList.add(new FoodJadi("Salad", 12, 8000, 5, 0, 19));
        foodList.add(new FoodJadi("Indomilk 100ml", 72, 5000, 4, 3, 14));

        // memasukan bahan baku ke stock bahan baku
        bahanBakuList.add(new BahanBaku("beef", 5));
        bahanBakuList.add(new BahanBaku("potato", 10));
        bahanBakuList.add(new BahanBaku("butter", 8));
        bahanBakuList.add(new BahanBaku("white bread", 6));
        bahanBakuList.add(new BahanBaku("margarine", 5));
        bahanBakuList.add(new BahanBaku("condensed milk", 5));
        bahanBakuList.add(new BahanBaku("rice", 5));
        bahanBakuList.add(new BahanBaku("egg", 5));
        bahanBakuList.add(new BahanBaku("chicken", 5));
        bahanBakuList.add(new BahanBaku("sweet soy sauce", 5));
        bahanBakuList.add(new BahanBaku("vegetable oil", 5));
        bahanBakuList.add(new BahanBaku("spring roll wrapper", 5));
        bahanBakuList.add(new BahanBaku("bamboo shoots", 5));
        bahanBakuList.add(new BahanBaku("chicken", 5));
        bahanBakuList.add(new BahanBaku("sugar", 5));

        // food masak
        FoodItem steak = new FoodMasak("Steak", 23000,0);
        ((FoodMasak) steak).setWaktuBuat(320);
        ((FoodMasak) steak).tambahResep("beef");
        ((FoodMasak) steak).tambahResep("potato");
        ((FoodMasak) steak).tambahResep("butter");

        FoodItem toastBread =  new FoodMasak("Toast Bread", 6500,0);
        ((FoodMasak) toastBread).setWaktuBuat(230);
        ((FoodMasak) toastBread).tambahResep("white bread");
        ((FoodMasak) toastBread).tambahResep("margarine");
        ((FoodMasak) toastBread).tambahResep("condensed milk");

        FoodItem nasiGoreng = new FoodMasak("Nasi Goreng",17000,0);
        ((FoodMasak) nasiGoreng).setWaktuBuat(450);
        ((FoodMasak) nasiGoreng).tambahResep("rice");
        ((FoodMasak) nasiGoreng).tambahResep("egg");
        ((FoodMasak) nasiGoreng).tambahResep("chicken");
        ((FoodMasak) nasiGoreng).tambahResep("sweet soy sauce");
        ((FoodMasak) nasiGoreng).tambahResep("vegetable oil");

        FoodItem lumpia = new FoodMasak("Lumpia",9800,0);
        ((FoodMasak) lumpia).setWaktuBuat(10);
        ((FoodMasak) lumpia).tambahResep("spring roll wrapper");
        ((FoodMasak) lumpia).tambahResep("bamboo shoots");
        ((FoodMasak) lumpia).tambahResep("chicken");
        ((FoodMasak) lumpia).tambahResep("sugar");

        foodList.add(steak);
        foodList.add(toastBread);
        foodList.add(nasiGoreng);
        foodList.add(lumpia);

        ArrayList<Menu> menuList = new ArrayList<>();
        menuList.add(new Menu("Menu utama", foodList));
        Cafetaria cafe = new Cafetaria("Cafe 1", menuList);


        //mulai (start)
        boolean isloged = true;
        while (isloged) {
            System.out.println("                                                                                    ^    ^");
            System.out.println("                         -- WELCOME TO THE --                                      / \\--/ |");
            System.out.println("¸.·´¯`·.¸.·´¯`·.¸.·´¯`·.¸.·´¯`·.¸.·´¯`·.¸.·´¯`·.¸.·´¯`·.¸.·´¯`·.¸.·´¯`·.¸.·´¯`·.¸  (  o  o)");
            System.out.println("_.-.              _   |/-._   ..          ,..  ,.        _____ ___  __. .   .       \\  ^ /\n" +
                    ".|'     ,\\  /     /\\.  ||  '|  ||        /''   ,/. |\\  |   |   |    |    |\\. |      /'-\"-|\n" +
                    " '\"..   /\\\\//\\   // \\  ||_,,'--||--'    .'     / \\ |'\\ |   |   |___ |,__ | \\   |  _/ < ; (;\n" +
                    "    |  //+\\/ \\\\ //---\\ ||-(.   ||       |.    |,,.||  \\|   |   |    |    |  \\|   / ,_ |_|_\\\n" +
                    "`,_,' -/     '|//    `\\|'  '\\. `'-==     ''-.'|   \\|   \\   |   |,__ |,__ |   \\   ( _,,)\\,,),)");
            System.out.println("¸.·´¯`·.¸.·´¯`·.¸.·´¯`·.¸.·´¯`·.¸.·´¯`·.¸.·´¯`·.¸.·´¯`·.¸.·´¯`·.¸.·´¯`·..·´¯`·.¸ \\ '.___,");
            System.out.println("1. Register                                                                        '-----'");
            System.out.println("2. Login");
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

                    case 0:
                        System.out.println("Goodbye!");
                        isloged = false;
                        break;
                    default:
                        System.out.println("Wrong input!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input with number!");
                sc.next(); // tanpa perintah ini maka infinite loop
            }

        }
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

            if (!n.isEmpty() || !n2.isEmpty() || !n3.isEmpty()) {
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
                                    System.out.println("Input with number!");
                                    sc.next();
                                }
                            }

                        } else {
                            System.out.println("- Wrong input! choose between 1 or 2. -");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Input with number!");
                        sc.next();
                    }
                }

                if (n4Filled == false) {
                    System.out.println(" _._     _,-'\"\"`-._\n" +
                            "(,-.`._,'(       |\\`-/|\n" +
                            "    `-.-' \\ )-`( , o o)\n" +
                            "          `-    \\`_`\"'-");

                    if (n4 == 1) {
                        User newUser = new User(n, n3, n5, 0, Roles.SMP.getRoleName());
                        user.add(newUser);
                        System.out.println("=== Created new account! ===");
                        newUser.menuUtama(sc, cafe);

                    } else if (n4 == 2) {
                        User newUser = new User(n, n3, n5, 0, Roles.SMA.getRoleName());
                        user.add(newUser);
                        System.out.println("=== Created new account! ===");
                        newUser.menuUtama(sc, cafe);
                    }
                }
            }
        } else {
            System.out.println("=== match the password! ===");
        }
    }

    public static void login(Scanner sc, ArrayList<User> user, Cafetaria cafe) {
        sc.nextLine();
        System.out.print("\n - Enter username: ");
        String n = sc.nextLine();
        System.out.print(" - Enter password: ");
        String n2 = sc.nextLine();

        if (!n.isEmpty() || !n2.isEmpty()) {
            for (int i = 0; i < user.size(); i++) {
                if (user.get(i).getNama().equals(n) && user.get(i).getPassword().equals(n2)) {
                    System.out.println(" _._     _,-'\"\"`-._\n" +
                            "(,-.`._,'(       |\\`-/|\n" +
                            "    `-.-' \\ )-`( , o o)\n" +
                            "          `-    \\`_`\"'-");
                    System.out.println("=== Logged in successfully! ===");
                    user.get(i).menuUtama(sc, cafe);

                } else {
                    System.out.println("=== Invalid Username or password! ===");
                }
            }
        }
    }

}
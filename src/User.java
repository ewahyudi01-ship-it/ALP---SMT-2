import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

public class User {

    protected String username;
    protected String password;
    protected int beratBadan;
    protected double saldo;
    protected ArrayList<Purchase> historiPembelian;
    protected Stack<Purchase> recentPurchases;
    private HealthReport healthReport;
    private MemberCard memberCard;
    private String roles;

    public User(String username, String password, int beratBadan, double saldo, String roles) {
        this.username = username;
        this.password = password;
        this.beratBadan = beratBadan;
        this.saldo = saldo;
        this.roles = roles;
        recentPurchases = new Stack<>();
        historiPembelian = new ArrayList<>();

    }

    //method2 void dll
    public void menuUtama(Scanner sc, Cafetaria cafe) {

        while (true) {
            System.out.println("\n" +
                    "---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---\n" +
                    " o | o   o | o   o | o   o | o   o | o   o | o   o | o   o | o   o | o \n" +
                    "---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---");
            System.out.println("———————————————————————————————————————————————————————————————————————");
            System.out.println("               Welcome to the Smart Canteen, " + username + "!  :)       ");
            System.out.println("———————————————————————————————————————————————————————————————————————");
            System.out.println("---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---\n" +
                    " o | o   o | o   o | o   o | o   o | o   o | o   o | o   o | o   o | o\n" +
                    "---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---\n");

            System.out.println(" - Profile - ");
            System.out.println("Name: " + username);
            System.out.println("Berat badan: " + beratBadan);
            System.out.println("role: " + roles);
            System.out.println("------------------");
            System.out.println("1. buy food");
            System.out.println("2. purchase history");
            System.out.println("3. health report");
            System.out.println("4. Member card");
            System.out.println("5. Create new menu");
            System.out.println("6. Restock Ingredient");
            System.out.println("7. List of Orders");
            System.out.println("8. Cancel Order");
            System.out.println("0. log out");
            System.out.print("Choice: ");

            try {
                int n = sc.nextInt();
                switch (n) {
                    case 1:
                        buyFood(sc, cafe);
                        break;

                    case 2:

                        break;

                    case 3:

                        break;

                    case 4:
                        break;

                    case 5:
                        if (roles == "Owner") {

                        }
                        break;

                    case 6:
                        break;

                    case 0:
                        return;

                    default:
                        System.out.println(" - Wrong input! - ");
                }
            } catch (InputMismatchException e) {
                System.out.println(" - Input with number! - ");
                sc.next(); // tanpa perintah ini maka infinite loop
            }
        }
    }

    public void buyFood(Scanner sc, Cafetaria cafe) {
        boolean isOn = true;

        while (isOn) {
            System.out.println("\n=== Choose Menu! ===");
            cafe.showAllMenu();
            System.out.print("-- Choose menu: ");

            try {
                int n = sc.nextInt(); //choose menu

                if (n <= cafe.getMenuSize() && n > 0) {
                    while (isOn) {
                        System.out.println("\n=== Choose Products! ===");
                        for (int i = 0; i < cafe.getMenu(n-1).getFoodItem().size(); i++) {
                            System.out.println(i+1+". "+cafe.getMenu(n - 1).getFoodItem().get(i).getFoodName() + " | Harga: " + cafe.getMenu(n-1).getFoodItem().get(i).getHarga());
                        }
                        System.out.print("-- Choose: ");

                        try {

                            int n2 = sc.nextInt(); //choose products

                            if (n2 <= cafe.getMenu(n - 1).getFoodItem().size() && n > 0) {
                                System.out.print("-- Quantity: ");

                                int n3 = 0;
                                try {

                                    n3 = sc.nextInt(); //quantity products

                                    Purchase purchase = new Purchase(this, cafe.getMenu(n - 1).getFoodItem().get(n2 - 1), n3);
                                    purchase.calculateTotal();
                                    purchase.printReceipt();
                                    isOn = false;

                                } catch (InputMismatchException e) {
                                    System.out.println(" - Input with number! - ");
                                }
                            }
                        } catch (InputMismatchException e) {
                            System.out.println(" - Please enter a number - ");
                            sc.next();
                        }
                    }
                }

            } catch (InputMismatchException e) {
                System.out.println(" - Please enter a number - ");
                sc.next();
            }
        }
    }

    public void lihatMenu(Cafetaria cafe) {
        cafe.showAllMenu();
    }

    public void melihatHistori() {

    }

    public void melihatLaporanKesehatan() {

    }

    //setter & getter
    public String getNama() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}


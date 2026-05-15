import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

public class User {

    protected String username;
    protected String password;
    protected double saldo;
    protected ArrayList<Purchase> historiPembelian;
    protected Stack<Purchase> recentPurchases;
    private HealthReport healthReport;
    private MemberCard memberCard;

    public User(String username, String password, double saldo) {
        this.username = username;
        this.password = password;
        this.saldo = saldo;
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
            System.out.println("—————————————————————————————————————————————————————————————————————————");
            System.out.println("               Welcome to the Smart Canteen  " + username + "!  :)       ");
            System.out.println("—————————————————————————————————————————————————————————————————————————");
            System.out.println("---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---\n" +
                    " o | o   o | o   o | o   o | o   o | o   o | o   o | o   o | o   o | o\n" +
                    "---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---\n");

            System.out.println("1. buy food");
            System.out.println("2. purchase history");
            System.out.println("3. health report");
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
                int n = sc.nextInt();
                if (n < cafe.getMenu(0).getFoodItem().size() && n > 0) { // <-- getMenu(int n) nantinya dibuat pakai input user.
                    System.out.print("-- Quantity: ");
                    int n2 = sc.nextInt();

                    Purchase purchase = new Purchase(this, cafe.getMenu(0).getFoodItem().get(n - 1), n2);
                    purchase.calculateTotal();
                    purchase.printReceipt();
                    isOn = false;
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


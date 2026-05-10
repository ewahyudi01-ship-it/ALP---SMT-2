import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

public abstract class User {

    protected String username;
    protected String password;
    protected double saldo;
    protected int points;
    protected String preferensiMakanan;
    protected ArrayList<Purchase> historiPembelian;
    protected Stack<Purchase> recentPurchases;
    private HealthReport healthReport;

    public User(String username, String password, double saldo, int points) {
        this.username = username;
        this.password = password;
        this.saldo = saldo;
        this.points = points;
        this.preferensiMakanan = "";
        recentPurchases = new Stack<>();
        historiPembelian = new ArrayList<>();
    }
    //method2 void dll
    public void menuUtama(Scanner sc, Cafetaria cafe, ArrayList<FoodItem> foodList) {
        System.out.println("\n=== Welcome to the Smart Canteen  "+username+"! :0 ===");
        System.out.println("1. buy food");
        System.out.println("2. history");
        System.out.println("3. receives rewards");
        System.out.println("4. health report");
        System.out.println("5. reward points");
        System.out.println("0. log out");
        System.out.print("Choice: ");

        try {
            int n = sc.nextInt();
            switch (n) {
                case 1:
                    buyFood(sc, cafe, foodList);
                    break;

                case 2:

                    break;

                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Wrong input!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Input with number!");
            sc.next(); // tanpa perintah ini maka infinite loop
        }
    }

    public void buyFood(Scanner sc, Cafetaria cafe, ArrayList<FoodItem> foodList) {
        boolean isOn = true;

        while (isOn) {
            lihatMenu(cafe);
            System.out.print("Choose:");
            try {
                int n = sc.nextInt();
                if (n < foodList.size() && n > 0) {
                    System.out.println("Quantity: ");
                    int n2 = sc.nextInt();

                    Purchase purchase = new Purchase(this, foodList.get(n - 1), n2);
                    purchase.calculateTotal();
                    purchase.printReceipt();
                    isOn = false;
                }

            } catch (InputMismatchException e) {
                System.out.println("Please enter a number");
                sc.next();
            }
        }
    }
    public void lihatMenu(Cafetaria cafe) {
        cafe.showMenu();
    }

    public void melihatHistori(){

    }

    public void menerimaReward(){

    }

    public void melihatLaporanKesehatan(){

    }
    public abstract double getDiscount();

    //setter & getter
    public String getNama(){
        return  username;
    }

    public String getPassword(){
        return password;
    }

}

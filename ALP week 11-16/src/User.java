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

    public void buyFood(Scanner sc, Cafetaria cafe, ArrayList<FoodItem> foodList) {
        boolean isOn = true;
        boolean isOrdered = false;

        while (isOn) {
            System.out.println("\n=== Buy Food ===");
            System.out.println("1. buy");
            System.out.println("2. check");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            try {
                int n = sc.nextInt();
                switch (n) {
                    case 1:
                        lihatMenu(cafe);
                        System.out.println("Choose:");
                        try {
                            int n2 = sc.nextInt();
                            if (n2 < foodList.size() && n2 > 0) {
                                System.out.println("Quantity: ");
                                int n3 = sc.nextInt();


                                Purchase purchase = new Purchase(this, foodList.get(n2 - 1), n3);
                                isOrdered = true;
                            }

                        } catch (InputMismatchException e) {
                            System.out.println("Please enter a number");
                            sc.next();
                        }

                        break;

                    case 2:

                        break;

                    case 0:
                        if (!isOrdered) {
                            isOn = false;
                        }
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

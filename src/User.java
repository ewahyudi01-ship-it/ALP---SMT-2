import java.util.*;

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

        // 1. MEMBUAT TIMER BACKGROUND
        Timer timerOtomatis = new Timer(true); // 'true' berarti berjalan sebagai daemon thread (background)

        // Jadwalkan tugas untuk mengecek pesanan setiap 1000 milidetik (1 detik)
        timerOtomatis.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Cek apakah ada pesanan di stack
                if (!recentPurchases.empty()) {
                    Purchase current = recentPurchases.peek();
                    long waktuSekarang = System.currentTimeMillis() / 1000;
                    long sisaWaktu = current.totalWaktu - (waktuSekarang - current.waktuPesan);

                    // Jika waktu sudah habis (0 atau minus)
                    if (sisaWaktu <= 0) {
                        recentPurchases.pop(); // Hapus otomatis dari stack
                        cafe.removeOrder();
                            System.out.print("\n[NOTIFICATION] Order of " + current.getFoodItem().getFoodName() + " is finished! \nChoice: ");

                    }
                }
            }
        }, 1000, 1000);

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
            System.out.println("saldo: " + saldo);
            System.out.println("------------------");
            if (!recentPurchases.empty()) {
                Purchase current = recentPurchases.peek();
                long waktuSekarang = System.currentTimeMillis() / 1000;
                long sisaWaktu = current.totalWaktu - (waktuSekarang - current.waktuPesan);

                // Hitung mundur dinamis saat menu dicetak
                long tampilkanDetik = sisaWaktu > 0 ? sisaWaktu : 0;
                System.out.println("Current order: " + current + " | Wait atleast around: " + tampilkanDetik + " sec");
            } else {
                System.out.println("Current order: Tidak ada pesanan aktif");
            }
            System.out.println("------------------");
            if (!cafe.getOrders().isEmpty() && roles.equals("Owner")) {
                System.out.println("------- order -------");
                cafe.showOrders();
                System.out.println("------------------");
            }
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
                        for (int i = 0; i < cafe.getMenu(n - 1).getFoodItem().size(); i++) {
                            System.out.println(i + 1 + ". " + cafe.getMenu(n - 1).getFoodItem().get(i).getFoodName() + " | Harga: " + cafe.getMenu(n - 1).getFoodItem().get(i).getHarga());
                        }
                        System.out.print("-- Choose: ");

                        try {

                            int n2 = sc.nextInt(); //choose products

                            if (!recentPurchases.empty() && cafe.getMenu(n - 1).getFoodItem().get(n2 - 1) instanceof FoodMasak) {
                                System.out.println("Tidak bisa order food masak lagi! tunggu sampai selesai pesanan sebelumnya!");
                                return;
                            }
                            if (n2 <= cafe.getMenu(n - 1).getFoodItem().size() && n > 0) {
                                System.out.println("Produk dipilih: " + cafe.getMenu(n - 1).getFoodItem().get(n2 - 1).displayInfo());
                                System.out.print("-- Quantity: ");

                                int n3 = 0;
                                try {
                                    n3 = sc.nextInt(); //quantity products

                                    if (n3 > 0 && n3 <= cafe.getMenu(n - 1).getFoodItem().get(n2 - 1).getStock()) { //input is in stock or 0

                                        if (Purchase.calculateCheck(this, cafe.getMenu(n - 1).getFoodItem().get(n2 - 1), n3) <= saldo) {

                                            //melakukan transaksi
                                            Purchase purchase = new Purchase(this, cafe.getMenu(n - 1).getFoodItem().get(n2 - 1), n3);

                                            int timeProduct = 0;
                                            if (cafe.getMenu(n - 1).getFoodItem().get(n2 - 1) instanceof FoodMasak) { // masukin timer roduct
                                                timeProduct = ((FoodMasak)cafe.getMenu(n - 1).getFoodItem().get(n2 - 1)).getWaktuBuat();
                                                purchase.addWaktu(timeProduct);
                                            }
                                            purchase.calculateTotal();

                                            cafe.addOrder((purchase));
                                            purchase.printReceipt();
                                            isOn = false;

                                            if (cafe.getMenu(n - 1).getFoodItem().get(n2 - 1) instanceof FoodJadi) {
                                                cafe.getMenu(n - 1).getFoodItem().get(n2 - 1).reduceStock(n3);

                                            } else if (cafe.getMenu(n - 1).getFoodItem().get(n2 - 1) instanceof FoodMasak) {
                                                ((FoodMasak) cafe.getMenu(n - 1).getFoodItem().get(n2 - 1)).reduceStockBahanBaku(n3);
                                                recentPurchases.push(purchase); // masuk ke stack (ONLY food masak)

                                            }
                                        } else {
                                            System.out.println(" - invalid input!, please input quantity between amount of items product! - ");
                                        }
                                    } else {
                                        System.out.println(" - Invalid input! - ");
                                    }
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


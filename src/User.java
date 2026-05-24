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

    // method2 void dll
    public void menuUtama(ArrayList<User>user,Scanner sc, Cafetaria cafe) {

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
                        System.out.println();
                        System.out.print("\n[NOTIFICATION] Order of " + current.getFoodItem().getFoodName() + " is finished! \nChoice: ");

                    }
                }
            }
        }, 1000, 1000);

        boolean isTrue = true;
        while (isTrue) {
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

            if (roles.equals("Owner")) {
                if (reminderBahanBaku() == 1) {
                    System.out.println("\n------ REMINDER ------");
                    System.out.println("WARNING! your raw material stock for products is empty, please refill!");
                        showAllIngredientToRefill();


                    System.out.println("Type '6' to restock it now! ");
                    System.out.println("----------------------");

                } else if (reminderBahanBaku() == 2) {
                    System.out.println("\n------ REMINDER ------");
                    System.out.println("WARNING! your raw material stock for products is almost empty, please refill!");
                        showAllIngredientToRefill();

                    System.out.println("Type '6' to restock it now! ");
                    System.out.println("----------------------");
                }
            }

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

            System.out.println("----------------------");

            System.out.println("1. buy food");
            System.out.println("2. purchase history");
            System.out.println("3. health report");
            System.out.println("4. Member card");
            System.out.println("5. Create new menu");
            System.out.println("6. Restock");
            System.out.println("7. List of Orders");
            System.out.println("8. Cancel Order");
            System.out.println("0. log out");
            System.out.print("Choice: ");

            try {
                int n = sc.nextInt();
                switch (n) {
                    case 1:
                        buyFood(user,sc, cafe);
                        break;

                    case 2:
                        purchaseHistory(sc);
                        break;

                    case 3:

                        break;

                    case 4:
                        boolean isOn = true;
                        while (isOn){
                           if (memberCard != null){ //jika ada member card
                               System.out.println("\n_\\|/__\\|/__\\|/__\\|/_   MEMBER CARD   _\\|/__\\|/__\\|/__\\|/_");
                               System.out.println("Info Stats:");
                               System.out.println("ID       : " + memberCard.getIdCard());
                               System.out.println("Rank     : " + memberCard.getRankSubscription());
                               System.out.println("Expire in: " + memberCard.getMemberCard());
                               System.out.println("----------------------------------------------------------");
                               System.out.println("1. Upgrade Member card");
                               System.out.println("2. Extend duration member card");
                               System.out.println("0. return to main menu");
                               System.out.print("Choice: ");

                               try {
                                   int choice = sc.nextInt();
                                   switch (choice) {
                                       case 1: // upgrade to premium
                                           if (!memberCard.getRankSubscription().equals(MemberCard.Rank.PREMIUM)) {
                                               System.out.println("\nUpgrade to Premium! no more expire duration, 20% discount all the time! for Rp.850.000");
                                               System.out.println("1.pay");
                                               System.out.println("0.back");
                                               System.out.print("Choice: ");
                                               try {
                                                   choice = sc.nextInt();
                                                   switch (choice) {
                                                       case 1:
                                                           int premium = 850000;

                                                           if (saldo >= 850000) {
                                                               this.memberCard.upgradeCardPremium();
                                                               saldo -= premium;
                                                               System.out.println("Purchase member card upgrade to premium succesful!");
                                                               break;
                                                           } else {
                                                               System.out.println("Not enough balance!");
                                                           }
                                                       case 0:
                                                           break;
                                                   }

                                               } catch (InputMismatchException e) {
                                                   System.out.println(" - Input with number! - ");
                                                   sc.next();
                                               }
                                           } else {
                                               System.out.println("You already have Premium member card subscription!");
                                           }
                                           break;

                                       case 2: //extend time duration
                                            if (memberCard.getRankSubscription().equals(MemberCard.Rank.REGULAR)) {
                                                System.out.println("¸,ø¤º°`°º¤ø¤º°`°º¤ø,¸");
                                                System.out.println("1. Extend to 3 days! for Rp.5000");
                                                System.out.println("2. Extend to 1 weeks! for Rp.10650");
                                                System.out.println("3. Extend to 1 month (30 day)! for Rp.34.500");
                                                System.out.println("0. Back");
                                            } else {
                                                System.out.println("You already have Premium member card subscription!");
                                            }
                                            break;

                                       case 0:
                                           isOn = false;

                                   }

                               } catch (InputMismatchException e) {
                                   System.out.println(" - Input with number! - ");
                                   sc.next();
                               }

                           } else if (memberCard == null) { //jika tidak ada member card
                               System.out.println("\n_\\|/__\\|/__\\|/__\\|/_ BUY MEMBER CARD _\\|/__\\|/__\\|/__\\|/_");
                               System.out.println("Buy your first Member Card, cost for: Rp.14.000, last for 3 days");
                               System.out.println("1. Buy new Member card");
                               System.out.println("0. return to main menu");
                               System.out.print("Choice: ");

                               try {
                                   int choice = sc.nextInt();
                                   switch (choice) {
                                       case 1:
                                           int regular = 14000;

                                           saldo -= regular;
                                           memberCard = new MemberCard("ID#" + getNama() + "0"+roles, MemberCard.Rank.REGULAR, 3);
                                           break;

                                           case 0: // kembali
                                               isOn = false;
                                   }
                               } catch (InputMismatchException e) {
                                   System.out.println(" - Input with number! - ");
                                   sc.next();
                               }
                           }
                       }

                        break;

                    case 5:
                        if (roles == "Owner") {

                        }
                        break;

                    case 6:
                        isOn = true;
                        while (isOn) {
                            if (roles.equals("Owner")) {
                                System.out.println("\n¸,ø¤°`°¤ø,¸¸,ø¤°`°¤ø,¸   REFILL STOCK INGREDIENT   ¸,ø¤°`°¤ø,¸¸,ø¤°`°¤ø,¸");
                                    showAllIngredientToRefill(); // food masak
                                System.out.println("\n¸,ø¤°`°¤ø,¸¸,ø¤°`°¤ø,¸ REFILL STOCK READY PRODUCTS ¸,ø¤°`°¤ø,¸¸,ø¤°`°¤ø,¸");
                                    showAllFoodJadiStockToRefill(cafe); // food jadi
                                System.out.println("\n=========================================================================");
                                System.out.println("1. Ingredient");
                                System.out.println("2. Products");
                                System.out.println("0. back");
                                System.out.print("Choose: ");

                                try {
                                    n = sc.nextInt();  // choose section ingreedient
                                    if(n == 1){
                                        System.out.print("\nChoose Ingredient: ");
                                        try {
                                            int choice = sc.nextInt();  // choose ingredient

                                            if (choice > 0 && choice <= showAllIngredientToRefill()) {
                                                System.out.print("Refill ammount: ");

                                                try { // ammount
                                                    int n2 = sc.nextInt();

                                                    if (n2 > 0) { // jika ingredient tidak dikasih 0
                                                        int number = 1;

                                                        for (int i = 0; i < Main.bahanBakuList.size(); i++) {
                                                            if (Main.bahanBakuList.get(i).getStockBaku() < 3) {

                                                                if (number == choice) {
                                                                    Main.bahanBakuList.get(i).addIngredient(n2);
                                                                    System.out.println("Successfully restock " + Main.bahanBakuList.get(i).getNamaBahanBaku() + " for " + n2);
                                                                    break;
                                                                }
                                                                number++;

                                                            }
                                                        }
                                                    }

                                                } catch (InputMismatchException e) {
                                                    System.out.println(" - Input with number! - ");
                                                    sc.next();
                                                }
                                            } else {
                                                System.out.println(" - Invalid input! - ");
                                            }

                                        } catch (InputMismatchException e) {
                                            System.out.println(" - Input with number! - ");
                                            sc.next();
                                        }
                                    } else if (n == 2) {  // choose section products
                                        System.out.print("\nChoose Products: ");

                                        try {
                                            int choice = sc.nextInt();  // choose products

                                            if (choice > 0 && choice <= showAllFoodJadiStockToRefill(cafe)) {
                                                System.out.print("Refill ammount: ");

                                                try {
                                                    int n2 = sc.nextInt();  // amount to be refill the products

                                                    if (n2 > 0) {
                                                        int number = 1;
                                                        Menu mainMenu = cafe.getMainMenu();

                                                        for (int i = 0; i < mainMenu.getFoodItem().size(); i++) {

                                                            if (mainMenu.getFoodItem().get(i).getStock() < 3 && mainMenu.getFoodItem().get(i) instanceof FoodJadi) {
                                                                if (number == choice) {
                                                                    ((FoodJadi) mainMenu.getFoodItem().get(i)).addStock(n2);
                                                                    System.out.println("Successfully restock " + mainMenu.getFoodItem().get(i).getFoodName() + " for " + n2);
                                                                    break;
                                                                }
                                                                number++;
                                                            }
                                                        }
                                                    }

                                                } catch (RuntimeException e) {
                                                    System.out.println(" - Input with number! -");
                                                    sc.next();
                                                }
                                            }
                                        } catch (RuntimeException e) {
                                            System.out.println(" - Input with number! -");
                                            sc.next();
                                        }

                                    } else if (n == 0){ // choose back to main menu
                                        isOn = false;

                                    }

                                } catch (RuntimeException e) {
                                    System.out.println(" - Input with number! -");
                                    sc.next();
                                }

                            } else {
                                System.out.println("Your not the Owner!");
                                isOn = false;
                            }
                        }
                        break;

                    case 7:
                        isOn = true;
                        while (isOn) {
                        if (roles.equals("Owner")) {

                            System.out.println("\n-=ø¤º°`°º¤ø=-=- CUSTOMER ORDER LIST -=-=ø¤º°`°º¤ø=-");
                            if (cafe.getOrders().isEmpty()) {
                                System.out.println("no one order a food, for now.");
                            } else {
                                cafe.showOrders();
                            }

                            System.out.println("ø¤°`°¤ø,¸¸,ø¤°`°¤ø,¸¸,ø¤°`°¤ø,¸¸,ø¤°`°¤ø,¸¸,ø¤°`°¤ø");

                                System.out.println("1.Remove current order");
                                System.out.println("0.back");
                                System.out.print("Choice: ");
                                try {
                                    n = sc.nextInt();
                                    switch (n) {
                                        case 1:
                                            if (!cafe.getOrders().isEmpty()) {   // menghapus order list dari owner, serta stack dari sisi pelanggan yang di target
                                                Purchase pesananYangDihapus = cafe.removeOrder(); // return nya objek "purchase" sebelum order menghapus datanya dengan poll()
                                                User targetUser = pesananYangDihapus.getUser();
                                                    targetUser.recentPurchases.remove(pesananYangDihapus); // menghapus stack user yang ditargetkan
                                            }
                                            break;

                                        case 0:
                                            isOn = false;
                                            break;
                                    }

                                } catch (InputMismatchException e) {
                                    System.out.println(" - Please enter a number - ");
                                    sc.next();
                                }

                            } else {
                                System.out.println("Your not the Owner!");
                                isOn = false;
                            }
                        }
                        break;

                    case 8:
                        if (!recentPurchases.empty()) {
                            recentPurchases.pop();
                            System.out.println("Your order has been cancelled!");
                        } else {
                            System.out.println("There is no ongoing cooking for current order!");
                        }
                        break;

                    case 0:
                        isTrue = false;
                        break;

                    default:
                        System.out.println(" - Invalid input! - ");

                }
            } catch (InputMismatchException e) {
                System.out.println(" - Input with number! - ");
                sc.next(); // tanpa perintah ini maka infinite loop
            }

        }
    }


    public void buyFood(ArrayList<User>user ,Scanner sc , Cafetaria cafe) {
        boolean isOn = true;

        while (isOn) {
            System.out.println("\n=== Choose Menu! ===");
            cafe.showAllMenu();
            System.out.print("-- Choose menu: ");

            try {

                int n = sc.nextInt(); // choose menu

                if (n <= cafe.getMenuSize() && n > 0) {
                    while (isOn) {
                        System.out.println("\n=== Choose Products! ===");
                        for (int i = 0; i < cafe.getMenu(n - 1).getFoodItem().size(); i++) {
                            System.out.println(i + 1 + ". " + cafe.getMenu(n - 1).getFoodItem().get(i).getFoodName() + " | Harga: " + cafe.getMenu(n - 1).getFoodItem().get(i).getHarga());
                        }
                        System.out.print("-- Choose: ");
                        try {
                            int n2 = sc.nextInt(); // choose products

                            if (!recentPurchases.empty() && cafe.getMenu(n - 1).getFoodItem().get(n2 - 1) instanceof FoodMasak) {
                                System.out.println("Tidak bisa order food masak lagi! tunggu sampai selesai pesanan sebelumnya!");
                                return;
                            }

                            if (n2 <= cafe.getMenu(n - 1).getFoodItem().size() && n2 > 0) {
                                System.out.println("Produk dipilih: " + cafe.getMenu(n - 1).getFoodItem().get(n2 - 1).displayInfo());
                                System.out.print("-- Quantity: ");

                                int n3 = 0;
                                try {
                                    n3 = sc.nextInt(); // quantity products

                                    if (n3 > 0 && n3 <= cafe.getMenu(n - 1).getFoodItem().get(n2 - 1).getStock()) { // input is 0 or too much than available stock
                                        if (cafe.getMenu(n - 1).getFoodItem().get(n2 - 1).getHarga() * n3 <= this.saldo) { //cek saldo user

                                            // melakukan transaksi
                                            Purchase purchase = new Purchase(this, cafe.getMenu(n - 1).getFoodItem().get(n2 - 1), n3);
                                            this.saldo -= purchase.getCalculateTotal();

                                            int timeProduct = 0;
                                            if (cafe.getMenu(n - 1).getFoodItem().get(n2 - 1) instanceof FoodMasak) { // masukin timer product
                                                timeProduct = ((FoodMasak) cafe.getMenu(n - 1).getFoodItem().get(n2 - 1)).getWaktuBuat();
                                                purchase.addWaktu(timeProduct);
                                            }

                                            historiPembelian.add(purchase); // arraylist
                                            purchase.printReceipt(user);
                                            sc.nextLine();
                                            isOn = false;

                                            if (cafe.getMenu(n - 1).getFoodItem().get(n2 - 1) instanceof FoodJadi) {
                                                cafe.getMenu(n - 1).getFoodItem().get(n2 - 1).reduceStock(n3);

                                            } else if (cafe.getMenu(n - 1).getFoodItem().get(n2 - 1) instanceof FoodMasak) {
                                                cafe.addOrder((purchase)); // orderlist Queue
                                                ((FoodMasak) cafe.getMenu(n - 1).getFoodItem().get(n2 - 1)).reduceStockBahanBaku(n3);
                                                recentPurchases.push(purchase); // masuk ke stack (ONLY food masak)
                                            }
                                        } else {
                                            System.out.println(" - Not enough ammount of balance :( - ");
                                            return;
                                        }
                                    } else {
                                        System.out.println(" - invalid input!, please input quantity between amount of items product! - ");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println(" - Input with number! - ");
                                    sc.next();
                                }
                            } else {
                                System.out.println(" - invalid input! - ");
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

    private void purchaseHistory(Scanner sc) {
        System.out.println("\n¸,ø¤º°`°º¤ø¤º°`°º¤ø,¸, Purchase History ,¸,ø¤º°`°º¤ø¤º°`°º¤ø,¸");
        boolean isOn = true;
        while (isOn){
            if (!historiPembelian.isEmpty()) {
                for (int i = 0; i < historiPembelian.size(); i++) {
                    System.out.println(i+1+". "+historiPembelian.get(i).getFoodItem().getFoodName() + " | Quantity: " + historiPembelian.get(i).getQuantity() +
                            " | total: " + historiPembelian.get(i).getCalculateTotal());
                }
            } else {
                System.out.println("There's no transaction. yet!");
            }
            System.out.println("0.back");
            System.out.print("Choice: ");

            try {
                int n  = sc.nextInt();

                switch (n) {
                    case 0:
                        isOn = false;
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println(" - Input with number! - ");
                sc.next();
            }
        }
    }

    public char reminderBahanBaku() {
        for (BahanBaku stok : Main.bahanBakuList) {
            if (stok.getStockBaku() <= 0) {
                return 1; //almost empty
            } else if (stok.getStockBaku() < 3 && stok.getStockBaku() > 0) {
                return 2; // empty
            }
        }
        return 0;
    }

    private int showAllIngredientToRefill() {
        int jumlahItemYangKurang = 0;
        int j = 0;

        for (BahanBaku stok : Main.bahanBakuList) {
            if (stok.getStockBaku() < 3) {
                j++;
                System.out.println(j + ". " + stok.getNamaBahanBaku() + " | stock: " + stok.getStockBaku());
                jumlahItemYangKurang++;
            }
        }
        return jumlahItemYangKurang;
    }

    private int showAllFoodJadiStockToRefill(Cafetaria cafe) {
        int jumlahItemYangKurang = 0;
        int j = 0;

        for (FoodItem stok : cafe.getMainMenu().getFoodItem()) {
            if(stok instanceof FoodJadi) {
                if (stok.getStock() < 3) {
                    j++;
                    System.out.println(j + ". " + stok.getFoodName() + " | stock: " + stok.getStock());
                    jumlahItemYangKurang++;
                }
            }
        }
        return jumlahItemYangKurang;
    }

    public void melihatLaporanKesehatan() {

    }

    // setter & getter

    public void tambahSaldo(double n) {
        this.saldo += n;
    }

    public String getNama() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public String getRoles() {
        return roles;
    }

}

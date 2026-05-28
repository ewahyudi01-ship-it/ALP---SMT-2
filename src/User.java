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
        healthReport = new HealthReport(this);
    }

    // method2 void dll
    public void menuUtama(ArrayList<User> user, Scanner sc, Cafetaria cafe) {

        Timer timerOtomatis = new Timer(true);
        timerOtomatis.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                Purchase current = cafe.getCurrentOrder();

                if (current != null) {
                    long waktuSekarang = System.currentTimeMillis() / 1000;

                    if (current.waktuPesan == 0) {
                        current.waktuPesan = waktuSekarang;
                    }

                    long sisaWaktu = current.totalWaktu - (waktuSekarang - current.waktuPesan);

                    if (sisaWaktu <= 0) {
                        Purchase selesai = cafe.removeOrder();
                        selesai.getUser().recentPurchases.remove(selesai);
                        System.out.println(
                                "\n[NOTIFICATION] Order " + selesai.getFoodItem().getFoodName() + " is finished!"
                        );
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
                Purchase current = cafe.getCurrentOrder();

                if (current != null && current.getUser() == this) {
                    long now = System.currentTimeMillis() / 1000;
                    long sisa = current.totalWaktu - (now - current.waktuPesan);


                    System.out.println("Current order: " + current.getFoodItem().getFoodName() + " x" + current.getQuantity() + " | Wait for: " + current.getTotalWaktu() + " sec");
                }
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
                        buyFood(user, sc, cafe);
                        break;

                    case 2:
                        purchaseHistory(sc);
                        break;

                    case 3:
                        healthReport.displayReport();
                        break;

                    case 4:
                        memberCardUser(sc, user);
                        break;

                    case 5:
                        createNewMenu(sc, cafe);
                        break;

                    case 6:
                        restock(sc, cafe);
                        break;

                    case 7:
                        listOfOrders(sc,  cafe);
                        break;

                    case 8:
                        cancelOrder();
                        break;

                    case 0:
                        timerOtomatis.cancel();
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

    private void cancelOrder() {
        if (!recentPurchases.empty()) {
            recentPurchases.pop();
            System.out.println("Your order has been cancelled!");
        } else {
            System.out.println("There is no ongoing cooking for current order!");
        }
    }

    private void listOfOrders(Scanner sc, Cafetaria cafe) {
        boolean isOn = true;
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
                    int n = sc.nextInt();
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
    }

    private void restock(Scanner sc, Cafetaria cafe) {
        boolean isOn = true;
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
                    int n = sc.nextInt();  // choose section ingreedient
                    if (n == 1) {
                        System.out.println("\n-----------------------");
                        for (int i = 0; i < Main.bahanBakuList.size(); i++) {
                            System.out.println(i + 1 + ". " + Main.bahanBakuList.get(i).getNamaBahanBaku() + " | Stock: " + Main.bahanBakuList.get(i).getStockBaku());
                        }

                        System.out.print("\nChoose Ingredient: ");
                        try {
                            int choice = sc.nextInt();  // choose ingredient

                            if (choice > 0 && choice <= Main.bahanBakuList.size()) {
                                System.out.print("Refill ammount: ");

                                try { // ammount
                                    int n2 = sc.nextInt();
                                    if (n2 > 0) { // jika ingredient tidak dikasih 0

                                        Main.bahanBakuList.get(choice - 1).addIngredient(n2);
                                        System.out.println("Successfully restock " + Main.bahanBakuList.get(choice - 1).getNamaBahanBaku() + " for " + n2);
                                        break;
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
                        try {
                            System.out.println("\n-----------------------");
                            Menu mainMenu = cafe.getMainMenu();
                            int jumlahProdukJadi = 0;

                            for (int i = 0; i < mainMenu.getFoodItem().size(); i++) {
                                if (mainMenu.getFoodItem().get(i) instanceof FoodJadi) {
                                    System.out.println(i + 1 + ". " + mainMenu.getFoodItem().get(i).getFoodName() + " | Stock: " + mainMenu.getFoodItem().get(i).getStock());
                                    jumlahProdukJadi++;
                                }
                            }
                            System.out.print("\nChoose Products: ");
                            int choice = sc.nextInt();  // choose products

                            if (choice > 0 && choice <= jumlahProdukJadi) {
                                System.out.print("Refill ammount: ");

                                try {
                                    int n2 = sc.nextInt();  // amount to be refill the products

                                    if (n2 > 0) {
                                        ((FoodJadi) mainMenu.getFoodItem().get(choice - 1)).addStock(n2);
                                        System.out.println("Successfully restock " + mainMenu.getFoodItem().get(choice - 1).getFoodName() + " for " + n2);
                                        break;

                                    }

                                } catch (RuntimeException e) {
                                    System.out.println(" - Input with number! -");
                                    sc.next();
                                }
                            } else {
                                System.out.println(" - Invalid input! - ");
                            }
                        } catch (RuntimeException e) {
                            System.out.println(" - Input with number! -");
                            sc.next();
                        }

                    } else if (n == 0) { // choose back to main menu
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
    }


    private void createNewMenu(Scanner sc, Cafetaria cafe) {
        if (roles == "Owner") {
            System.out.println("\n _  _  _  _  _  _  _  _  _  _  _  _  _  _  _  _  _  _  _  _ \n" +
                    "( )( )( )( )( )( )( )( )( )( )( )( )( )( )( )( )( )( )( )( )\n" +
                    "------------- C R E A T E --- N E W --- M E N U ------------\n" +
                    "(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)\n");

            ArrayList<FoodItem> foodItems = new ArrayList<>();
            boolean isOn = true;
            while (isOn) {
                for (int i = 0; i < cafe.getMainMenu().getFoodItem().size(); i++) {
                    System.out.println(i + 1 + ". " + cafe.getMainMenu().getFoodItem().get(i).getFoodName());
                }
                System.out.println("-<>--<>--<>--<>--<>--<>--<>--<>--<>--<>--<>--<>--<>--<>--<>-");
                System.out.println("1. Add products");
                System.out.println("2. Finish");
                System.out.println("0. Back");
                System.out.print("Choice: ");
                try {
                    int n = sc.nextInt();
                    switch (n) {
                        case 1: // add product for new mwnu
                            System.out.print("\n - Choose products to add: ");
                            try {
                                n = sc.nextInt();
                                if (n > 0 && n < cafe.getMainMenu().getFoodItem().size()) { // cek input valid or not
                                    foodItems.add(cafe.getMainMenu().getFoodItem().get(n - 1));
                                } else {
                                    System.out.println(" - Invalid input! -");
                                }

                            } catch (InputMismatchException e) {
                                System.out.println(" - Input with number! - ");
                                sc.next();
                            }
                            break;

                        case 2: // create new menu
                            if (foodItems.size() > 1) {
                                System.out.print("Menu name: ");
                                sc.nextLine();
                                String menuName = sc.nextLine();

                                if (!menuName.trim().equalsIgnoreCase("")) {
                                    Menu menuPaket = new Menu("Packet " + menuName, foodItems);
                                    cafe.getMenuList().add(menuPaket);

                                    System.out.println("\n¸.·´¯`·.¸.·´¯`·.¸ Successfully created new packet menu! :o ¸.·´¯`·.¸.·´¯`·.¸\n");
                                    isOn = false;
                                } else {
                                    System.out.println(" - Menu name is empty! try again! - ");
                                }
                            } else {
                                System.out.println("Sorry! but menu must contain at least two products to proceed!\n");
                            }
                            break;

                        case 0:
                            isOn = false;
                    }

                } catch (InputMismatchException e) {
                    System.out.println(" - Input with number! - ");
                    sc.next();
                }

            }
        } else {
            System.out.println("Your not the Owner!");
        }
    }

    private void memberCardUser(Scanner sc, ArrayList<User> user) {
        boolean isOn = true;
        while (isOn) {
            if (memberCard != null) { //jika ada member card
                System.out.println("\n_\\|/__\\|/__\\|/__\\|/_   MEMBER CARD   _\\|/__\\|/__\\|/__\\|/_");
                System.out.println("Info Stats:");
                System.out.println("ID       : " + memberCard.getIdCard());
                System.out.println("Rank     : " + memberCard.getRankSubscription());
                System.out.println("Expire in: " + memberCard.getMemberCardExpiry());
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
                                    User owner = getOwner(user);

                                    switch (choice) {
                                        case 1:
                                            if (saldo >= MemberCard.PRICE_PREMIUM) {
                                                this.memberCard.upgradeCardPremium();
                                                saldo -= MemberCard.PRICE_PREMIUM;
                                                owner.tambahSaldo(MemberCard.PRICE_REGULAR);

                                                System.out.println("Purchase member card upgrade to premium succesful!");
                                                break;
                                            } else {
                                                System.out.println("Not enough balance! :{");
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
                                System.out.print("Choice: ");


                                try {
                                    int n = sc.nextInt();
                                    User owner = getOwner(user);
                                    switch (n) {

                                        case 1:
                                            if (saldo >= MemberCard.PRICE_3_DAYS) {
                                                saldo -= MemberCard.PRICE_3_DAYS;
                                                memberCard.setCardExpiry(MemberCard.DURATION_3_DAYS);
                                                owner.tambahSaldo(MemberCard.PRICE_3_DAYS);

                                            } else {
                                                System.out.println("Not enough balance! :{ ");
                                            }
                                            break;

                                        case 2:
                                            if (saldo >= MemberCard.PRICE_7_DAYS) {
                                                saldo -= MemberCard.PRICE_7_DAYS;
                                                memberCard.setCardExpiry(MemberCard.DURATION_7_DAYS);
                                                owner.tambahSaldo(MemberCard.PRICE_7_DAYS);

                                            } else {
                                                System.out.println("Not enough balance! :{ ");
                                            }
                                            break;

                                        case 3:
                                            if (saldo >= MemberCard.PRICE_30_DAYS) {
                                                saldo -= MemberCard.PRICE_30_DAYS;
                                                memberCard.setCardExpiry(MemberCard.DURATION_30_DAYS);
                                                owner.tambahSaldo(MemberCard.PRICE_30_DAYS);

                                            } else {
                                                System.out.println("Not enough balance! :{ ");
                                            }
                                            break;

                                        case 0:
                                            break;

                                        default:
                                            System.out.println("- Wrong input! select from 0 - 3! -");
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

                        case 0:
                            isOn = false;

                    }

                } catch (InputMismatchException e) {
                    System.out.println(" - Input with number! - ");
                    sc.next();
                }

            } else if (memberCard == null) { //jika tidak ada member card
                System.out.println("\n__\\|/__\\|/__\\|/__\\|/_ BUY MEMBER CARD _\\|/__\\|/__\\|/__\\|/__");
                System.out.println("Buy your first Member Card | Cost: Rp.14.000 | Duration: 3 days");
                System.out.println("----------------------------------------------------------");
                System.out.println("1. Buy new Member card");
                System.out.println("0. return to main menu");
                System.out.print("Choice: ");

                try {
                    int choice = sc.nextInt();
                    User owner = getOwner(user);
                    switch (choice) {
                        case 1:
                            if (saldo >= MemberCard.PRICE_REGULAR) {
                                memberCard = new MemberCard("ID#" + getNama() + "-" + roles , MemberCard.Rank.REGULAR, MemberCard.DURATION_3_DAYS);
                                saldo -= MemberCard.PRICE_REGULAR;
                                owner.tambahSaldo(MemberCard.PRICE_REGULAR);

                                System.out.println("Successfully bought your member card!");
                            } else {
                                System.out.println("Not enough balance! :{ ");
                            }
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

    }


    public void buyFood(ArrayList<User> user, Scanner sc, Cafetaria cafe) {
        boolean isOn = true;

        while (isOn) {
            System.out.println("\n=== Choose Menu! ===");
            cafe.showAllMenu();
            System.out.print("-- Choose menu: ");

            try {

                int n = sc.nextInt(); // choose menu

                if (n <= cafe.getMenuSize() && n > 0) { // cek input valid atau tidak

                    if (n == 1) { // if pilih menu utama
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

                                                // --- 3.7 Health Check: Nutrition & Safety ---
                                                FoodItem selectedFood = cafe.getMenu(n - 1).getFoodItem().get(n2 - 1);
                                                System.out.println("\n--- Nutrition Info for: " + selectedFood.getFoodName() + " ---");
                                                System.out.printf("  Calories : %.1f kcal%n", selectedFood.getCalories() * n3);
                                                System.out.printf("  Protein  : %.1f g%n", selectedFood.getProtein() * n3);
                                                System.out.printf("  Sugar    : %.1f g%n", selectedFood.getSugarLvl() * n3);
                                                System.out.println("  Healthy  : " + (selectedFood.isHealthy() ? "YES" : "NO"));
                                                System.out.println("------------------------------------------");

                                                String safetyWarning = healthReport.checkFoodSafety(selectedFood, n3);
                                                if (safetyWarning != null) {
                                                    System.out.println("\n⚠ PURCHASE CANCELLED - Health Safety Warning:");
                                                    System.out.println("  " + safetyWarning);
                                                    System.out.println("  Please check your health report (option 3) for more info.");
                                                    return;
                                                }
                                                // --- End Health Check ---

                                                // melakukan transaksi
                                                Purchase purchase = new Purchase(this, cafe.getMenu(n - 1).getFoodItem().get(n2 - 1), n3);
                                                historiPembelian.add(purchase); // arraylist
                                                purchase.printReceipt(sc);

                                                this.saldo -= purchase.getCalculateTotal(); // kurangi saldo kustomer
                                                User owner = getOwner(user); // tambahi saldo owner
                                                owner.tambahSaldo(purchase.getCalculateTotal());


                                                sc.nextLine();
                                                isOn = false;  //transaction completed

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
                    } else { // menu paket
                        double hargaTotaldariMenuPaket = 0;
                        boolean semuaStokAman = true;

                        // 1. Tampilkan item paket & validasi stok semua item terlebih dahulu
                        System.out.println("\n--- Items inside this Packet ---");
                        for (int i = 0; i < cafe.getMenu(n - 1).getFoodItem().size(); i++) {
                            FoodItem items = cafe.getMenu(n - 1).getFoodItem().get(i);

                            if (items instanceof FoodMasak) {
                                ((FoodMasak) items).getStockFoodMasak();
                                if (items.getStock() <= 0) {
                                    System.out.println(" - Sorry, the stock for the " + items.getFoodName() + " is empty :( - ");
                                    semuaStokAman = false;
                                }
                            } else if (items instanceof FoodJadi) {
                                if (items.getStock() <= 0) {
                                    System.out.println(" - Sorry, the stock for the " + items.getFoodName() + " is empty :( - ");
                                    semuaStokAman = false;
                                }
                            }
                            System.out.println((i + 1) + ". " + items.getFoodName() + " | harga: " + items.getHarga());
                            hargaTotaldariMenuPaket += items.getHarga();
                        }
                        System.out.println("-------------------------------------");

                        // Jika ada salah satu item di dalam paket yang habis maka batalkan transaksi
                        if (!semuaStokAman) {
                            System.out.println(" - Cannot purchase this packet due to empty stock item! - ");
                            return;
                        }

                        System.out.println("Succesfully purchase, in total price for this packet: " + hargaTotaldariMenuPaket);

                        // --- 3.7 Health Check for Packet Menu ---
                        System.out.println("\n--- Nutrition Summary for this Packet ---");
                        double packetCal = 0, packetProt = 0, packetSugar = 0;
                        for (int i = 0; i < cafe.getMenu(n - 1).getFoodItem().size(); i++) {
                            FoodItem items = cafe.getMenu(n - 1).getFoodItem().get(i);
                            packetCal   += items.getCalories();
                            packetProt  += items.getProtein();
                            packetSugar += items.getSugarLvl();
                        }
                        System.out.printf("  Total Calories : %.1f kcal%n", packetCal);
                        System.out.printf("  Total Protein  : %.1f g%n", packetProt);
                        System.out.printf("  Total Sugar    : %.1f g%n", packetSugar);
                        System.out.println("------------------------------------------");

                        // Check each item in the packet for health safety
                        String packetWarning = null;
                        for (int i = 0; i < cafe.getMenu(n - 1).getFoodItem().size(); i++) {
                            FoodItem items = cafe.getMenu(n - 1).getFoodItem().get(i);
                            String w = healthReport.checkFoodSafety(items, 1);
                            if (w != null) { packetWarning = w; break; }
                        }
                        if (packetWarning != null) {
                            System.out.println("\n⚠ PURCHASE CANCELLED - Health Safety Warning:");
                            System.out.println("  " + packetWarning);
                            System.out.println("  Please check your health report (option 3) for more info.");
                            return;
                        }
                        // --- End Health Check ---

                        // reduce stock
                        for (int i = 0; i < cafe.getMenu(n - 1).getFoodItem().size(); i++) {
                            FoodItem items = cafe.getMenu(n - 1).getFoodItem().get(i);
                            if (items instanceof FoodMasak) {
                                ((FoodMasak) items).reduceStockBahanBaku(1);
                            } else if (items instanceof FoodJadi) {
                                items.reduceStock(1);
                            }
                        }

                        // cek saldo kustomer & profit ke saldo owner
                        if (hargaTotaldariMenuPaket <= this.saldo) {
                            this.saldo -= hargaTotaldariMenuPaket;
                            User owner = getOwner(user);
                            owner.tambahSaldo(hargaTotaldariMenuPaket);

                            // beli dan nyimpan daftar catatan purchase
                            for (int i = 0; i < cafe.getMenu(n - 1).getFoodItem().size(); i++) {
                                FoodItem items = cafe.getMenu(n - 1).getFoodItem().get(i);
                                Purchase singlePurchase = new Purchase(this, items, 1);
                                historiPembelian.add(singlePurchase);
                            }
                            isOn = false;

                        } else {
                            System.out.println(" - Sorry, not enough balance! -");
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
        while (isOn) {
            if (!historiPembelian.isEmpty()) {
                for (int i = 0; i < historiPembelian.size(); i++) {
                    System.out.println(i + 1 + ". " + historiPembelian.get(i).getFoodItem().getFoodName() + " | Quantity: " + historiPembelian.get(i).getQuantity() +
                            " | total: " + historiPembelian.get(i).getCalculateTotal());
                }
            } else {
                System.out.println("There's no transaction. yet!");
            }
            System.out.println("0.back");
            System.out.print("Choice: ");

            try {
                int n = sc.nextInt();

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
            if (stok instanceof FoodJadi) {
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

    public User getOwner(ArrayList<User> user) {
        for (int i = 0; i < user.size(); i++) {
            if (user.get(i).getRoles().equals(Main.Roles.OWNER.getRoleName())) {
                return user.get(i);
            }
        }
        return null;
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

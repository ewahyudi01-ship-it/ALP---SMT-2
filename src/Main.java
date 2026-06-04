//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    enum Roles {
        OWNER("Owner"),
        SMA("SMA"),
        SMP("SMP");

        private String name;

        Roles(String name) {
            this.name = name;
        }
        String getRoleName() {
            return name;
        }
    }

    // bahan baku
    public static ArrayList<BahanBaku> bahanBakuList = new ArrayList<>(); // static karena dipakai 2 file dan banyak objek

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<User> user = new ArrayList<>(); // <--- user saat ini
        user.add(new User("Owner", "smart121", 65, 2000000000, "Owner"));

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
        bahanBakuList.add(new BahanBaku("white bread", 16));
        bahanBakuList.add(new BahanBaku("margarine", 9));
        bahanBakuList.add(new BahanBaku("condensed milk", 5));
        bahanBakuList.add(new BahanBaku("rice", 6));
        bahanBakuList.add(new BahanBaku("egg", 8));
        bahanBakuList.add(new BahanBaku("chicken", 15));
        bahanBakuList.add(new BahanBaku("sweet soy sauce", 9));
        bahanBakuList.add(new BahanBaku("vegetable oil", 15));
        bahanBakuList.add(new BahanBaku("spring roll wrapper", 7));
        bahanBakuList.add(new BahanBaku("bamboo shoots", 8));
        bahanBakuList.add(new BahanBaku("chicken", 11));
        bahanBakuList.add(new BahanBaku("sugar", 6));

        // food masak
        FoodItem steak = new FoodMasak("Steak", 23000, 0);
        ((FoodMasak) steak).setWaktuBuat(320);
        ((FoodMasak) steak).tambahResep("beef");
        ((FoodMasak) steak).tambahResep("potato");
        ((FoodMasak) steak).tambahResep("butter");

        FoodItem toastBread = new FoodMasak("Toast Bread", 6500, 0);
        ((FoodMasak) toastBread).setWaktuBuat(230);
        ((FoodMasak) toastBread).tambahResep("white bread");
        ((FoodMasak) toastBread).tambahResep("margarine");
        ((FoodMasak) toastBread).tambahResep("condensed milk");

        FoodItem nasiGoreng = new FoodMasak("Nasi Goreng", 17000, 0);
        ((FoodMasak) nasiGoreng).setWaktuBuat(450);
        ((FoodMasak) nasiGoreng).tambahResep("rice");
        ((FoodMasak) nasiGoreng).tambahResep("egg");
        ((FoodMasak) nasiGoreng).tambahResep("chicken");
        ((FoodMasak) nasiGoreng).tambahResep("sweet soy sauce");
        ((FoodMasak) nasiGoreng).tambahResep("vegetable oil");

        FoodItem lumpia = new FoodMasak("Lumpia", 9800, 0);
        ((FoodMasak) lumpia).setWaktuBuat(10);
        ((FoodMasak) lumpia).tambahResep("spring roll wrapper");
        ((FoodMasak) lumpia).tambahResep("bamboo shoots");
        ((FoodMasak) lumpia).tambahResep("chicken");
        ((FoodMasak) lumpia).tambahResep("sugar");
        // add FoodMasak
        foodList.add(steak);
        foodList.add(toastBread);
        foodList.add(nasiGoreng);
        foodList.add(lumpia);

        ArrayList<Menu> menuList = new ArrayList<>();
        menuList.add(new Menu("Menu utama", foodList));
        Cafetaria cafe = new Cafetaria("Cafe 1", menuList);

        // start apps
        AppSystem appSystem = new AppSystem();
        appSystem.startMainMenu(sc, user, cafe);

    }

}
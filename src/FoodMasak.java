import java.util.ArrayList;

public class FoodMasak extends FoodItem {
    private ArrayList<String> resepBahan = new ArrayList<>();
    private double caloriesTotal;
    private double proteinTotal;
    private double sugarLvlTotal;
    private int waktuBuat;

    public FoodMasak(String foodName, double harga, int stock) {
        super(foodName, harga, stock);
    }

    public void setWaktuBuat(int waktuBuat) {
        this.waktuBuat = waktuBuat;
    }

    public void tambahResep(String namaBahan) {
        this.resepBahan.add(namaBahan);
    }

    public ArrayList<String> getResepBahan() {
        return resepBahan;
    }


    public void startCooking(int quantity) {

        int totalBuat = waktuBuat * quantity;

        try {

            for(int i = totalBuat; i >= 0; i--) {

                System.out.print(
                        "\rCooking "
                                + foodName
                                + " x" + quantity
                                + " | remaining: "
                                + i + "s     "
                );

                Thread.sleep(1000);
            }

            System.out.println("\nOrder selesai!");

        }
        catch (InterruptedException e) {

            System.out.println("Timer error");
        }
    }

    public void getStockFoodMasak() {
        int maks = 999;

        for (int i = 0; i < getResepBahan().size(); i++) {
            for (BahanBaku stok : Main.bahanBakuList) {
                if (stok.getNamaBahanBaku().equals(getResepBahan().get(i))) {
                    if (maks > stok.getStockBaku()) {
                        maks = stok.getStockBaku();
                    }
                    break;
                }
            }
        }
        this.stock = maks;
    }

    public void reduceStockBahanBaku(int n3) {
        for (int i = 0; i < getResepBahan().size(); i++) {
            for (BahanBaku stok : Main.bahanBakuList) {
                if (stok.getNamaBahanBaku().equals(getResepBahan().get(i))) {
                    stok.kurangiStockBaku(n3);
                    break;
                }
            }
        }
    }

    public void getTotalCalories() {
        this.caloriesTotal=0;

        System.out.println("\n------- Kalori -------");
        for (int i = 0; i < getResepBahan().size(); i++) {
            for (BahanBaku stok : Main.bahanBakuList) {
                if (stok.getNamaBahanBaku().equals(getResepBahan().get(i))) {

                    System.out.println("Kalori "+ stok.getNamaBahanBaku() +": " + stok.getCalories());
                    this.caloriesTotal += stok.getCalories();
                    break;
                }
            }
        }

    }

    public void getTotalProtein() {
        this.proteinTotal=0;

        System.out.println("\n------- Protein -------");
        for (int i = 0; i < getResepBahan().size(); i++) {
            for (BahanBaku stok : Main.bahanBakuList) {
                if (stok.getNamaBahanBaku().equals(getResepBahan().get(i))) {

                    System.out.println("Protein "+ stok.getNamaBahanBaku() +": " + stok.getProtein());
                    proteinTotal += stok.getProtein();
                    break;
                }
            }
        }
    }

    public void getTotalSugarLvl() {
        this.sugarLvlTotal=0;

        System.out.println("\n------- Gula -------");
        for (int i = 0; i < getResepBahan().size(); i++) {
            for (BahanBaku stok : Main.bahanBakuList) {
                if (stok.getNamaBahanBaku().equals(getResepBahan().get(i))) {

                    System.out.println("Protein "+ stok.getNamaBahanBaku() +": " + stok.getSugarLvl());
                    sugarLvlTotal += stok.getSugarLvl();
                    break;
                }
            }
        }
    }
    public int getWaktuBuat() {
        return this.waktuBuat;
    }

    @Override
    public String displayInfo() {
        this.getStockFoodMasak();
        this.getTotalCalories();
        this.getTotalProtein();
        this.getTotalSugarLvl();
        return foodName + " | total calories: " + caloriesTotal + " | total sugar: " + sugarLvlTotal + " | total protein: " + proteinTotal +
                " | sisa stock: " + this.stock + " | harga: " + this.harga;
    }

    @Override
    public boolean isHealthy() {

        return false;
    }
}

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


    public boolean getStockFoodMasak() {
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
        if (maks != 0) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public void reduceStock(int n3) {
        for (int i = 0; i < getResepBahan().size(); i++) {
            for (BahanBaku stok : Main.bahanBakuList) {
                if (stok.getNamaBahanBaku().equals(getResepBahan().get(i))) {
                    stok.kurangiStockBaku(n3);
                    break;
                }
            }
        }
    }


    @Override
    public double getCalories() {
        return caloriesTotal;
    }

    @Override
    public double getProtein() {
        return proteinTotal;
    }

    @Override
    public double getSugarLvl() {
        return sugarLvlTotal;
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
        return caloriesTotal < 800 && sugarLvlTotal < 25;
    }
}
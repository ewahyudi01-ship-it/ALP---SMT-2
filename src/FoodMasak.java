import java.util.ArrayList;

public class FoodMasak extends FoodItem {
    private ArrayList<String> resepBahan = new ArrayList<>(); // arraylist
    private double caloriesTotal;
    private double proteinTotal;
    private double sugarLvlTotal;
    private int waktuBuat;

    public FoodMasak(String foodName, double harga, int stock) {
        super(foodName, harga, stock);  // super
    }

    public void setWaktuBuat(int waktuBuat) {
        this.waktuBuat = waktuBuat;
    }   // encapsulation , method: setter

    public void tambahResep(String namaBahan) {
        this.resepBahan.add(namaBahan);
    }  // encapsulation , method: setter

    public ArrayList<String> getResepBahan() {  // encapsulation , method: getter
        return resepBahan;
    }

    @Override
    public int getStock() {
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
        return stock;
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
    }  // polymorphism & encapsulation , method: getter

    @Override
    public double getProtein() {
        return proteinTotal;
    }  // polymorphism & encapsulation , method: getter

    @Override
    public double getSugarLvl() {
        return sugarLvlTotal;
    }  // polymorphism & encapsulation , method: getter

    public void getTotalCalories() {  // encapsulation , method: getter
        this.caloriesTotal=0;

        System.out.println("\n------- Kalori -------");
        for (int i = 0; i < getResepBahan().size(); i++) {
            for (BahanBaku stok : Main.bahanBakuList) {
                if (stok.getNamaBahanBaku().equals(getResepBahan().get(i))) {

                    System.out.println("Kalori "+ stok.getNamaBahanBaku() +": " + stok.getCaloriesBahanBaku());
                    this.caloriesTotal += stok.getCaloriesBahanBaku();
                    break;
                }
            }
        }
    }

    public void getTotalProtein() {  // encapsulation , method: getter
        this.proteinTotal=0;

        System.out.println("\n------- Protein -------");
        for (int i = 0; i < getResepBahan().size(); i++) {
            for (BahanBaku stok : Main.bahanBakuList) {
                if (stok.getNamaBahanBaku().equals(getResepBahan().get(i))) {

                    System.out.println("Protein "+ stok.getNamaBahanBaku() +": " + stok.getProteinBahanBaku());
                    proteinTotal += stok.getProteinBahanBaku();
                    break;
                }
            }
        }
    }

    public void getTotalSugarLvl() {  // encapsulation , method: getter
        this.sugarLvlTotal=0;

        System.out.println("\n------- Gula -------");
        for (int i = 0; i < getResepBahan().size(); i++) {
            for (BahanBaku stok : Main.bahanBakuList) {
                if (stok.getNamaBahanBaku().equals(getResepBahan().get(i))) {

                    System.out.println("Gula "+ stok.getNamaBahanBaku() +": " + stok.getSugarLvlBahanBaku());
                    sugarLvlTotal += stok.getSugarLvlBahanBaku();
                    break;
                }
            }
        }
    }


    public int getWaktuBuat() {
        return this.waktuBuat;
    }   // encapsulation , method: getter

    @Override
    public String displayInfo() { // polymorrphism
        this.getStock();
        this.getTotalCalories();
        this.getTotalProtein();
        this.getTotalSugarLvl();
        return foodName + " | total calories: " + caloriesTotal + " | total sugar: " + sugarLvlTotal + " | total protein: " + proteinTotal +
                " | sisa stock: " + this.stock + " | harga: " + this.harga;
    }

    @Override
    public boolean isHealthy() {
        return caloriesTotal < 800 && sugarLvlTotal < 25;
    } // polymorphism
}
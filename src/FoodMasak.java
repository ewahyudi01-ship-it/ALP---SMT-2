import java.util.ArrayList;

public class FoodMasak extends FoodItem {
    private ArrayList<BahanBaku> bahanBakuList;


    public FoodMasak(String foodName, double harga, int stock) {
        super(foodName, harga, stock);
        this.bahanBakuList = new ArrayList<BahanBaku>();
    }

    public ArrayList<BahanBaku> getBahanBakuList() {
        return bahanBakuList;
    }



    @Override
    public String displayInfo() {
        return foodName + " | calories: "+ " | sugar lvl: "  +
                " | sisa stock: " + this.stock + " | harga: " + this.harga;
    }

    @Override
    public boolean isHealthy() {

        return false;
    }
}

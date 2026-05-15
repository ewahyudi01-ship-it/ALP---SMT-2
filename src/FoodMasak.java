import java.util.ArrayList;

public class FoodMasak extends FoodItem {
    private ArrayList<BahanBaku> bahanBakuList;


    public FoodMasak(String foodName, double harga, int stock) {
        super(foodName, harga, stock);
        this.bahanBakuList = new ArrayList<BahanBaku>();
    }

    @Override
    public String displayInfo() {
        return "";
    }

    @Override
    public boolean isHealthy() {

        return false;
    }
}

//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
import java.io.File;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BahanBaku {
    private String namaBahanBaku;
    private int stockBaku;

    private double calories;
    private double protein;
    private double sugarLvl;

    // API Key dari API Ninjas (Ganti dengan API Key milikmu)
    private static final String API_KEY = "kJuvzfwcnNJdpF7Wjrh3WNEljHKUN1QH8vdezPiV";

    public BahanBaku(String namaBahanBaku, int stockBaku) {
        this.namaBahanBaku = namaBahanBaku;
        this.stockBaku = stockBaku;
        ambilNutrisiLocal();
    }

    private void ambilNutrisiLocal() {

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root =
                    mapper.readTree(new File("nutrition.JSON"));
            JsonNode food =
                    root.get(this.namaBahanBaku.toLowerCase());

            if (food != null) {
                this.calories =
                        food.get("calories").asDouble();
                this.protein =
                        food.get("protein").asDouble();

                this.sugarLvl =
                        food.get("sugar").asDouble();
            }
            else {
                System.out.println(
                        "Data tidak ditemukan: "
                                + namaBahanBaku );
                this.calories = 0;
                this.protein = 0;
                this.sugarLvl = 0;
            }
        }
        catch (Exception e) {

            e.printStackTrace();
        }
    }

    public int getStockBaku() {
        return stockBaku;
    }

    public void kurangiStockBaku(int n3) {
        this.stockBaku -= n3;
    }
    public String getNamaBahanBaku() {
        return namaBahanBaku;
    }
    public double getCalories() {
        return calories;
    }
    public double getSugarLvl() {
        return sugarLvl;
    }
    public double getProtein() {
        return protein;
    }

}

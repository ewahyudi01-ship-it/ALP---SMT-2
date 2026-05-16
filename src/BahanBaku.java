import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class BahanBaku {
    private String namaBahanBaku;
    private int stockBaku;

    private double calories;
    private double protein;
    private double sugarLvl;

    // API Key dari API Ninjas (Ganti dengan API Key milikmu)
    private static final String API_KEY = "kJuvzfwcnNJdpF7Wjrh3WNEljHKUN1QH8vdezPiV";

    public BahanBaku(String namaBahanBaku) {
        this.namaBahanBaku = namaBahanBaku;
        ambilNutrisiAPI();
    }

    private void ambilNutrisiAPI() {
        OkHttpClient client = new OkHttpClient();

        // Panggil API Ninja Nutrition berdasarkan nama bahan baku
        Request request = new Request.Builder()
                .url("https://api.api-ninjas.com/v1/nutrition?query=" + this.namaBahanBaku)
                .addHeader("X-Api-Key", API_KEY)
                .build();

        try (Response response = client.newCall(request).execute()) { // ada try and catch karena program ini harus koneksi ke server, otherwise jika misalnya data gk bisa ambil dari server, maka masuk ke catch
            if (response.isSuccessful() && response.body() != null) {
                String jsonResponse = response.body().string(); //mengubah data ke JSON
                JsonArray jsonArray = JsonParser.parseString(jsonResponse).getAsJsonArray(); //dan memasuk format data yg terubah dari JSON, ke sini

                if (jsonArray.size() > 0) {
                    // Ambil objek pertama yang paling cocok
                    JsonObject infoNutrisi = jsonArray.get(0).getAsJsonObject();

                    // Set atribut berdasarkan response API (per 100g biasanya)
                    this.calories = infoNutrisi.get("calories").getAsDouble();
                    this.sugarLvl = infoNutrisi.get("sugar_g").getAsDouble();
                    this.protein = infoNutrisi.get("protein_g").getAsDouble();
                } else {
                    System.out.println("Bahan baku '" + namaBahanBaku + "' tidak ditemukan di API.");
                }
            }
        } catch (IOException e) {
            System.out.println("Gagal mengambil data untuk: " + namaBahanBaku + ". Error: " + e.getMessage());
        }
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

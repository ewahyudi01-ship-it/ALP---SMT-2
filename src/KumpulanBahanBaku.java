import java.io.File;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject; // membuat struktur berupa key, value seperti 'hashmap'
import org.json.JSONTokener; // mengubah data-data mentah yang dapat dipahami oleh java seperti translator
import java.io.FileReader; // untuk membaca di dalam file
import java.io.FileWriter; // untuk menulis di dalam file

public class KumpulanBahanBaku {
    private String namaBahanBaku;
    private int stockBaku;

    private double calories;
    private double protein;
    private double sugarLvl;

    public KumpulanBahanBaku(String namaBahanBaku, int stockBaku) {
        this.namaBahanBaku = namaBahanBaku;
        this.stockBaku = stockBaku;
        ambilNutrisiLocal();
    }

    private void ambilNutrisiLocal() {   // encapsulation , method: getter

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(new File("nutrition.JSON"));
            JsonNode food = root.get(this.namaBahanBaku.toLowerCase());

            if (food != null) {
                this.calories = food.get("calories").asDouble();
                this.protein = food.get("protein").asDouble();
                this.sugarLvl = food.get("sugar").asDouble();
            }
            else {
                System.out.println("Data not found: " + namaBahanBaku );
                this.calories = 0;
                this.protein = 0;
                this.sugarLvl = 0;
            }
        }
        catch (Exception e) {

            e.printStackTrace();
        }
    }
    public static void addNewIngredientToJSON(String name, int calories, double protein, double sugar) {  // encapsulation , method: setter
        try {
            File file = new File("nutrition.JSON"); // target file
            JSONObject rootObject; // menyiapkan "{" dan "}" ke dalam file JSON

            // 1. Cek apakah file sudah ada isinya. Jika ada, baca data lamanya dulu.
            if (file.exists() && file.length() > 0) {
                FileReader reader = new FileReader(file); // Membuka jalur pipa pembacaan data dari file fisik.
                JSONTokener tokener = new JSONTokener(reader);
                rootObject = new JSONObject(tokener);
                reader.close();                          // menutup jalur pipa
            } else {
                // Jika kosong, maka buat JSON Object kosong baru
                rootObject = new JSONObject();
            }

            // 2. Buat sub-object JSON baru untuk menampung nutrisi bahan baku baru, dengan membuat kurung kurawal baru
            JSONObject nutritionDetails = new JSONObject();
            nutritionDetails.put("calories", calories);
            nutritionDetails.put("protein", protein);
            nutritionDetails.put("sugar", sugar);

            // 3. Masukkan sub-object ke dalam rootObject dengan nama bahan baku sebagai key
            rootObject.put(name.toLowerCase(), nutritionDetails);

            // 4. Tulis kembali data yang sudah digabungkan ke dalam file JSON (Indent 4 spasi agar rapi)
            FileWriter writer = new FileWriter(file);
            writer.write(rootObject.toString(4));
            writer.flush();
            writer.close();

            System.out.println(" Successfully added " + name + " to nutrition.JSON!");

        } catch (Exception e) {
            System.out.println("⚠ Failed to write to JSON file: " + e.getMessage());
        }
    }

    public void addIngredient(int n) {
        stockBaku += n;
    }   // encapsulation , method: setter
    public int getStockBaku() {
        return stockBaku;
    }     // encapsulation , method: getter
    public void kurangiStockBaku(int n3) {
        this.stockBaku -= n3;
    }  // encapsulation , method: setter

    public String getNamaBahanBaku() {
        return namaBahanBaku;
    }  // encapsulation , method: getter
    public double getCaloriesBahanBaku() {
        return calories;
    }  // encapsulation , method: getter
    public double getSugarLvlBahanBaku() {return sugarLvl;}  // encapsulation , method: getter
    public double getProteinBahanBaku() {
        return protein;
    }  // encapsulation , method: getter

}

public class FoodJadi extends FoodItem {

    private double calories;
    private double protein;
    private double sugarLvl;

    public FoodJadi(String foodName, int calories, double harga, int sugarLvl, int protein, int stock) {
        super(foodName, harga, stock);
        this.calories = calories;
        this.protein = protein;
        this.sugarLvl = sugarLvl;
    }

    public void reduceStock (int n3){
        this.stock -= n3;
    }

    @Override
    public String displayInfo(){
        return  foodName + " | calories: " + this.calories + " | sugar lvl: " + this.sugarLvl + " | protein: " + this.protein +
                " | sisa stock: " + this.stock + " | harga: " + this.harga;
    }

    @Override
    public boolean isHealthy() {
        if(calories < 500 && sugarLvl < 20 && protein > 10) {
            return true;
        }
        return false;
    }



}

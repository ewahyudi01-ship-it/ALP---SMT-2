public class FoodItem {

    protected String foodName;
    protected int calories;
    protected double harga;
    protected int sugarLvl;
    protected int protein;
    protected int stock;

    public FoodItem(String foodName, int calories, double harga, int sugarLvl, int protein , int stock){
        this.foodName = foodName;
        this.calories = calories;
        this.harga = harga;
        this.sugarLvl = sugarLvl;
        this.protein = protein;
        this.stock = stock;
    }

    public String displayInfo(){
        return "Food Name: " + foodName + " | calories: " + this.calories + " | sugar lvl: " + this.sugarLvl +
                " | sisa stock: " + this.stock + " | harga: " + this.harga;
    }

    public boolean isHealthy(){
        if(calories < 500 && sugarLvl < 20 && protein > 10) {
            return true;
        }
        return false;
    }

    public void reduceStock(){

    }



}

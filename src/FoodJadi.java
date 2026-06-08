public class FoodJadi extends FoodItem {

    private double calories;
    private double protein;
    private double sugarLvl;

    public FoodJadi(String foodName, double calories, double harga, double sugarLvl, double protein, int stock) {
        super(foodName, harga, stock); // super
        this.calories = calories;
        this.protein = protein;
        this.sugarLvl = sugarLvl;
    }

    public void reduceStock (int n3){
        this.stock -= n3;
    }  // encapsulation , method: setter

    public void addStock(int n3){
        this.stock += n3;
    }  // encapsulation , method: setter

    @Override
    public double getCalories() {  // encapsulation , method: getter
        return calories;
    }

    @Override
    public double getProtein() {  // encapsulation , method: getter
        return protein;
    }

    @Override
    public double getSugarLvl() {
        return sugarLvl;
    }  // encapsulation , method: getter

    public String displayStock(){
        return  foodName + " | Stock: " + this.stock;
    }  // encapsulation , method: getter

    @Override
    public String displayInfo(){  // polymorphism
        return  foodName + " | calories: " + this.calories + " | sugar lvl: " + this.sugarLvl + " | protein: " + this.protein +
                " | sisa stock: " + this.stock + " | harga: " + this.harga;
    }

    @Override
    public boolean isHealthy() {  // polymorphism
        if(calories < 600 && sugarLvl < 25) {
            return true;
        }
        return false;
    }

}
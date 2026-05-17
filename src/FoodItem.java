public abstract class FoodItem {

    protected String foodName;
    protected double harga;
    protected int stock;

    public FoodItem(String foodName, double harga, int stock){
        this.foodName = foodName;
        this.harga = harga;
        this.stock = stock;
    }

    public abstract String displayInfo();

    public abstract boolean isHealthy();

    public void reduceStock(int n3){
        this.stock -= n3;
    }
    public String getFoodName() {
        return foodName;
    }
    public int  getStock() {
        return stock;
    }
    public double getHarga() {
        return harga;
    }

    public FoodItem getFoodItem(){
        return this;
    }

}

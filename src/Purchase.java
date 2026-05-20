public class Purchase {

    private User user;
    private FoodItem foodItem;
    private int quantity;

    public long waktuPesan = System.currentTimeMillis() / 1000; // mencatat detik saat ini
    public int totalWaktu;

    public Purchase(User user, FoodItem foodItem, int quantity){
        this.user = user;
        this.foodItem = foodItem;
        this.quantity = quantity;
    }

    public double getCalculateTotal(){
        return foodItem.getHarga() * quantity;
    }

    public double calculateCheck(User user, FoodItem foodItem, int quantity) {
        return user.saldo - foodItem.harga * quantity;

    }

    public void printReceipt() {
        System.out.println("Transaction completed! you bought " + foodItem.foodName+ " for " + this.quantity);
        System.out.println("Cost: "+ getCalculateTotal());

    }

    public FoodItem getFoodItem() {
        return foodItem;
    }

    public User getUser (){
        return user;
    }
    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {

        return foodItem.getFoodName() +
                " x" + quantity;
    }

    public void addWaktu(int waktuPesan) {
        this.totalWaktu = waktuPesan * quantity;
    }
}

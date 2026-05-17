public class Purchase {

    private User user;
    private FoodItem foodItem;
    private int quantity;

    public long waktuPesan = System.currentTimeMillis() / 1000; // mencatat detik saat ini
    public int totalWaktu = 10 * quantity; // Ketentuan: 10 detik per produk (otomatis bertambah sesuai kuantitas)

    public Purchase(User user, FoodItem foodItem, int quantity){
        this.user = user;
        this.foodItem = foodItem;
        this.quantity = quantity;

        this.totalWaktu = 10 * quantity;
    }

    public double calculateTotal(){
        return foodItem.getHarga() * quantity;
    }

    public static double calculateCheck(User user, FoodItem foodItem, int quantity) {
        return user.saldo - foodItem.harga * quantity;

    }

    public void printReceipt() {
        System.out.println("Transaction completed! you bought " + foodItem.foodName+ " for " + this.quantity);
        System.out.println("Cost: "+calculateTotal());

    }
    public int getTotalCookingTime() {
        return (int) foodItem.harga * quantity;
    }

    public FoodItem getFoodItem() {
        return foodItem;
    }

    public int getQuantity() {
        return quantity;
    }
    @Override
    public String toString() {

        return foodItem.getFoodName() +
                " x" + quantity;
    }
}

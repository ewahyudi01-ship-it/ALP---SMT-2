public class Purchase {

    private User user;
    private FoodItem foodItem;
    private int quantity;

    public Purchase(User user, FoodItem foodItem, int quantity){
        this.user = user;
        this.foodItem = foodItem;
        this.quantity = quantity;
    }


    public double calculateTotal() {
        return foodItem.harga * this.quantity;
    }

    public void printReceipt() {
        System.out.println("you bought " + foodItem.foodName+ " for " + this.quantity);
        System.out.println("Cost: "+calculateTotal());

    }

}

public class Purchase {

    private User user;
    private FoodItem foodItem;
    private int quantity;

    public  Purchase(User user, FoodItem foodItem, int quantity){
        this.user = user;
        this.foodItem = foodItem;
        this.quantity = quantity;
    }

    public double calculateTotal() {
        return 0;
    }

    public void printReceiptt() {
    }

}

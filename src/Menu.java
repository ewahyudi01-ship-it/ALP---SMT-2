import java.util.ArrayList;

public class Menu {
    private String namaMenu;
    private ArrayList<FoodItem> foodItem;

    public Menu(String namaMenu, ArrayList<FoodItem> foodItem) {
        this.namaMenu = namaMenu;
        this.foodItem = foodItem;
    }

    public String getNamaMenu() {
        return namaMenu;
    }
    public void setNamaMenu(String namaMenu) {
        this.namaMenu = namaMenu;
    }
    public ArrayList<FoodItem> getFoodItem() {
        return foodItem;
    }
    public void setFoodItem(ArrayList<FoodItem> foodItem) {
        this.foodItem = foodItem;
    }

}

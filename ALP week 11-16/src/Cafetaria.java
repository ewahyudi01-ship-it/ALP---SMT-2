public class Cafetaria {
  private ArrayList<FoodItem> foodItems;
    private Queue<Purchase> orders;

    public Cafetaria(){
        foodItems = new ArrayList<FoodItem>();
        foodItems.add(new FoodItem("Steak",300, 46000, 0, 20,15));
        foodItems.add(new FoodItem("Apple",50, 3000, 20, 0,25));
        foodItems.add(new FoodItem("Snack beng-beng (regular)",100, 9000, 11, 1,10));
        foodItems.add(new FoodItem("Pringles (small)",98, 11000, 1, 16,6));
        foodItems.add(new FoodItem("Bottle Aqua",0, 6000, 0, 0,30));
        foodItems.add(new FoodItem("Salad",12, 8000, 5, 0,19));
        foodItems.add(new FoodItem("Toast Bread",65, 5000, 2, 9,5));
        foodItems.add(new FoodItem("Mi Goreng",350, 5000, 49, 8,20));
        foodItems.add(new FoodItem("Indomilk 100ml",72, 5000, 4, 3,14));
    }

    public void showMenu(){
        for (int i = 0; i < foodItems.size(); i++) {
            System.out.println( i+1+". "+foodItems.get(i).displayInfo());
        }
    }
}

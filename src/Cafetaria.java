import java.util.*;

public class Cafetaria {
    //encapsulation
    private ArrayList<Menu> menuList; // arraylist
    private Queue<Purchase> orders; //polymorphism  queue

    public Cafetaria(ArrayList<Menu> menuList) {
        this.menuList = menuList;
        this.orders = new LinkedList<Purchase>();
    }

    public void showAllMenu(){
        for (int i = 0; i < menuList.size(); i++) {
            System.out.println( i+1+". "+getMenu(i).getNamaMenu());
            for (int j = 0; j < getMenu(i).getFoodItem().size(); j++) {
                System.out.println("- "+getMenu(i).getFoodItem().get(j).getFoodName() + " | harga: Rp." + getMenu(i).getFoodItem().get(j).getHarga());
            }
            System.out.println("");
        }
        System.out.println("---------------------------------------------------");
    }
    public void showOrders(){
        int i = 1;
        for (Purchase order : orders) {
            System.out.println(i+". "+order);
            i++;
        }
    }

    public int totalTimeProductionFoodMasak(Purchase current) {
        if (orders == null || orders.isEmpty() || current == null) {
            return 0;
        }

        int timeTotal = 0;
        for (Purchase p : this.orders) {
            // JANGAN pakai p.totalWaktu lagi. Ganti dengan sisa waktu berjalannya!
            timeTotal += p.getTotalWaktu();

            if (p == current) {
                return timeTotal;
            }
        }
        return 0;
    }

    //encapsulation
    public Purchase removeOrder() {
        return orders.poll();
    }  // encapsulation , method: setter

    public void addOrder(Purchase order){
        orders.add(order);
    }  // encapsulation , method: setter

    //getter & setter
    public Menu getMenu(int n) {
        return menuList.get(n);
    }  // encapsulation , method: getter
    public Menu getMainMenu() {
        return menuList.get(0);
    }  // encapsulation , method: getter

    public ArrayList<Menu> getMenuList() {
        return menuList;
    }  // encapsulation , method: getter

    public Purchase getCurrentOrder() { // encapsulation , method: getter
        return orders.peek();
    }

    public  Queue<Purchase> getOrders() {
        return orders;
    }  // encapsulation , method: getter
    public int getMenuSize() {
        return menuList.size();
    }  // encapsulation , method: getter
}


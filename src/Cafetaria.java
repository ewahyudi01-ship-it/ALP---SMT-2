import java.util.*;

public class Cafetaria {

    private String namaCafeteria;
    private ArrayList<Menu> menuList;
    private Queue<Purchase> orders;

    public Cafetaria(String namaCafeteria, ArrayList<Menu> menuList) {
        this.namaCafeteria = namaCafeteria;
        this.menuList = menuList;
    }

    public void showAllMenu(){
        for (int i = 0; i < menuList.size(); i++) {
            System.out.println( i+1+". "+getMenu(i).getNamaMenu());
            for (int j = 0; j < getMenu(i).getFoodItem().size(); j++) {
                System.out.println("- "+getMenu(i).getFoodItem().get(j).getFoodName() + " | harga: Rp." + getMenu(i).getFoodItem().get(j).getHarga());
            }
        }
    }
    public void showOrders(){
        int i = 1;
        for (Purchase order : orders) {
            System.out.println(i+". "+order);
            i++;
        }
    }

    //getter & setter
    public Menu getMenu(int n) {
        return menuList.get(n);
    }
    public  Queue<Purchase> getOrders() {
        return orders;
    }

}



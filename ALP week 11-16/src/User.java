import java.util.ArrayList;
import java.util.Stack;

public abstract class User {

    protected String username;
    protected String password;
    protected double saldo;
    protected int points;
    protected String preferensiMakanan;
    protected ArrayList<Purchase> historiPembelian;
    protected Stack recentPurchases;
    private HealthReport healthReport;

    public  User(String username, String password, double saldo, int points, String preferensiMakanan) {
        this.username = username;
        this.password = password;
    }

    //method2 void dll
    public abstract void menuUtama();

    public void buyFood(){

    }

    public void melihatHistori(){

    }

    public void menerimaReward(){

    }

    public void melihatLaporanKesehatan(){

    }
    public abstract double getDiscount();

    //setter & getter
    public String getNama(){
        return  username;
    }

    public String getPassword(){
        return password;
    }

}

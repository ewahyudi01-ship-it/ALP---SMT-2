public class Siswa extends User{
    public Siswa(String username, String password){
        super(username, password);
    }

    @Override
    public void menuUtama() {
        System.out.println("=== Welcome to the Smart Canteen  "+username+"! ===");
    }

    @Override
    public double getDiscount() {
        return 0;
    }
}

public class MahaSiswa extends User {
    public MahaSiswa(String username, String password) {
        super(username, password);
    }

    @Override
    public void menuUtama() {
        System.out.println("=== Welcome to the Smart Canteen  "+username+"! :0 ===");
    }

    @Override
    public double getDiscount() {
        return 0;
    }
}

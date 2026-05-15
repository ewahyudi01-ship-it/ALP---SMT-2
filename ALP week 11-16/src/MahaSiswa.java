public class MahaSiswa extends User {
    public MahaSiswa(String username, String password, double saldo, int points) {
        super(username, password, saldo, points);
    }

    @Override
    public double getDiscount() {
        return 0;
    }
}

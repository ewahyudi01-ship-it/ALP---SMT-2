public class Siswa extends User{
    public Siswa(String username, String password, double saldo, int points){
        super(username, password, saldo, points);
    }


    @Override
    public double getDiscount() {
        return 0;
    }
}

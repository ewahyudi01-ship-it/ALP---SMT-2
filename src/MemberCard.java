import java.time.LocalDate;

public class MemberCard {

    private String iDCard;
    private LocalDate cardExpiry;
    private boolean isCardActive;
    private Rank rankSubscription;

    public enum Rank {
        REGULAR, PREMIUM;
    }

    public MemberCard(String iDCard, Rank rankSubscription, int n) {
        this.iDCard = iDCard;
        this.rankSubscription = rankSubscription;

        // LocalDate.now() mengambil tanggal lokal hari ini di komputer/server
        // .plusDays() otomatis menghitung tanggal ke depan (aman dari masalah kabisat/perbedaan jumlah hari dalam sebulan)
        this.cardExpiry = LocalDate.now().plusDays(n);
    }

    public void upgradeCardPremium() {
        this.rankSubscription = Rank.PREMIUM;
    }

    public boolean isCardActive() {
        LocalDate hariIni = LocalDate.now();

        if (!hariIni.isAfter(cardExpiry)) { // Kartu aktif JIKA hari ini belum melewati (isAfter) tanggal expiry,
            return isCardActive = true;   // atau hari ini PAS di tanggal expiry-nya.
        } else {
            return isCardActive = false;
        }
    }
    public void setCardExpiry(int n) {
        LocalDate hariIni = LocalDate.now();

        if (isCardActive()) {
            // Jika masih aktif, akumulasikan dari tanggal expiry yang lama
            this.cardExpiry = this.cardExpiry.plusDays(n);
        } else {
            // Jika sudah hangus, hitung masa aktif baru mulai dari HARI INI, tanpa "hariIni" maka plusDays(n) menambah perpanjangan hari di waktu kartu sudah expire atau bukan HARI INI
            this.cardExpiry = hariIni.plusDays(n);
        }
        System.out.println("Your member card has been extended to: " + cardExpiry);
    }

    public LocalDate getMemberCard() {
        return this.cardExpiry;
    }

    public boolean hasMemberCard() {
        if (this == null) {
            return false;

        } else {
            return true;
        }
    }

    public String getIdCard() {
        return iDCard;
    }

    public Rank getRankSubscription() {
        return rankSubscription;
    }

}

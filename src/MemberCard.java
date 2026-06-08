import java.time.LocalDate;

public class MemberCard {

    private String iDCard;
    private LocalDate cardExpiry;
    private boolean isCardActive;
    private Rank rankSubscription;

    public static final double PRICE_REGULAR = 14000;
    public static final double PRICE_PREMIUM = 850000;

    public static final double PRICE_3_DAYS = 5000;
    public static final double PRICE_7_DAYS = 10650;
    public static final double PRICE_30_DAYS = 34500;

    public static final int DURATION_3_DAYS = 3;
    public static final int DURATION_7_DAYS = 7;
    public static final int DURATION_30_DAYS = 30;

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

    public void upgradeCardPremium() {   // encapsulation , method: setter
        this.rankSubscription = Rank.PREMIUM;
        this.cardExpiry = null;
    }

    public boolean isCardActive() {   // encapsulation , method: getter
        if (rankSubscription == Rank.PREMIUM) {
            return  true;
        }

        LocalDate hariIni = LocalDate.now();
        if (!hariIni.isAfter(cardExpiry)) { // Kartu aktif JIKA hari ini belum melewati (isAfter) tanggal expiry,
            return isCardActive = true;   // atau hari ini PAS di tanggal expiry-nya.
        } else {
            return isCardActive = false;
        }
    }

    public void setCardExpiry(int n) {   // encapsulation , method: setter
        LocalDate hariIni = LocalDate.now();

        if (isCardActive()) {
            System.out.println("\n╔═══════════════════════════════════════════════════════════════════════════════╗");
            System.out.print("║Your member card has been extended from: (" + cardExpiry +") ");
            // Jika masih aktif, akumulasikan dari tanggal expiry yang lama
            this.cardExpiry = this.cardExpiry.plusDays(n);
        } else {
            System.out.print("║Your member card has been extended from: (" + hariIni +") ");
            // Jika sudah hangus, hitung masa aktif baru mulai dari HARI INI, tanpa "hariIni" maka plusDays(n) menambah perpanjangan hari di waktu kartu sudah expire atau bukan HARI INI
            this.cardExpiry = hariIni.plusDays(n);
        }
        System.out.println("---> ("+  cardExpiry + ")        ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════════════╝");
    }

    public LocalDate getMemberCardExpiry() {
        return this.cardExpiry;
    }  // encapsulation , method: getter

    public boolean hasMemberCard() {
        if (this == null) {
            return false;

        } else {
            return true;
        }
    }

    public String getIdCard() {
        return iDCard;
    }  // encapsulation , method: getter

    public Rank getRankSubscription() {
        return rankSubscription;
    }  // encapsulation , method: getter

}


public class MemberCard {

    private String iDCard;
    private String cardExpiry;
    private boolean isCardActive;

    public  MemberCard(String iDCard, String cardExpiry) {
        this.iDCard = iDCard;
    }

    public String getiDCard() {
        return iDCard;
    }
    public String getCardExpiry() {
        return cardExpiry;
    }


    public void setCardExpiry(String cardExpiry) {
        this.cardExpiry = cardExpiry;
    }


}

package ee.taltech.iti0202.kt1.mail;

public class Letter {
    private String recipient;
    private String city;
    private String address;

    /**
     * Luuakse uus kiri adressaadi nimega, linna ja aadressiga
     *
     * @param recipient       adressaadi nimi
     * @param destinationCity adressaadi linn
     * @param address         adressaadi aadress
     */
    public Letter(String recipient, String destinationCity, String address) {
        this.recipient = recipient;
        city = destinationCity;
        this.address = address;
    }

    /**
     * Tagastab kirja linna
     *
     * @return linn
     */
    public String getDestinationCity() {
        return city;
    }

    /**
     * Muudab linna
     *
     * @param destinationCity linn
     */
    public void setDestinationCity(String destinationCity) {
        city = destinationCity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    @Override
    public String toString() {
        return "City: " + city + ", Address: " + address + ", Recipient: " + recipient;
    }
}

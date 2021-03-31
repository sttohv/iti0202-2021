package ee.taltech.iti0202.kt1.mail;

public class Letter {
    private String recipient;
    private String city;
    private String address;

    /**
     * Create a new letter with recipient, destination city and address.
     */
    public Letter(String recipient, String destinationCity, String address) {
        this.recipient = recipient;
        city = destinationCity;
        this.address = address;
    }

    public String getDestinationCity() {
        return city;
    }

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

    /**
     * String representation of the letter.
     * The format is: City: %s, Address: %s, Recipient: %s
     */
    public String toString() {
        return "City: " + city + ", Address: " + address + ", Recipient: " + recipient;
    }
}

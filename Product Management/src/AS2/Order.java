package AS2;


public class Order {
    private String pcode;
    private String ccode;
    private int quantity;

    @Override
    public String toString() {
        String s = pcode + "|" + ccode + "|" + quantity;
        return s;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getCcode() {
        return ccode;
    }

    public void setCcode(String ccode) {
        this.ccode = ccode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order(String pcode, String ccode, int quantity) {
        this.pcode = pcode;
        this.ccode = ccode;
        this.quantity = quantity;
    }

    public Order() {
    }
}

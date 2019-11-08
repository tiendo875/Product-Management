package AS2;


public class Product {

    private String pcode;
    private String pro_name;
    private int quantity;
    private int saled;

    private double price;

    @Override
    public String toString() {
        String s = pcode + " |   " + pro_name + "   |  " + quantity + " |  " + saled + "  |     " + price;
        return s;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSaled() {
        return saled;
    }

    public void setSaled(int saled) {
      
        this.saled = saled;
    }
    
    public Product() {
    }

    public Product(String pcode, String pro_name, int quantity, int saled, double price) {
        this.pcode = pcode;
        this.pro_name = pro_name;
        this.quantity = quantity;
        this.saled = saled;
        this.price = price;
    }

}

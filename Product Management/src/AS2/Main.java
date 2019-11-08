package AS2;


import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here        
        Menu();
    }

    public static int check() {

        int check = 0;
        int a = 0;
        Scanner s = new Scanner(System.in);
        while (check == 0) {
            try {
                a = Integer.parseInt(s.nextLine());
                check = 1;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number");
                System.out.print("Re-enter: ");
            }
        }
        return a;
    }

    public static void Menu() throws IOException {        
        ProductList b = new ProductList();
        CustomerList c = new CustomerList();
        OrderList o = new OrderList(b,c);
        int option;
        do {
            System.out.println("----Sale Management System----");
            System.out.println("1. Product list");
            System.out.println("2. Customer list");
            System.out.println("3. Order list");
            System.out.println("0. Exit");
            System.out.print("Option: ");
            option = check();
            switch (option) {
                case 1:
                    PMenu(b);
                    break;
                case 2:
                    CMenu(c);
                    break;
                case 3:
                    OMenu(o);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Please enter 0-3");
            }
        } while (option != 0);

    }

    public static void PMenu(ProductList b) throws IOException {
        String pcode;
        Scanner s = new Scanner(System.in);
        int option;
        do {
            System.out.println("----Products----");
            System.out.println("1. Load data from file");
            System.out.println("2. Input & add to the head");
            System.out.println("3. In-order traverse");
            System.out.println("4. Breadth-first traverse");
            System.out.println("5. In-order traverse to file");
            System.out.println("6. Search by pcode");
            System.out.println("7. Delete by pcode by copying");
            System.out.println("8. Simply balancing");
            System.out.println("9. Count number of products");
            System.out.println("0. Exit");
            System.out.print("Option: ");
            option = check();
            switch (option) {
                case 1:
                    b.clear();
                    b.loadFile("product.txt");
                    System.out.println("Loading successfully");
                    break;
                case 2:
                    System.out.println("Input");
                    try {
                        b.insert(PInput());
                    } catch (Exception ex) {
                        System.err.println(ex.getMessage());
                    }
                    b.inOrder(b.root);
                    break;
                case 3:
                    System.out.println("Code|ProductName|Quantity|Saled|Price|Value");
                    b.inOrder(b.root);
                    break;
                case 4:
                    b.breadth(b.root);
                    break;
                case 5:
                    b.inOrderToFile("product.txt");
                    System.out.println("Write to file successfully");
                    break;
                case 6:
                    System.out.println("Search");
                    System.out.print("Enter pcode to search: ");
                    pcode = s.nextLine().toUpperCase();
                    {
                        Product p = b.SearchByCode(pcode);
                        if ( p == null){
                            System.err.println("Not found");
                            break;
                        }
                        System.out.println(p.toString());
                    }
                    break;
                case 7:
                    System.out.println("Delete");
                    System.out.print("Enter pcode to delete: ");
                    pcode = s.nextLine().toUpperCase();
                    try {
                        b.deleteByCopy(pcode);
                    } catch (Exception ex) {
                        System.err.println("Not found");
                        break;
                    }
                    b.inOrder(b.root);
                    break;
                case 8:
                    System.out.println("Balance");
                    b.balance();
                    b.breadth(b.root);
                    //b.inOrder(b.root);
                    break;
                case 9:
                    System.out.println("Count");
                    System.out.println("Number of products: " + b.countProduct(b.root));
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Please enter 0-9");
            }
        } while (option != 0);
    }

    public static void CMenu(CustomerList c) throws IOException {
        String ccode;
        Scanner s = new Scanner(System.in);
        int option;
        do {
            System.out.println("----Customers----");
            System.out.println("1. Load data from file");
            System.out.println("2. Input & add to the end");
            System.out.println("3. Display data");
            System.out.println("4. Save customer list to file");
            System.out.println("5. Search by ccode");
            System.out.println("6. Delete by ccode");
            System.out.println("0. Exit");
            System.out.print("Option: ");
            option = check();
            switch (option) {
                case 1:
                    c.clear();
                    c.loadFile("Customers.txt");
                    break;
                case 2:
                    System.out.println("Input");
                    c.addLast(CInput());
                    c.display();
                    break;
                case 3:
                    System.out.println("Code | Name | Phone");
                    c.display();
                    break;
                case 4:
                    c.saveFile("Customers.txt");
                    break;
                case 5:
                    System.out.println("Search");
                    System.out.print("Enter ccode to search: ");
                    ccode = s.nextLine().toUpperCase();
                    {
                        Customer cc = c.searchByccode(ccode);
                        if (cc == null){
                            System.err.println("Not Found");
                            break;
                        }
                        System.out.println(cc.toString());
                    }
                    break;
                case 6:
                    System.out.println("Delete");
                    System.out.print("Enter ccode to delete: ");
                    ccode = s.nextLine().toUpperCase();
                    try {
                        c.dele(ccode);
                    } catch (Exception ex) {
                        System.err.println("Not found");
                    }
                    c.display();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Please enter 0-6");
            }
        } while (option != 0);
    }

    public static void OMenu(OrderList o) {
        int option;
        do {
            System.out.println("----Ordering----");
            System.out.println("1. Input data");
            System.out.println("2. Display data");
            System.out.println("3. Sort  by pcode + ccode");
            System.out.println("0. Exit");
            System.out.print("Option: ");
            option = check();
            switch (option) {
                case 1:
                    System.out.println("Input");
                    o.add(OInput());
                    break;
                case 2:
                    System.out.println("ProductCode|CustomerCode|Quantity");
                    o.display();
                    break;
                case 3:
                    System.out.println("Sort");
                    o.sortByPcodeCcode();
                    o.display();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Please enter 0-3");
            }
        } while (option != 0);
    }

    private static Product PInput() {
        Scanner s = new Scanner(System.in);
        String pcode, pro_name;
        int quantity = 0;
        int saled = 0;
        double price = 0;
        System.out.print("Enter product's code: ");
        pcode = s.nextLine().toUpperCase();
        System.out.print("Enter product's name: ");
        pro_name = s.nextLine();
        while (true) {
            try {
                System.out.print("Enter quantity: ");
                quantity = Integer.parseInt(s.nextLine());
                System.out.print("Enter saled: ");
                saled = Integer.parseInt(s.nextLine());

                System.out.print("Enter price: ");
                price = Double.parseDouble(s.nextLine());
                if (saled > quantity) {
                    System.out.println("Quantity must greater than or equal Saled");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Quantity, Saled, Price must be numberic");
            }
        }
        return new Product(pcode, pro_name, quantity, saled, price);
    }

    private static Customer CInput() {
        Scanner s = new Scanner(System.in);
        String ccode, cus_name;
        String phone;

        System.out.print("Enter customer code: ");
        ccode = s.nextLine().toUpperCase();
        System.out.print("Enter customer name: ");
        cus_name = s.nextLine();

        while (true) {
            try {
                System.out.print("Enter customer phone: ");
                phone = s.nextLine();
                if (phone.matches("[0-9]+")) {
                    break;
                } else {
                    System.out.println("Numberic");
                }
            } catch (NumberFormatException e) {
                System.out.println("Phone must be numberic");
            }
        }
        return new Customer(ccode, cus_name, phone);
    }

    private static Order OInput() {
        Scanner s = new Scanner(System.in);
        String pcode, ccode;
        int quantity;
        System.out.print("Enter pcode: ");
        pcode = s.nextLine().toUpperCase();
        System.out.print("Enter ccode: ");
        ccode = s.nextLine().toUpperCase();
        System.out.print("Enter quantity: ");
        quantity = s.nextInt();

        return new Order(pcode, ccode, quantity);
    }
}

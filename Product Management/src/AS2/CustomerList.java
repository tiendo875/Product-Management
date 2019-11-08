package AS2;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

public class CustomerList {

    LinkedListNode<Customer> head;
    LinkedListNode<Customer> tail;

    public CustomerList() {
        head = tail = null;
    }

    void clear() {
        head = tail = null;
    }

    boolean isEmpty() {
        return (head == null);
    }

    //2.1. Load data from file 
    void loadFile(String fname) throws IOException {
        File file = new File(fname);
            if (file.createNewFile()) {
                System.out.println("New Text File is created!");
            }
        try (RandomAccessFile f = new RandomAccessFile(fname, "r") 
        ) {
            String s, ccode, cus_name, phone;
            String[] a;
            Customer x;
            while (true) {
                s = f.readLine();
                if (s == null || s.trim().equals("")) {
                    break;
                }
                a = s.split("[|]");
                ccode = a[0].trim();
                cus_name = a[1].trim();
                phone = a[2].trim();
                x = new Customer(ccode, cus_name, phone);
                addLast(x);
            }
            System.out.println("Customer List is loaded successfully");
        }
    }

    //2.2. Input & add to the end
    void addLast(Customer x) {
        LinkedListNode q = new LinkedListNode(x);
        if ((searchByccode(x.getCcode()) == null)) {
            if (isEmpty()) {
                head = tail = q;
                return;
            }
            tail.next = q;
            tail = q;
        }
        else            
            System.out.println("Ccode duplicate. Can't add");
    }
// 2.3.Display data
    void display() {
        LinkedListNode<Customer> p = head;
        while (p != null) {
            System.out.println(p.info);
            p = p.next;
        }
        System.out.println();
    }
//2.4.Save customer list to file
    void saveFile(String fname) throws IOException {
        try (PrintWriter f = new PrintWriter(fname)) {
            LinkedListNode<Customer> p = head;
            try {
                while (p != null) {
                    f.println(p.info.toString());
                    p = p.next;
                }
            } catch (Exception e) {
            }
        }    
    }

//2.5.Search by ccode
    Customer searchByccode(String ccode) {
        LinkedListNode<Customer> p = head;
        while (p != null) {
            if (p.info.getCcode().equals(ccode)) {
                return p.info;
            }
            p = p.next;
        }
        return (null);
    }
//2.6.Delete by ccode
     void deleFirst() {   
        if (isEmpty()) {
            return;
        }
        head = head.next;
        if (head == null) {
            tail = null;
        }
    }

    void dele(String ccode) throws Exception{
        if (isEmpty()) {
            return;
        }
        LinkedListNode<Customer> f = head;
        if (f.info.getCcode().equals(ccode)) {
            deleFirst();
            return;
        }
        while (f != null && !f.next.info.getCcode().equals(ccode)) {
            f = f.next;
        }
        if (f == null) {
            throw new Exception(); // ccode is not in the list
        }        
        LinkedListNode<Customer> q = f.next, q1 = q.next;
        f.next = q1;
        if (q == tail) {
            tail = f;
        }
    }
}

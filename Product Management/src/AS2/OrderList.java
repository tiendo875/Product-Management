package AS2;


public class OrderList {
    ProductList pl;
    CustomerList cl;
    LinkedListNode<Order> head;
    LinkedListNode<Order> tail;

    public OrderList(ProductList pl, CustomerList cl) {
        head = tail = null;
        this.pl = pl;
        this.cl = cl;
    }

    void clear() {
        head = tail = null;
    }

    boolean isEmpty() {
        return (head == null);
    }

    // 3.1. Input data
    void add(Order x) { 
        LinkedListNode q = new LinkedListNode(x);
        if (check(x)){
            if (isEmpty()) {
                head = tail = q;
                return;
            }
            tail.next = q;
            tail = q;
        }
    }
    
    LinkedListNode checkDuplicate(Order x){
        LinkedListNode<Order> p = head;
        while (p != null) {
            if (p.info.getPcode().equals(x.getPcode()) && p.info.getCcode().equals(x.getCcode())) {
                return p;
            }
            p = p.next;
        }
        return null;
    }

    boolean check(Order x) {
        LinkedListNode<Order> p = head;
        Product pro;
        if ((pro = pl.SearchByCode(x.getPcode())) == null || cl.searchByccode(x.getCcode()) == null){
            System.out.println("Can't find pcode or ccode");
            return false;
        }
        
        if (checkDuplicate(x) != null){
            System.out.println("This Order has already existed");
            return false;
        }
        int quantity = pro.getQuantity();
        int saled = pro.getSaled();
        if (saled == quantity){
            System.out.println("The product is exhausted");
            return false;
        }
        if (x.getQuantity() > quantity - saled){
            System.out.println("remaining product amount is not enought");
            return false;
        }
        System.out.println("Order is added successfully");
        return true;
    }
    
    // 3.2. Display ordering data
    void display() {
        LinkedListNode<Order> p = head;
        while (p != null) {
            System.out.println(p.info);
            p = p.next;
        }
        System.out.println();
    }
    
    void swap(LinkedListNode p, LinkedListNode q){
        Object x = p.info;
        p.info = q.info;
        q.info = x;        
    }
    
    //3.3. Sort by pcode + ccode
    void sortByPcodeCcode() {          
        LinkedListNode<Order> p;
        LinkedListNode<Order> q;
        p = head;
        while (p != null) {
            q = p.next;
            while (q != null) {
                int k = q.info.getPcode().compareTo(p.info.getPcode());
                if (k < 0)
                    swap(p,q);
                else if (k == 0){
                    if (q.info.getCcode().compareTo(p.info.getCcode()) < 0)
                        swap(p,q);
                }
                    
                q = q.next;
            }
            p = p.next;
        }
    }
}


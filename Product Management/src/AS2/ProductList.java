package AS2;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Vector;

public class ProductList {
    
    BTreeNode root;

    LinkedListNode<Product> head;
    LinkedListNode<Product> tail;

    public ProductList() {
        root = null;
    }
    
    public ProductList(BTreeNode root) {
        this.root = root;
    }

    void clear() {
        root = null;
    }

    boolean isEmpty() {
        return (root == null);
    }
    
    int compare(Product p1,Product p2){
        return p1.getPcode().compareToIgnoreCase(p2.getPcode());
    }

    //1.1.Load data from file
    void loadFile(String fname) throws IOException { // 1.1 main function
        File file = new File(fname);
            if (file.createNewFile()) {
                System.out.println("New Text File is created!");
            }
        try (RandomAccessFile f = new RandomAccessFile(fname, "r")
        ) {
            String s, pcode, pro_name;
            String[] a;
            int quantity, saled;
            double price;
            Product x;
            while (true) {
                s = f.readLine();
                if (s == null || s.trim().equals("")) {
                    break;
                }
                a = s.split("[|]");
                pcode = a[0].trim();
                pro_name = a[1].trim();
                quantity = Integer.parseInt(a[2].trim());
                saled = Integer.parseInt(a[3].trim());
                price = Double.parseDouble(a[4].trim());
                x = new Product(pcode, pro_name, quantity, saled, price);
                try {
                    insert(x);
                } catch (Exception ex) {
                    System.err.println(ex.getMessage());
                }
            }
            f.close();
        }
    }
    
    //1.2.Input & insert data
    void insert(Product p) throws Exception { 
        BTreeNode q = new BTreeNode(p);
        if (root==null) {
            root = q;
            return;
        }
        BTreeNode f = null, g = root;
        while (g != null) {
            if (compare(p,g.info)==0) {
                throw new Exception("The key " + p.getPcode() + " already exists, no insertion");
            }
            f = g;
            if (compare(p,g.info) < 0) {
                g = g.left;
            } else {
                g = g.right;
            }
        }
        if ( f == null) return;
        if (compare(p,f.info) < 0) {
            f.left = q;
        } else {
            f.right = q;
        }
    }
  
    //1.3.In-order traverse
    void visit(BTreeNode p) {
        if (p != null) {
            System.out.println(p.info + " ");
        }
    }

    void inOrder(BTreeNode p) {
        if (p == null) {
            return;
        }
        inOrder(p.left);
        visit(p);
        inOrder(p.right);
    }
    
    //1.4 Breadth-first traverse
    void breadth(BTreeNode p) { 
        if (p == null) {
            return;
        }
        MyQueue q = new MyQueue();
        q.enqueue(p);
        BTreeNode r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            visit(r);
            if (r.left != null) {
                q.enqueue(r.left);
            }
            if (r.right != null) {
                q.enqueue(r.right);
            }
        }
    }

    //1.5 In-order traverse to file
    void fInOrder(BTreeNode p, PrintWriter f){
        if (p == null) {
            return;
        }
        fInOrder(p.left, f);
        try {
            f.println(p.info.toString());            
        } catch (Exception e) {
        }
        fInOrder(p.right, f);
    }

    void inOrderToFile(String fname) throws IOException { 
        PrintWriter f = new PrintWriter(fname);
        fInOrder(root, f);
        f.flush();
    }

    //1.6.Search by pcode
    Product SearchByCode(String xCode) { // 1.6 main function
        BTreeNode p = root;
        while (p != null) {
            if (xCode.compareToIgnoreCase(p.info.getPcode()) == 0) {
                p.info.setPrice(99);
                return p.info;
            }
            
            if (xCode.compareToIgnoreCase(p.info.getPcode()) < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return null;
    }
    
    //1.7.Delete by pcode by copying
    void deleteFunc(BTreeNode p, BTreeNode f) {
        //p do not have child
        if (p.left == null && p.right == null) {
            if (f == null) {
                root = null;
            } else {
                if (f.left == p) {
                    f.left = null;
                } else {
                    f.right = null;
                }
            }
            return;
        }
        //p have one-left child
        if (p.left != null && p.right == null) {
            if (f == null) {
                root = p.left;
            } else {
                if (f.left == p) {
                    f.left = p.left;
                } else {
                    f.right = p.left;
                }
            }
            return;
        }
        //p have one-right child
        if (p.left == null && p.right != null) {
            if (f == null) {
                root = p.right;
            } else {
                if (f.left == p) {
                    f.left = p.right;
                } else {
                    f.right = p.right;
                }
            }
            return;
        }
        BTreeNode p1 = p.left;
        BTreeNode f1 = p;
        while (p1.right != null) {
            f1 = p1;
            p1 = p1.right;
        }
        p.info = p1.info;
        if (f1.right == p1) {
            f1.right = p1.left;
        } else {
            f1.left = p1.left;
        }
    }
    
    void deleteByCopy(String xName) throws Exception { 
        BTreeNode f, p;
        f = null;
        p = root;
        while (p != null) {
            if (p.info.getPcode().equals(xName)) {
                break;
            }
            f = p;
            if (xName.compareTo(p.info.getPcode()) < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (p == null) {
            throw new Exception(); // not found
        }
        deleteFunc(p, f);
    }

    void inOrder(ArrayList<Product> t, BTreeNode p) {
        if (p == null) {
            return;
        }
        inOrder(t, p.left);
        t.add(p.info);
        try {
            deleteByCopy(p.info.getPcode());
        } catch (Exception ex) {
        }
        inOrder(t,p.right);
    }
    
    //1.8.Simply balancing
     void storeBSTNodes(BTreeNode root, Vector<BTreeNode> nodes)  
    { 
        // Base case 
        if (root == null) 
            return; 
  
        // Store nodes in Inorder (which is sorted order for BST) 
        storeBSTNodes(root.left, nodes); 
        nodes.add(root); 
        storeBSTNodes(root.right, nodes); 
    } 
  
    // Recursive function to construct binary tree
    BTreeNode buildTreeUtil(Vector<BTreeNode> nodes, int start, 
            int end)  
    { 
        // base case 
        if (start > end) 
            return null; 
  
        // Get the middle element and make it root 
        int mid = (start + end) / 2; 
        BTreeNode node = nodes.get(mid); 
  
        // Using index in Inorder traversal, construct left and right subtress 
        node.left = buildTreeUtil(nodes, start, mid - 1); 
        node.right = buildTreeUtil(nodes, mid + 1, end); 
  
        return node; 
    } 
  
    // converts an unbalanced BST to a balanced BST 
    BTreeNode buildTree(BTreeNode root)  
    { 
        // Store nodes of given BST in sorted order 
        Vector<BTreeNode> nodes = new Vector<BTreeNode>(); 
        storeBSTNodes(root, nodes); 
  
        // Constucts BST from nodes[] 
        int n = nodes.size(); 
        return buildTreeUtil(nodes, 0, n - 1); 
    } 
    
    void balance(){
        this.root = buildTree(this.root);
    }
    
    //1.9.Count number of products
    int countProduct(BTreeNode p){ // 1.9 main function
        if(p==null) return 0;
        int l,r;
        l=countProduct(p.left);
        r=countProduct(p.right);
        return l+r+1 ;
    }
    
}

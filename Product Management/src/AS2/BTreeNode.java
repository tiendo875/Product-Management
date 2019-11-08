package AS2;


public class BTreeNode {
    Product info;
    BTreeNode left, right;

    public BTreeNode(Product i, BTreeNode left, BTreeNode right) {
        this.info = i;
        this.left = left;
        this.right = right;
    }

    public BTreeNode(Product b) {
        this(b, null, null);
    }
}

import java.util.*;

public class BST<Key extends Comparable<Key>, Value> implements ST<Key, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private double depth;

        Node(Key key, Value val) {
            this.key = key;
            this.val = val;
            this.depth = 0;
        }
    }

    public boolean contains(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0)
                return true;
            else if (cmp < 0)
                x = x.left;
            else if (cmp > 0)
                x = x.right;
        }
        return false;
    }

    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0)
                return x.val;
            else if (cmp < 0)
                x = x.left;
            else if (cmp > 0)
                x = x.right;
        }
        return null;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null)
            return new Node(key, val);
        int cmp = key.compareTo(x.key);
        if (cmp == 0)
            x.val = val;
        else if (cmp < 0)
            x.left = put(x.left, key, val);
        else if (cmp > 0)
            x.right = put(x.right, key, val);
        return x;
    }

    public int numOfLeaves() {
        return numOfLeaves(root);
    }

    private int numOfLeaves(Node x) {
        if (x == null)
            return 0;
        else if (x.left != null || x.right != null) {
            return numOfLeaves(x.left) + numOfLeaves(x.right);
        } else {
            return 1;
        }
    }

    double numOfNodes = 0;
    double currentDepth = -1;
    double depthSum = 0;

    public Iterator<Key> iterator() {
        return new BSTIterator();
    }

    private class BSTIterator implements Iterator<Key> {
        private Stack<Node> stack = new Stack<Node>();

        BSTIterator() {
            pushLeft(root);
        }

        private void pushLeft(Node x) {
            while (x != null) {
                currentDepth++;
                x.depth = currentDepth;
                stack.push(x);
                x = x.left;
            }
        }

        public boolean hasNext() {
            if(!stack.isEmpty()){
                numOfNodes++;
                return true;
            } else {
                return false;
            }
        }

        public Key next() {
            Node x = stack.pop();
            currentDepth = x.depth;
            depthSum += x.depth;
            pushLeft(x.right);
            return x.key;
        }
    }

    public static void main(String[] args) {
        BST bst = new BST();
        Comparable[] comparables = { 0, 1, 2, 3 };
        bst.put(comparables[0], "google");
        bst.put(comparables[1], "facebook");
        bst.put(comparables[2], "youtube");
    }

}

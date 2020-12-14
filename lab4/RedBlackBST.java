import java. util.*;

public class RedBlackBST<Key extends Comparable<Key>, Value> implements Iterable<Key>, ST<Key, Value>
{
     private static final boolean RED = true;
     private static final boolean BLACK = false;
     private Node root;
     
     private class Node
     {
         Key key;
         Value val;
         Node left, right;
         boolean color;
         Node(Key key, Value val, boolean color)
         {
             this.key = key;
             this.val = val;
             this.color = color;
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
     
     public void put(Key key, Value val)
     {
         root = insert(root, key, val);
         root.color = BLACK;
     }
     
     private boolean isRed(Node x)
     {
         if (x == null) return false;
         return (x.color == RED);
     }
     
     private Node rotL(Node h) 
     {
        Node v = h.right;
        h.right = v.left;
        v.left = h;
        return v;
     }
     
     private Node rotR(Node h) 
     {
        Node u = h.left;
        h.left = u.right;
        u.right = h;
        return u;
     }
     
     private Node splitFourNode(Node h)
     {
         Node x = rotR(h);
         x.left.color = BLACK;
         return x;
     }
     
     private Node leanLeft(Node h)
     {
         Node x = rotL(h);
         x.color = x.left.color;
         x.left.color = RED;
         return x;
     }
     
     private Node insert(Node h, Key key, Value val)
     {
         if (h == null)
            return new Node(key, val, RED);
            
         if (isRed(h.left))
            if (isRed(h.left.left))
                h = splitFourNode(h);
                
         int cmp = key.compareTo(h.key);
         
         if (cmp == 0) h.val = val;
         
         else if (cmp < 0)
            h.left = insert(h.left, key, val);
         else
            h.right = insert(h.right, key, val);
            
         if (isRed(h.right))
            h = leanLeft(h);
         
          return h;
     }

     
     public Iterator<Key> iterator()
     { return new BSTIterator(); }
    
     private class BSTIterator implements Iterator<Key>
     {
         private Stack<Node> stack = new Stack<Node>();
         
         private void pushLeft(Node x) {
            while (x != null) {
                stack.push(x);
                x = x.left;
            }
        }
         
        BSTIterator() {
            pushLeft(root);
        }
         
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public Key next() {
            Node x = stack.pop();
            pushLeft(x.right);
            return x.key;
        }
     }
}
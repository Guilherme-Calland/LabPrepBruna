import java.util.Iterator;

public interface ST<Key extends Comparable<Key>, Value>{
    void put(Key key, Value val);
    Value get(Key key);
    boolean contains(Key key);
    Iterator<Key> iterator();
}
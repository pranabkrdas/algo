package com.home.latest.tree;

/**
 * Created by pranabdas on 11/23/15.
 */
public interface SymbolTable<Key extends Comparable<Key>, Value> {

    Value get(Key key);

    void put(Key key, Value val);

    void delete(Key key);

    boolean contains(Key key);

    boolean isEmpty();

    int size();

    Key min();

    Key max();

    /**
     * largest key smaller than or equal to key
     * @param key
     * @return
     */
    Key floor(Key key);

    /**
     * smallest key greater than or equal to key
     * @param key
     * @return
     */
    Key ceiling(Key key);

    /**
     * number of keys less than key
     * @param key
     * @return
     */
    int rank(Key key);

    /**
     * key of rank k
     * @param k
     * @return
     */
    Key select(int k);

    void deleteMin();

    void deleteMax();

    /**
     * number of keys in [lo....hi]
     * @param lo
     * @param high
     * @return
     */
    int size(Key lo, Key high);

    /**
     * keys in [lo...high] in sorted order
     * @param lo
     * @param high
     * @return
     */
    Iterable<Key> keys(Key lo, Key high);

    /**
     * all the keys in sorted order
     * @return
     */
    Iterable<Key> keys();
}

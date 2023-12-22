package com.rosed.wildernesschestloot.util;

import java.util.Map;

public interface Tracker<K, V> {

    /**
     * increments value
     *
     * @param key object being tracked
     */
    void increment(K key);

    /**
     * decrement value
     *
     * @param key object being tracked
     */
    void decrement(K key);

    /**
     * set value
     *
     * @param key object being tracked
     */
    void set(K key, V value);

    /**
     * get value of object being tracked
     *
     * @param key object being tracked
     * @return value of tracked object
     */
    V get(K key);

    /**
     * add new object and tracked attribute pairing with default value
     *
     * @param k object being tracked
     */
    void add(K k);

    /**
     * add new object and tracked attribute pairing
     *
     * @param k object being tracked
     * @param v attribute being tracked
     */
    void add(K k, V v);

    /**
     * remove pairing from tracker list
     *
     * @param key object being removed from tracker
     */
    void remove(K key);

}

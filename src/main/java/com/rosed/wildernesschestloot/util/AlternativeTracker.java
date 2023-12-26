package com.rosed.wildernesschestloot.util;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.function.BiFunction;

public class AlternativeTracker<K, V> {

    private final Map<K, V> map = Maps.newHashMap();
    private final V defaultValue;

    public AlternativeTracker(V defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * If the specified key is not already associated with a value or is
     * associated with null, associates it with the given non-null value.
     * Otherwise, replaces the associated value with the results of the given
     * remapping function, or removes if the result is {@code null}. This
     * method may be of use when combining multiple mapped values for a key.
     * For example, to either create or append a {@code String msg} to a
     * value mapping:
     *
     * <pre> {@code
     * map.merge(key, msg, String::concat)
     * }</pre>
     *
     * <p>If the remapping function returns {@code null}, the mapping is removed.
     * If the remapping function itself throws an (unchecked) exception, the
     * exception is rethrown, and the current mapping is left unchanged.
     *
     * <p>The remapping function should not modify this map during computation.
     **/
    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        return map.merge(key, value, remappingFunction);
    }

    /**
     * set value
     *
     * @param k object being tracked
     */
    public void set(K k, V value) {
        map.replace(k, value);
    }

    /**
     * get value of object being tracked
     *
     * @param k object being tracked
     * @return value of tracked object
     */
    public V get(K k) {
        return map.getOrDefault(k, defaultValue);
    }

    /**
     * add new object and tracked attribute pairing with default value
     *
     * @param k object being tracked
     */
    public void add(K k) {
        map.put(k, defaultValue);
    }

    /**
     * add new object and tracked attribute pairing
     *
     * @param k object being tracked
     * @param v attribute being tracked
     */
    public void add(K k, V v) {
        map.put(k, v);
    }

    /**
     * remove pairing from tracker list
     *
     * @param k object being removed from tracker
     */
    public void remove(K k) {
        map.remove(k);
    }

}

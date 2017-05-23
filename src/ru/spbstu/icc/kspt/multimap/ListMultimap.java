package ru.spbstu.icc.kspt.multimap;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public interface ListMultimap<K, V> {

    /* Query operations */

    int size();

    boolean isEmpty();

    boolean containsKey(K key);

    boolean containsValue(V value);

    boolean containsEntry(K key, V value);

    /* Modification operations */

    void put(K key, V value);

    boolean remove(K key, V value);

    void putAll(K key, List<V> values);

    List<V> replaceValues(K key, List<V> values);

    List<V> removeAll(K key);

    void clear();

    /* Retrieve operations */

    List<V> get(K key);

    Set<K> keySet();

    Collection<V> values();

    Collection<Entry<K, V>> entries();

    Map<K, Collection<V>> asMap();

}
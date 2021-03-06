package ru.spbstu.icc.kspt.multimap;

import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

/*
 * Реализация в виде класса на Java одного из видов multi map
 * (ассоциативный массив, в котором каждому ключу может соответствовать более одного значения)
 * ListMultimap -- значения, соответствующие одному ключу, могут повторяться и упорядочены
 */
public class ListMultimapImpl<K, V> implements ListMultimap<K, V> {

    private final Map<K, List<V>> map;

    public ListMultimapImpl() {
        this.map = new HashMap<>();
    }

    public ListMultimapImpl(Map<K, List<V>> map) {
        this.map = map;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        for (Entry<K, List<V>> entry : map.entrySet()) {
            if (entry.getValue().contains(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsEntry(K key, Object value) {
        if (!map.containsKey(key)) {
            return false;
        }
        return map.get(key).contains(value);
    }

    @Override
    public void put(K key, V value) {
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<>());
        }
        map.get(key).add(value);
    }

    @Override
    public void putAll(K key, List<V> values) {
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<>());
        }
        map.get(key).addAll(values);
    }

    @Override
    public boolean remove(K key, V value) {
        if (!map.containsKey(key)) {
            return false;
        }
        return map.get(key).remove(value);
    }

    @Override
    public List<V> replaceValues(K key, List<V> values) {
        return map.put(key, values);
    }

    @Override
    public List<V> removeAll(K key) {
        return map.remove(key);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public List<V> get(K key) {
        return map.get(key);
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        return new AbstractCollection<V>() {

            @Override
            public Iterator<V> iterator() {
                return new Iterator<V>() {

                    private Iterator<Entry<K, List<V>>> entries = map.entrySet().iterator();

                    private Iterator<V> values;

                    public boolean hasNext() {
                        return entries.hasNext() || (values != null && values.hasNext());
                    }

                    public V next() {
                        if (values == null || !values.hasNext()) {
                            values = entries.next().getValue().iterator();
                            return next();
                        }
                        return values.next();
                    }

                    public void remove() {
                        values.remove();
                    }
                };
            }

            @Override
            public int size() {
                int size = 0;
                for (K k : map.keySet()) {
                    size += map.get(k).size();
                }
                return size;
            }

            @Override
            public boolean isEmpty() {
                return ListMultimapImpl.this.isEmpty();
            }

            @Override
            public void clear() {
                ListMultimapImpl.this.clear();
            }

            @Override
            public boolean contains(Object v) {
                return ListMultimapImpl.this.containsValue(v);
            }
        };
    }

    @Override
    public Collection<Entry<K, V>> entries() {
        List<Entry<K, V>> entries = new ArrayList<>();
        for (Entry<K, List<V>> entry : map.entrySet()) {
            for (V value : entry.getValue()) {
                Entry<K, V> newEntry = new SimpleEntry<>(entry.getKey(), value);
                entries.add(newEntry);
            }
        }
        return entries;
    }

    @Override
    public Map<K, Collection<V>> asMap() {
        return new HashMap<>(map);
    }

}
package csc202.finalprjct.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@SuppressWarnings("unchecked")
public class SuperHashMap<K,V> implements SuperMap<K,V> {
    class SuperEntry implements SuperMap.Entry<K,V> {
        private K key;
        private V value;

        public SuperEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }
    }

    private int arraySize = 16;
    private int size = 0;
    private List<SuperEntry>[] entries = new List[arraySize];

    public SuperHashMap() {

    }

    /**
     * Returns the number of key-value mappings in this map.  If the
     * map contains more than <tt>Integer.MAX_VALUE</tt> elements, returns
     * <tt>Integer.MAX_VALUE</tt>.
     *
     * @return the number of key-value mappings in this map
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns <tt>true</tt> if this map contains no key-value mappings.
     *
     * @return <tt>true</tt> if this map contains no key-value mappings
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     *
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     *         {@code null} if this map is empty or contains no mapping for the key
     */
    @Override
    public V get(K key) {
        if (size == 0) {
            return null;
        }

        int index = Math.abs(key.hashCode()) % arraySize;
        if (entries[index] == null) {
            return null;
        }
        // There can be several Entries in the position of the given index
        for (int i = 0; i < entries[index].size(); i++) {
            SuperEntry currentEntry = entries[index].get(i);
            if (currentEntry.getKey().equals(key)) {
                return currentEntry.getValue();
            }
        }

        return null;
    }

    /**
     * Returns <tt>true</tt> if this map contains a mapping for the specified
     * key.  More formally, returns <tt>true</tt> if and only if
     * this map contains a mapping for a key <tt>k</tt> such that
     * <tt>(key==null ? k==null : key.equals(k))</tt>.  (There can be
     * at most one such mapping.)
     *
     * @param key key whose presence in this map is to be tested
     * @return <tt>true</tt> if this map contains a mapping for the specified
     *         key
     * @throws NullPointerException for null keys
     */
    @Override
    public boolean containsKey(K key) throws NullPointerException {
        if (key == null) {
            throw new NullPointerException("Key can not be null");
        }

        int index = Math.abs(key.hashCode()) % arraySize;

        if (entries[index] == null) {
            return false;
        }
        for (int j = 0; j < entries[index].size(); j++) {
            if (entries[index].get(j).getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns <tt>true</tt> if this map maps one or more keys to the
     * specified value.  More formally, returns <tt>true</tt> if and only if
     * this map contains at least one mapping to a value <tt>v</tt> such that
     * <tt>(value==null ? v==null : value.equals(v))</tt>.  This operation
     * will probably require time linear in the map size for most
     * implementations of the <tt>Map</tt> interface.
     *
     * @param value value whose presence in this map is to be tested
     * @return <tt>true</tt> if this map maps one or more keys to the
     *         specified value
     */
    @Override
    public boolean containsValue(V value) {
        for (int i = 0; i < arraySize; i++) {
            if (entries[i] == null) {
                continue;
            }
            for (int j = 0; j < entries[i].size(); j++) {
                if (entries[i].get(j).getValue().equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Associates the specified value with the specified key in this map
     * (optional operation).  If the map previously contained a mapping for
     * the key, the old value is replaced by the specified value.  (A map
     * <tt>m</tt> is said to contain a mapping for a key <tt>k</tt> if and only
     * if {@link #containsKey(Object) m.containsKey(k)} would return
     * <tt>true</tt>.)
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @throws NullPointerException for null keys
     * @return the previous value associated with <tt>key</tt>, or
     *         <tt>null</tt> if there was no mapping for <tt>key</tt>.
     *         (A <tt>null</tt> return can also indicate that the map
     *         previously associated <tt>null</tt> with <tt>key</tt>,
     *         if the implementation supports <tt>null</tt> values.)
     */
    @Override
    public V put(K key, V value) throws NullPointerException {
        if (key == null) {
            throw new NullPointerException("Key can not be null");
        }

        int index = Math.abs(key.hashCode()) % arraySize;
        if (!containsKey(key)) {
            if (entries[index] == null) {
                entries[index] = new ArrayList<>();
            }
            entries[index].add(new SuperEntry(key, value));
            if (size++ >= Integer.MAX_VALUE) {
                size = Integer.MAX_VALUE;
            }
            size++;

        } else {
            for (SuperEntry entry : entries[index]) {
                if (entry.getKey().equals(key)) {
                    entry.value = value;
                }
            }
        }

        if (needToIncreaseArraySize()) {
            increaseArraySize();
        }

        return value;
    }

    /**
     * Checks if there is need to increase array of mappings.
     *
     */
    private boolean needToIncreaseArraySize() {
        return size > arraySize * 0.75;

    }

    /**
     * Increases arraySize multiplying by two. And refreshes indexes for the new array of
     * mappings.
     *
     */
    private void increaseArraySize() {
        List<SuperEntry>[] tempEntries = Arrays.copyOf(entries, entries.length);
        size = 0;
        arraySize = arraySize * 2;
        entries = new List[arraySize];
        for (List<SuperEntry> tempEntriesList : tempEntries) {
            if (tempEntriesList == null) {
                continue;
            }
            for (SuperEntry tempEntry : tempEntriesList) {
                put(tempEntry.getKey(), tempEntry.getValue());
            }
        }
    }

    /**
     * Removes the mapping for a key from this map if it is present
     * (optional operation).
     *
     * <p>Returns the value to which this map previously associated the key,
     * or <tt>null</tt> if the map contained no mapping for the key.
     *
     *
     * <p>The map will not contain a mapping for the specified key once the
     * call returns.
     *
     * @param key key whose mapping is to be removed from the map
     * @return the previous value associated with <tt>key</tt>, or
     *         <tt>null</tt> if there was no mapping for <tt>key</tt> or this map is empty.
     * @throws NullPointerException for null keys
     */
    @Override
    public V remove(K key) throws NullPointerException {
        if (key == null) {
            throw new NullPointerException("Key can not be null");
        }

        if (size == 0) {
            return null;
        }

        int index = Math.abs(key.hashCode()) % arraySize;

        if (entries[index] == null) {
            return null;
        }

        // There can be several Entries in the position of the given index
        for (int i = 0; i < entries[index].size(); i++) {
            SuperEntry currentEntry = entries[index].get(i);
            if (currentEntry.getKey().equals(key)) {
                V value = currentEntry.getValue();
                if (entries[index].size() == 1) {
                    entries[index].clear();
                } else {
                    entries[index].remove(i);
                }
                size--;
                return value;
            }
        }

        return null;
    }

    /**
     * Removes all of the mappings from this map (optional operation).
     * The map will be empty after this call returns.
     *
     */
    @Override
    public void clear() {
        arraySize = 16;
        entries = new List[arraySize];
        size = 0;
    }

    /**
     * Returns a {@link Collection} view of the values contained in this map.
     *
     * @return a collection view of the values contained in this map
     */
    @Override
    public Collection<V> values() {
        List<V> values = new ArrayList<>();
        for (List<SuperEntry> entriesList : entries) {
            if (entriesList == null) {
                continue;
            }
            for (SuperEntry entry : entriesList) {
                values.add(entry.getValue());
            }
        }
        return values;
    }
}

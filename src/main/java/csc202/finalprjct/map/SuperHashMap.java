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

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

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

    @Override
    public boolean containsKey(K key) {
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

    @Override
    public V put(K key, V value) {
        int index = Math.abs(key.hashCode()) % arraySize;
        if (!containsKey(key)) {
            if (entries[index] == null) {
                entries[index] = new ArrayList<>();
            }
            entries[index].add(new SuperEntry(key, value));
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

    private boolean needToIncreaseArraySize() {
        return size > arraySize * 0.75;

    }

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

    @Override
    public V remove(K key) {
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

    @Override
    public void clear() {
        arraySize = 16;
        entries = new List[arraySize];
        size = 0;
    }

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

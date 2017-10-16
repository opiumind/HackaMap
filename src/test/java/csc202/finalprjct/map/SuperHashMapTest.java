package csc202.finalprjct.map;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Olga Lisovskaya on 10/15/17.
 */
public class SuperHashMapTest {
    @Test
    public void size() throws Exception {
        SuperMap<String, Integer> testMap = new SuperHashMap<>();
        for (int i = 0; i < 10; i++) {
            testMap.put(String.valueOf(i), i);
        }
        assertEquals("Wrong size().", 10, testMap.size());
    }

    @Test
    public void isEmpty() throws Exception {
        SuperMap<String, Integer> testMap = new SuperHashMap<>();
        for (int i = 0; i < 10; i++) {
            testMap.put(String.valueOf(i), i);
        }
        assertEquals("Wrong isEmpty().", false, testMap.isEmpty());
    }

    @Test
    public void get() throws Exception {
        SuperMap<String, String> testMap = new SuperHashMap<>();
        for (int i = 0; i < 10; i++) {
            testMap.put(String.valueOf(i), "Element-" + i);
        }
        assertEquals("Wrong get().", "Element-4", testMap.get("4"));
    }

    @Test
    public void containsKey() throws Exception {
        SuperMap<String, String> testMap = new SuperHashMap<>();
        for (int i = 0; i < 10; i++) {
            testMap.put(String.valueOf(i * 3), "Element-" + i);
        }
        assertEquals("Wrong containsKey().", false, testMap.containsKey("10"));
    }

    @Test
    public void containsValue() throws Exception {
        SuperMap<String, String> testMap = new SuperHashMap<>();
        for (int i = 0; i < 10; i++) {
            testMap.put(String.valueOf(i * 3), "Element-" + i);
        }
        assertEquals("Wrong containsValue().", true, testMap.containsValue("Element-0"));
    }

    @Test
    public void put() throws Exception {
        SuperMap<String, String> testMap = new SuperHashMap<>();
        for (int i = 0; i < 16; i++) {
            testMap.put(String.valueOf(i * 3), "Element-" + i);
        }
        assertEquals("Wrong put().", "RRR", testMap.put("rr", "RRR"));
        assertEquals("Wrong size after put().", 17, testMap.size());
        assertEquals("Wrong get() after put().", "RRR", testMap.get("rr"));
    }

    @Test
    public void remove() throws Exception {
        SuperMap<String, String> testMap = new SuperHashMap<>();
        for (int i = 0; i < 26; i++) {
            testMap.put(String.valueOf(i * 3), "Element-" + i);
        }
        assertEquals("Wrong remove().", "Element-3", testMap.remove("9"));
        assertEquals("Wrong get() after remove().", null, testMap.get("9"));
    }

    @Test
    public void clear() throws Exception {
        SuperMap<String, String> testMap = new SuperHashMap<>();
        for (int i = 0; i < 33; i++) {
            testMap.put(String.valueOf(i * 3), "Element-" + i);
        }
        testMap.clear();
        assertEquals("Wrong clear().", 0, testMap.size());
    }

    @Test
    public void values() throws Exception {
        SuperMap<String, String> testMap = new SuperHashMap<>();
        List<String> testValues = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            testMap.put(String.valueOf(i * 3), "Element-" + i);
            testValues.add("Element-" + i);
        }

        testValues.sort(null);
        List<String> valuesFromMap = new ArrayList<>(testMap.values());
        valuesFromMap.sort(null);

        assertEquals("Wrong values().", testValues, valuesFromMap);
    }
}
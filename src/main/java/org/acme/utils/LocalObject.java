package org.acme.utils;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class LocalObject<K extends LocalObjectEnum, V> {
    Map<K, V> map = new LinkedHashMap<>();

    public void put(K key, V value) {
        map.put(key, value);
    }

    public void putAll(LocalObject<K, V> localObject) {
        map.putAll(localObject.getMap());
    }

    public V get(K key) {
        return map.get(key);
    }

    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    public V remove(K key) {
        return map.remove(key);
    }

    public int size() {
        return map.size();
    }

    public void clearAll() {
        map.clear();
    }

    public Map<K, V> getMap() {
        return map;
    }

    public void setMap(Map<K, V> map) {
        this.map = map;
    }

    public Set<K> keySet() {
        return map.keySet();
    }

    public Set<Map.Entry<K,V>> entrySet() {
        return map.entrySet();
    }

    public Collection<V> valSet() {
        return map.values();
    }

    public String getString(K key) {
        return getStringValue(map.get(key));
    }

    public String getStringNotNull(K key, V value) {
        V obj = map.get(key);
        if (obj == null) {
            return null;
        } else {
            return getStringValue(obj);
        }
    }

    private String getStringValue(Object obj) {
        if (obj == null) {
            return null;
        } else if (obj instanceof String) {
            return (String) obj;
        } else if (obj instanceof Integer integer) {
            return String.valueOf(integer.intValue());
        } else if (obj instanceof Long long1) {
            return String.valueOf(long1.longValue());
        } else if (obj instanceof Double double1) {
            return String.valueOf(double1.doubleValue());
        } else {
            return null;
        }
    }

    public V getObject(K paramObject) {
        return map.get(paramObject);
    }
}

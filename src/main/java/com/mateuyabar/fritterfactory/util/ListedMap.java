package com.mateuyabar.fritterfactory.util;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * 
 * Map which its value is a List. This Map has added functionallity add, which
 * enables to add a value to the set of the key.
 * 
 */
public class ListedMap<K, V> extends Hashtable<K, List<V>> {
	private static final long serialVersionUID = 5210326981038218066L;

	public void add(K key, V value) {
		if (get(key) == null) {
			super.put(key, createEntry());
		}
		get(key).add(value);
	}

	public void addAll(K key, List<V> value) {
		for(int i=0; i<value.size();++i){
			add(key, value.get(i));
		}
	}

	private List<V> createEntry() {
		return new ArrayList<V>();
	}

}
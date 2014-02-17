package fi.seco.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ALRUCache<K, V> implements Map<K, V> {

	private static final Logger log = LoggerFactory.getLogger(ALRUCache.class);

	protected final Map<K, Entry<K, V>> hashmap;

	protected transient final Entry<K, V> header = new Entry<K, V>(null, null);

	protected abstract boolean isTooLarge();

	protected static final class Entry<K2, V2> {
		public Entry(K2 key, V2 content) {
			this.key = key;
			this.content = content;
		}

		public final K2 key;
		private final V2 content;

		public final V2 getContent() {
			return content;
		}

		public Entry<K2, V2> before = this, after = this;

		public final void remove() {
			before.after = after;
			after.before = before;
		}

		public final void addBefore(Entry<K2, V2> existingEntry) {
			after = existingEntry;
			before = existingEntry.before;
			before.after = this;
			after.before = this;
		}

		@Override
		public boolean equals(Object other) {
			@SuppressWarnings("unchecked") V2 o = (V2) other;
			if (o == null) return content == null;
			return o.equals(content);
		}

	}

	public ALRUCache() {
		hashmap = new HashMap<K, Entry<K, V>>();
	}

	public ALRUCache(int initialCapacity) {
		hashmap = new HashMap<K, Entry<K, V>>(initialCapacity);
	}

	public ALRUCache(int initialCapacity, float loadFactor) {
		hashmap = new HashMap<K, Entry<K, V>>(initialCapacity, loadFactor);
	}

	@Override
	public synchronized void clear() {
		hashmap.clear();
		header.before = header.after = header;
		cleared();
	}

	protected abstract void cleared();

	@Override
	public synchronized V remove(Object key) {
		Entry<K, V> toRemove = hashmap.get(key);
		if (toRemove == null) return null;
		toRemove.remove();
		hashmap.remove(toRemove.key);
		removed(toRemove.content);
		return toRemove.getContent();
	}

	protected abstract void removed(V content);

	protected abstract void added(V content);

	@Override
	public synchronized V put(K key, V object) {
		added(object);
		while (isTooLarge()) {
			Entry<K, V> toRemove = header.after;
			toRemove.remove();
			hashmap.remove(toRemove.key);
			removed(toRemove.content);
		}
		Entry<K, V> neu = new Entry<K, V>(key, object);
		neu.addBefore(header);
		Entry<K, V> e = hashmap.put(key, neu);
		if (e != null) {
			e.remove();
			removed(e.content);
			return e.getContent();
		}
		return null;
	}

	@Override
	public synchronized V get(Object key) {
		Entry<K, V> g = hashmap.get(key);
		if (g != null) {
			g.remove();
			g.addBefore(header);
			return g.getContent();
		}
		return null;
	}

	@Override
	public boolean containsKey(Object arg0) {
		return hashmap.containsKey(arg0);
	}

	@Override
	public int size() {
		return hashmap.size();
	}

	@Override
	public boolean isEmpty() {
		return hashmap.isEmpty();
	}

	@Override
	public boolean containsValue(Object value) {
		return hashmap.containsValue(value);
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		for (Map.Entry<? extends K, ? extends V> e : m.entrySet())
			put(e.getKey(), e.getValue());
	}

	@Override
	public Set<K> keySet() {
		return hashmap.keySet();
	}

	@Override
	public Collection<V> values() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		throw new UnsupportedOperationException();
	}

}

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/**
 * 
 * @author zhaojialiang
 * Classroom: csc 335
 * file:AbstractMap.java
 * @param <K> An eraser element of key will change
 *  its format every time the class is reused.
 * @param <V> An eraser element of value will change
 *  its format every time the class is reused.
 *  Content:This is a map with generic functions, 
 *  and its republic is similar to hashmap. He has 
 *  his own unique set element, and set can print 
 *  out unique elements. Currently my map does not support remove.
 */
public class ArrayMap<K, V> extends AbstractMap<K,V> {
  private ArrayMapEntrySet set;
   
	
	private int endIndex=0;
	
   private final  static int DEFAULT=10;
   private K[] keys;
   private V[] values;
   private int size;
   private int times=2;
   
   /**
    * ArrayMap construction function create two value
    * for set and normal map
    */
   public ArrayMap() {
	   this.set = new ArrayMapEntrySet ();
	   this.keys=(K[]) new Object[DEFAULT];
	   this.values=(V[]) new Object[DEFAULT];
	   
   }
   /**
    * Returns a Set of key, value pairs contained in an Entry object.
    *  The AbstractMap class provides a concrete SimpleEntry 
    *  class that we can use to hold them.
    * @return return a set collection for 
    * check many value there are.
    */
	@Override
	public Set<Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		
		return this.set;
	}
	/**
	 * This method adds key and value to your map. 
	 * If key already exists, the new value replaces 
	 * the old one, and the old one is returned.
	 * @param key put a key into map
	 * @param value put a value into map
	 * @return the value just put in
	 */
	@Override
	public V put(K key, V value) {
		int i=0;
		while(i<endIndex) {
			if(keys[i].equals(key)) {
				values[i]=value;
				return value;
			}
			i++;
			
		}
		if(keys.length-1<endIndex) {
			int newLong= DEFAULT *times;
			times++;
            keys= Arrays.copyOf(keys,newLong);
            values= Arrays.copyOf(values,newLong);
		}
		this.keys[endIndex]=key;
		this.values[endIndex]=value;
		this.endIndex++;
		this.size++;
		
		
		return value;
	}
	/**
	 * the size of map
	 * @return size of this map
	 */
	@Override
	public int size() {
		return size;
	}


private class ArrayMapEntrySet extends AbstractSet<Entry<K,V>>{
	
	private  Iterator<Entry<K, V>> itr;

	
	/**
	 * This returns an iterator that walks over 
	 * the Set of Entries in the Map.
	 * @return itr is a class of Iterator
	 * that can help us to view set more easier
	 */
	@Override
	public Iterator<Entry<K, V>> iterator() {
		// TODO Auto-generated method stub
		itr=new ArrayMapEntrySetIterator();
		return itr;
	}
	/**
	 * check a new entry is it in map
	 * @param o input a value function will help you to find that is exist
	 * @return ture or false is it exist
	 */
	@Override
	public boolean contains(Object o) {
	
		
		SimpleEntry<K,V> he=(SimpleEntry<K,V>) o ;
		Iterator<Entry<K, V>> b = new ArrayMapEntrySetIterator();
		while(b.hasNext()) {
			
			SimpleEntry<K,V>  a= (SimpleEntry<K,V>) b.next();
			if(a.getKey().equals(he.getKey()) && a.getValue().equals(he.getValue())) {
				return true;
			}
		
		}
        return false; 
		
	}
	/**
	 * size size of the set
	 * @return how long of the set
	 */
	@Override
	public int size() {
		
		return size;
	}
	
	private class ArrayMapEntrySetIterator<T> implements Iterator<T>{
		private SimpleEntry<K,V> node;
		private int point=0;
	 
		/**
		 * check the right is it have next
		 * @return true of false the value still have next
		 */
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			if(point>=size) {
				return false;
			}
			return true;
		}
		/**
		 * make node to next one
		 * @return change current value to next value
		 */
		@Override
		public T next() {
			
			node=new SimpleEntry<K,V> (keys[point],values[point]);
			
			point+=1;
			
				return (T) node;

		}
		/**
		 * I didn't write this function, 
		 * because it is optional in the homework.
		 */
		@Override
		public void remove() {
			endIndex--;
			keys[endIndex]=null;
			values[endIndex]=null;
			
			size--;
		}
		
	}
}

}

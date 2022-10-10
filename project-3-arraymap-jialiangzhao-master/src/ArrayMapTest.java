import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class ArrayMapTest {
	@Test
	void ArrayMapTest () {
		
		ArrayMap<Character, Character> a= new ArrayMap<Character, Character>();
		a.put('a','b');
		a.put('1','b');
		a.put('2','b');
		a.put('3','b');
		a.put('4','b');
		a.put('5','b');
		a.put('6','b');
		a.put('7','b');
		a.put('8','b');
		a.put('9','b');
		a.put('0','b');
		a.put('q','b');
		a.put('w','b');
		a.put('e','b');
		a.put('r','b');
		a.put('t','b');
		a.put('y','b');
		a.put('u','b');
		a.put('i','b');
		a.put('o','b');
		a.put('p','b');
		a.put('a','c');
		a.put('i','b');
		System.out.println( a.get('a'));
		a.size();
		
		SimpleEntry<Character,Character> test= new SimpleEntry<Character,Character>('i','b');
		SimpleEntry<Character,Character> test2= new SimpleEntry<Character,Character>('m','c');
		Set<Entry<Character, Character>> c = a.entrySet();
		c.size();
		
		Iterator<Entry<Character, Character>> b = a.entrySet().iterator();
		while(b.hasNext()) {
			
			System.out.println( b.next());
		}
		System.out.println(c.contains(test));
		System.out.println(c.contains(test2));
		b.remove();
		Iterator<Entry<Character, Character>> l = a.entrySet().iterator();
while(l.hasNext()) {
			
			System.out.println( l.next());
		}
}
}

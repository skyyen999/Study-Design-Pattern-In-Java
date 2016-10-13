package c14.iterator;

import java.util.Iterator;

import org.junit.Test;

import c14.iterator.simpleList.SimpleList;

/**
 * simpleList-測試Iterator
 */
public class SimpleListTest {
	@Test
	public void test(){
		SimpleList list = new SimpleList();

		list.add("樂高車");
		list.add("超跑");
		list.add("露營車");
		list.add("連結車");
		list.add("九門轎車");
		list.add("F1賽車");
		
		// 取出iterator
		@SuppressWarnings("rawtypes")
		Iterator it = list.getIterator();
		// 使用 hasNext與next取出list裡面的元素
		while(it.hasNext()){
			System.out.println(it.next());
		}	

		it.next(); 		// 這裡會拋出 IndexOutOfBoundsException
	}

}

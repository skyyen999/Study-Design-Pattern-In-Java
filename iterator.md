# 走訪器模式 Iterator Pattern

####目的：提供方法走訪集合內的物件，走訪過程不需知道集合內部的結構
現在的JAVA或是程式語言都有內建Foreach來走訪集合內的物件，foreach使用上更方便也更直覺，不過foreach底層其實也是用Iterator
來實現的。以下我們做一個簡單的list(SimpleList)，這個list內建一個實作Iterator介面的SimpleIterator，當然這個SimpleList必須要有
回傳SimpleIterator的方法，這樣其他程式才能利用SimpleIterator來走訪list的內容物。  

類別圖：  
![Iterator class diagram](image/iterator.gif)  

程式碼： 
```
// java內建的Iteator interface
public interface Iterator<E> {
	// 需要實作的方法有兩個
    boolean hasNext();
    E next();
	//...下略	
}


/**
 * 自己做一個簡單的list
 */
@SuppressWarnings("rawtypes")
public class SimpleList {
	private int index = 0;
	private int size = 0;				
	private String[] carList = new String[1000]; // 可以裝1000個，在範例中已經太夠用了

	// simpleList 要有增加元素的方法
	public void add(String car){
		carList[size] = car;
		size++;
	}
	
	/**
	 * 取得Iterator
	 * @return 
	 */
	public SimpleIterator getIterator(){
		return new SimpleIterator();
	}
	
	// 自己實作的Iterator類別
	private class SimpleIterator implements Iterator{
		@Override
		// 實作hasNext
		public boolean hasNext() {
			if(index >= size){
				return false;
			}
			return true;
		}

		
		@Override
		// 實作next
		public String next() {
			if(hasNext()){
				return carList[index++];		
			}
			throw new IndexOutOfBoundsException();
		}

	}
}

/**
 * simpleList-測試Iterator
 */
public class SimpleListTest {
	SimpleList list = new SimpleList();
	@Test
	public void test(){
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
```  

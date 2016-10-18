# 蠅量級(享元)模式 Flyweight Pattern

####目的：大量物件共享一些共同性質，降低系統的負荷  
一個蠅量級類別包刮了內部性質，也就是所有物件都共用的性質，另外也有外部性質，這是隨著需求可以變換的性質。  

###樹，都是一樣的
現在我們有個假日花園系統，每個人都可以來認養一棵樹，不過為了省錢，所以每一個樹種我們只栽種一棵，當擁有者來的
時候，我們只是將掛牌上的擁有者姓名換掉，實際上提供的是同一顆樹。  
  
你說如果同時有兩個擁有者來看同一顆樹怎麼辦!!?不好意思，我們這個假日花園是採預約制的，每一個樹種一次只開放一個擁有者參觀。
  
###類別圖
![Flyweight Class Diagram](image/flyweight.gif)  
  
  
###程式碼  
```
/**
 * 樹木(Flyweight)
 */
public class Tree {
	private String type;	// 樹種(內部性質，可以共享的資訊)
	private String owner;	// 樹的擁有者(外部性質，不能共享的資訊)

	public Tree(String type){
		this.type = type;
		System.out.println("取得一顆新的" + type);
	}
	
	
	public void setOwner (String owner){
		this.owner = owner;
	}
	
	/**
	 * 
	 */
	public void display(){
		System.out.println(type  + " , 擁有者: " + owner);
	}
}

/**
 * 樹種管理員(Flyweight factory)
 */
public class TreeManager {
	private static Map<String, Tree> treePool = new HashMap<>();
	
	public static Tree getTree(String type){
		// 如果目前還沒有這種種類的樹，就新增一棵
		if(!treePool.containsKey(type)){
			treePool.put(type, new Tree(type));
		}
		// 已經有這樣的樹，拿pool裡面的出來
		return treePool.get(type);
	}
}
```  
測試碼
```  
/**
 * 蠅量級模式 - 測試
 */
public class TreeTest {
	@Test
	public void test(){
		System.out.println("============蠅量級模式測試============");

		Tree rose = TreeManager.getTree("玫瑰");
		rose.setOwner("rose");
		rose.display();
		Tree jRose = TreeManager.getTree("玫瑰");
		jRose.setOwner("jacky");
		System.out.println("------jacky要一棵玫瑰的時候，其實我們沒有創一棵的給他，而是拿pool裡面那棵------");
		jRose.display();
		
		System.out.println();
		Tree hinoki = TreeManager.getTree("Hinoki");
		hinoki.setOwner("no one");
		hinoki.display();
	}
}

```  
測試結果：
```
============蠅量級模式測試============
取得一顆新的玫瑰
玫瑰 , 擁有者: rose
------jacky要一棵玫瑰的時候，其實我們沒有創一棵的給他，而是拿pool裡面那棵------
玫瑰 , 擁有者: jacky

取得一顆新的Hinoki
Hinoki , 擁有者: no one
``` 
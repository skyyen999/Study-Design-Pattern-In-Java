# 拜訪者模式 Visitor Pattern

####目的：使用不同的拜訪者使集合(collection)中的元素行為與元素類別切離
在Java之中，Collection是非常好用的東西，可以透過泛型裝進同一個父類別的物件，但缺點就是物件的行為也就被泛型綁死了，
為了使類別物件的在集合中還能保有自己的特定，使用拜訪者將物件的行為封裝。

###中華料理大賽
中華料理大賽有分別來自特級廚師、黑暗料理界、特極麵點師傅三個陣營的廚師(Interface)參加比賽，參賽的廚師都會加入參賽者名單集合(Collection)，假如第一輪比賽的題目是燒賣，每陣營廚師做出來的燒賣都長的不太一樣，這很簡單，
我們只要讓每個廚師分別實作做燒賣這個方法，集合內的廚師只要一個調用做燒賣就可以；接下來第二道題目是豆腐，我們一樣為廚師每個增加做豆腐方法，這時候我們就需要修改廚師集合的內容，廚師才會一個一個做出美味的豆腐，假如比賽的題目不斷增加，我們就必須不斷修改廚師集合，這部分的程式碼如下，假如他們今天要比10道題目，就會有10個if else判斷，也要問廚師介面增加這些題目，當然每個廚師實作類別也要修改。  
```
/**
 * 參加比賽的廚師(被操作元素集合)
 */
public class ChefGroup {
	private List<Chef> list = new ArrayList<>();
	
	public void join(Chef chef){
		list.add(chef);
	}
	
	public void leave(Chef chef){
		list.remove(chef);
	}
	
	/**
	 * 指定比賽題目
	 */
	public void topic(Topic topic){
		String topicName = topic.getClass().getSimpleName();
		
		if(topicName.equals("Topic_saoMai")){
			//比賽題目為燒賣
			for(Chef chef : list){
				chef.cookSaoMai();
			}				
		} else if(topicName.equals("tofu")){
			// 比賽題目為豆腐
			for(Chef chef : list){
				chef.cookTofu();
			}
		} //要增加題目，首先要增加else if判斷
	}
}

/**
 * 廚師介面-被操作的元素
 */
public abstract class Chef {
	private String name;
	public Chef(String name){
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	abstract void cookTofu(); 	//廚師要會做豆腐
	abstract void cookSaoMai(); //廚師要會做燒賣
	//...要增加題目，要修改廚師介面
}
// 特級廚師
public class SuperChef extends Chef {

	public SuperChef(String name) {
		super(name);
	}

	@Override
	void cookTofu() {
		System.out.println(this.getName() + " : 宇宙大燒賣");		
	}

	@Override
	void cookSaoMai() {
		System.out.println(this.getName() + " : 熊貓豆腐");		
	}
	
	//...要增加題目，要修改廚師實作類別
}

//黑暗料理界廚師
public class DarkChef extends Chef {
	public DarkChef(String name) {
		super(name);
	}

	@Override
	void cookTofu() {
		System.out.println(this.getName() + " : 魔幻鴉片燒賣");
	}

	@Override
	void cookSaoMai() {
		System.out.println(this.getName() + " : 豆腐三重奏");		
	}
	
	//...要增加題目，要修改廚師實作類別
}

//特級麵點師傅
public class SuperNoodleChef extends Chef {
	public SuperNoodleChef(String name) {
		super(name);
	}

	@Override
	void cookTofu() {
		System.out.println(this.getName() + " : 鐵桿臭豆腐");								
	}

	@Override
	void cookSaoMai() {
		System.out.println(this.getName() + " : 鐵桿50人份燒賣");					
	}
	
	//...要增加題目，要修改廚師實作類別
}

// 指定的比賽菜餚
public interface Topic {

}
// 燒賣
public class Topic_saoMai implements Topic {

}
// 豆腐
public class Topic_tofu implements Topic {

}
```


為了避免上面這種情況，這邊我們將比賽題目抽出成為拜訪者(Visitor)，做燒賣這個動作則是實作拜訪者的類別，我們將每陣營廚師做燒賣的方法交給做燒賣拜訪者來實現，接下來比賽的題目不斷的增加，我們只要一直增加實體拜訪者(ConcreteVisotor)就好，不需要修改廚師集合的內容。例如說今天第二輪題目是做豆腐料理，那我們只要增加一個做豆腐拜訪者即可。  



###類別圖
![Visitor Class Diagram](image/visitor.gif)  

###程式碼
```
/**
 * 廚師介面-被操作的元素
 */
public abstract class Chef {
	private String name;
	public Chef(String name){
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	// visitor代表裁判指定的料理
	public abstract void accept(Visitor visitor);

}
/**
 * 特級廚師-被操作的元素
 */
public class SuperChef extends Chef {

	public SuperChef(String name) {
		super(name);
	}

	// 如何實現做料理的工作已經移交給visitor
	@Override
	public void accept(Visitor visitor) {
		visitor.cook(this);
	}

}

/**
 * 黑暗料理界廚師-被操作的物件
 */
public class DarkChef extends Chef {
	
	public DarkChef(String name) {
		super(name);
	}

	// 如何實現做料理的工作已經移交給visitor
	@Override
	public void accept(Visitor visitor) {
		visitor.cook(this);
	}

}

/**
 * 特級麵點師傅-被操作的物件
 */
public class SuperNoodleChef extends Chef {

	public SuperNoodleChef(String name) {
		super(name);
	}

	// 如何實現做料理的工作已經移交給visitor
	@Override
	public void accept(Visitor visitor) {
		visitor.cook(this);
	}

}


/**
 * 參加比賽的廚師(被操作元素集合)
 */
public class ChefGroup {
	private List<Chef> list = new ArrayList<>();
	
	public void join(Chef chef){
		list.add(chef);
	}
	
	public void leave(Chef chef){
		list.remove(chef);
	}
	
	/**
	 * 指定比賽題目
	 */
	public void topic(Visitor visitor){
		for(Chef chef : list){
			chef.accept(visitor);
		}
	}
}

/**
 * 指定的菜餚-拜訪者
 */
public interface Visitor {
	// 利用overload來實現每種不同廚師煮出不同的指定菜餚
	void cook(DarkChef superChef);
	void cook(SuperChef superChef);
	void cook(SuperNoodleChef superNoodleChef);
}

/**
 * 指定做豆腐(Concrete Visitor)
 */
public class Visitor_tofu implements Visitor {

	@Override
	public void cook(DarkChef chef) {
		System.out.println(chef.getName() + " : 豆腐三重奏");
	}

	@Override
	public void cook(SuperChef chef) {
		System.out.println(chef.getName() + " : 熊貓豆腐");
	}

	@Override
	public void cook(SuperNoodleChef chef) {
		System.out.println(chef.getName() + " : 鐵桿臭豆腐");
	}

}

/**
 * 指定做燒賣(Concrete Visitor)
 */
public class Visitor_saoMai implements Visitor {

	@Override
	public void cook(DarkChef chef) {
		System.out.println(chef.getName() + " : 魔幻鴉片燒賣");
	}

	@Override
	public void cook(SuperChef chef) {
		System.out.println(chef.getName() + " : 宇宙大燒賣");
	}

	@Override
	public void cook(SuperNoodleChef chef) {
		System.out.println(chef.getName() + " : 鐵桿50人份燒賣");
	}

}


```  

測試碼
```
/**
 * 拜訪者模式-測試
 */
public class ChefTest {
	@Test
	public void test(){
		// 準備參賽的廚師們
		ChefGroup chefGropu = new ChefGroup();
		chefGropu.join(new SuperChef("小當家"));
		chefGropu.join(new DarkChef("紹安"));
		chefGropu.join(new SuperNoodleChef("解師傅"));
		
		System.out.println("------------第一回合:燒賣--------------");
		Visitor round1 = new Visitor_saoMai();
		chefGropu.topic(round1);
		
		System.out.println("------------第二回合:豆腐--------------");
		Visitor round2 = new Visitor_tofu();
		chefGropu.topic(round2);
		
		// 假如有第三回合，我們只需要增加Visitor的實做類別，不會影響到其他程式
		// 假如要新增參賽者，那就...很麻煩了
	}
}
```
測試結果
```
------------第一回合:做燒賣--------------
小當家 : 宇宙大燒賣
紹安 : 魔幻鴉片燒賣
解師傅 : 鐵桿50人份燒賣
------------第二回合:做燒賣--------------
小當家 : 熊貓豆腐
紹安 : 豆腐三重奏
解師傅 : 鐵桿臭豆腐
```
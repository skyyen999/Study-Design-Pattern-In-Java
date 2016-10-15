# 拜訪者模式 Visitor Pattern

####目的：使用不同的拜訪者使集合(collection)中的元素行為與元素切離
Collection是非常好用的東西，可以透過泛型裝進相同，類似的類別物件，但缺點就是物件的型為也就被泛型綁死了，
為了使類別物件的在集合中還能保有自己的特定，使用拜訪者將物件的行為封裝。

###中華料理大賽
中華料理大賽有分別來自特級廚師、黑暗料理界、特急麵點師傅三個陣營的廚師(Interface)參加比賽，參賽的廚師都會加入
參賽者名單(Collection)，假如第一輪比賽的題目是燒賣，每陣營廚師做出來的燒賣都長的不太一樣，可是因為他們已經被加在
Collection中，因此他們做出來的燒賣都會是一樣的(同樣的Interface行為相同)，這邊我們將比賽題目抽出成為Visitor，做燒賣(ConcreteVisotr)這個動作
則是實作Visitor的類別，我們將將每陣營廚師做燒賣的方法交給ConcreteVisotr，因此雖然他們的Interface是相同的，不過仍然可以做出不同的燒賣
，假如今天第二輪題目是做豆腐料理，那我們只要增加一個做豆腐(ConcreteVisotr)而不會破壞程式結構。    

類別圖
![Visitor Class Diagram](image/visitor.gif)  

程式碼
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

結果
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
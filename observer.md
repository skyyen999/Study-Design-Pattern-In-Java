# 觀察者模式 Observer Pattern
  
####目的：處理一個物件對應多個物件之間的連動關係
* 當一個被觀察物件(Subject)改變時，其他的觀察者物件(Observer)都會收到通知並且執行對應的動作

###冒險者協會發任務了
做為村莊內最重要的組織，冒險者協(邪)會定期會發布一些任務讓整天不知道要做什麼的冒險者們有事情做，當協會發布任務通知時，
每一名有在關心協會的冒險者就會接收到訊息，然後自己選擇要不要去執行任務。  

首先這邊需要一個被觀察介面(Subject)來定義一個被觀察者應該長怎樣，冒險者協會繼承這個介面成為實體的被觀察者介面(ConcreteSubject)。  
另外一邊一樣有一個觀察者介面(Observer)定義觀察者的行為，冒險者們繼承觀察者介面成為具體的觀察者(ConcreteObserver)。  
  
###類別圖  
![Observer Class Diagram](image/observer.gif)  
   
###程式碼
被觀察者介面Subject與實作類別
```
/**
 * 被觀察者介面(Subject)
 */
public abstract class Subject {
	protected List<Adventurer> list = new ArrayList<>();
	/**
	 * 觀察者想被通知
	 * @param observer
	 */
	public void add(Adventurer observer){
		list.add(observer);
	};
	
	/**
	 * 觀察者不想接到通知
	 * @param observer
	 */
	public void remove(Adventurer observer){
		list.remove(observer);
	}
	
	/**
	 * 貼出任務公告
	 * @param questions
	 */
	public abstract void sendQuestions(String questions);
}

/**
 * 冒險者協會(ConcreteSubject)
 */
public class Association extends Subject {

	@Override
	public void sendQuestions(String questions) {
		for(Adventurer adventurer : list){
			adventurer.getQuestions(questions);
		}
		
	}
}
```

觀察者介面Observer與實作類別  
```
/**
 * 冒險者(Observer)
 */
public abstract class Adventurer {
	protected String name;
	
	public Adventurer(String name){
		this.name = name;
	}
	/**
	 * 冒險者接受任務
	 */
	public abstract void getQuestions(String questions);
}

/**
 * 槍兵(ConcreteObserver)-繼承冒險者
 */
public class Lancer extends Adventurer {
	public Lancer(String name) {
		super(name);
	}

	@Override
	public void getQuestions(String questions) {
		System.out.println(name + ":單來就改，任務來就接，沒在怕的");		
	}
}

/**
 * 吟遊詩人(ConcreteObserver)-繼承冒險者
 */
public class Bard extends Adventurer {

	public Bard(String name) {
		super(name);
	}

	@Override
	public void getQuestions(String questions) {
		if(questions.length() > 10){
			System.out.println(name + ":任務太難了，我只會唱歌跳舞，不接不接");
		} else {
			System.out.println(name + ":當街頭藝人太難賺了，偶爾也是要解任務賺點錢的");
		}

	}
}

/**
 * 槍手(ConcreteObserver)-繼承冒險者
 */
public class Gunman extends Adventurer {

	public Gunman(String name) {
		super(name);
	}

	@Override
	public void getQuestions(String questions) {
		if(questions.length() < 10){
			System.out.println(name + ":任務太簡單了，我不想理他");
		} else {
			System.out.println(name + ":只要我的手上有槍，誰都殺不死我，出發執行任務賺獎金拉!!!");
		}
	}
}

```
測試碼  
```
/**
 * 觀察者模式-測試
 */
public class AssociationTest {
	@Test
	public void test () {
		System.out.println("============觀察者模式測試============");

		// 冒險者們
		Adventurer lancer = new Lancer("jacky");
		Adventurer lancer2 = new Lancer("seven");
		Adventurer bard = new Bard("lee");
		Adventurer gunman = new Gunman("longWu");
	
		// 冒險者協會
		Subject association = new Association();
		association.add(lancer);
		association.add(lancer2);
		association.add(bard);
		association.add(gunman);
		
		System.out.println("---派送簡單任務---");
		association.sendQuestions("run");
		
		System.out.println();
		System.out.println("---派送複雜任務---");
		association.sendQuestions("run run run, run for your life");
		
		// seven表示他不想接到任務通知了
		association.remove(lancer2);
		System.out.println();
		System.out.println("---派送複雜任務(seven已經不在名單中)---");
		association.sendQuestions("run run run, run for your life");	
	}
}

```
測試結果     
```
============觀察者模式測試============
---派送簡單任務---
jacky:單來就改，任務來就接，沒在怕的
seven:單來就改，任務來就接，沒在怕的
lee:當街頭藝人太難賺了，偶爾也是要解任務賺點錢的
longWu:任務太簡單了，我不想理他

---派送複雜任務---
jacky:單來就改，任務來就接，沒在怕的
seven:單來就改，任務來就接，沒在怕的
lee:任務太難了，我只會唱歌跳舞，不接不接
longWu:只要我的手上有槍，誰都殺不死我，出發執行任務賺獎金拉!!!

---派送複雜任務(seven已經不在名單中)---
jacky:單來就改，任務來就接，沒在怕的
lee:任務太難了，我只會唱歌跳舞，不接不接
longWu:只要我的手上有槍，誰都殺不死我，出發執行任務賺獎金拉!!!
```
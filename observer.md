# 觀察者模式 Observer Pattern
  
####目的：
* 當一個被觀察物件(Subject)改變時，其他的觀察者物件(Observer)都會收到通知並且做相對的動作，也就是定義一個被觀察物件對應多個觀察者物件的關係。

####冒險者協會發任務了
做為村莊內最重要的組織，冒險者協(邪)會定期會發布一些任務讓整天不知道要做什麼的冒險者們有事情做，當協會發布任務通知時，
每一名有在關心協會的冒險者就會接收到訊息，然後執行任務。  

首先這邊需要一個被觀察介面(Subject)來定義一個被觀察者應該長怎樣，冒險者協會繼承這個介面成為實體的被觀察者介面(ConcreteSubject)。  
另外一邊一樣有一個觀察者介面(Observer)定義觀察者的行為，冒險者們繼承觀察者介面成為具體的觀察者(ConcreteObserver)。  

在下面的實做中，協會就只是一個協會，可以讓冒險者自由決定是否收到任務通知，冒險者們會根據自己能力評估是否接受任務。

 
  
類別圖：  
![Association Observer](image/observer.gif)  
   
程式碼：  
```
/**
 * 被觀察者介面
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
 * 槍兵(Concrete Observer)-繼承冒險者
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
 * 吟遊詩人(Concrete Observer)-繼承冒險者
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
 * 槍手(Concrete Observer)-繼承冒險者
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
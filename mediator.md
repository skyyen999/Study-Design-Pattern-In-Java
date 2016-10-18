# 中介者模式 Mediator Pattern

####目的：當有多個物件之間有交互作用，使用一個中介物件來負責這些物件的交互

###聊天系統  
![Meditor Exmple]mediatorExmple(image/mediatorExmple.png)
上圖左邊四個圈圈ABCD代表我們的聊天系統上有4個使用者，每一個使用者都能跟其他使用者聊天，
因此之間的關係會變的像圖一樣很混亂，如果可以將傳遞訊息的工作統一交給中介者(Mediator)，像右圖這樣，
程式的架構會比較清楚，而且可以切開ABCD之間的互相耦合。   
  
###類別圖  
![Mediator Class Diagram](image/.gif)  

###程式碼
```
Mediator介面與實作類別
/**
 * 中介者介面(Mediator)
 */
public interface Mediator {
	// 發訊息給某人
	void send(String message,String from , Messager to);
	
	// 發訊息給每個人
	void sendToAll(String from, String message);
}

/**
 * 中介者類別(ConcreteMediator)
 */
public class MessageMediator implements Mediator {
	private static List<VIPUser> vipList = new ArrayList<>();
	private static List<CommonUser> userList = new ArrayList<>();

	public static void joinChat(Messager messager){
		if(messager.getClass().getSimpleName().equals("VIPUser")){
			vipList.add((VIPUser) messager);
		} else {
			userList.add((CommonUser) messager);
		}
	}
	
	// 發訊息給某人
	public void send(String message,String from , Messager to){
		for(Messager msg : vipList){
			if(msg.getName().equals(from)){
				System.out.println(from + "->" + to.getName() + ":" + message);
			}
		}
		for(Messager msg : userList){
			if(msg.getName().equals(from)){
				System.out.println(from + "->" + to.getName() + ":" + message);
			}
		}
	};
	
	// 發訊息給每個人
	public void sendToAll(String from, String message){
		for(Messager msg : vipList){
			if(!msg.getName().equals(from)){
				System.out.println(from + "->" + msg.getName() + ":" + message);
			}
		}
		
		for(Messager msg : userList){
			if(!msg.getName().equals(from)){
				System.out.println(from + "->" + msg.getName() + ":" + message);
			}
		}
		
	};
}
``` 
Colleague介面與實作類別  
``` 
/**
 * 定義可以發送訊息的物件介面(Colleague)
 */
public abstract class Messager {	
	private String name;
	public static Mediator mediator = new MessageMediator();
	
	public Messager(String name){
		this.name = name;
	}
	
	// 發訊息給每個人
	public void sendToAll(String message){
		mediator.sendToAll(name,message);
	}
	
	// 發訊息給某人
	public void send(String message, Messager to){
		mediator.send(message, this.name , to);
	};


	public String getName() {
		return this.name;
	};
}

/**
 * 可以發送訊息的類別(ConcreteColleague)
 */
public class CommonUser extends Messager{

	public CommonUser(String name) {
		super(name);
	}	

	@Override
	public void sendToAll(String message){
		System.out.println("非VIP用戶不能使用廣播");
	}
}

/**
 * 可以發送訊息的類別(ConcreteColleague)
 */
public class VIPUser extends Messager{
	public VIPUser(String name) {
		super(name);
	}	
}
```  
測試碼  
```  
/**
 * 中介者模式-測試
 */
public class MediatorTest {
	@Test
	public void Test(){
		System.out.println("============中介者模式測試============");

		Messager jacky = new VIPUser("jacky");
		Messager huant = new CommonUser("huant");
		Messager neil = new CommonUser("neil");
		
		MessageMediator.joinChat(jacky);
		MessageMediator.joinChat(huant);
		MessageMediator.joinChat(neil);
		System.out.println("---VIP會員直接送訊息給每個人---");
		jacky.sendToAll("hi, 你好");
		
		System.out.println("---私底下送訊息---");
		jacky.send("單挑阿!PK阿!", huant);

		neil.send("收假了!!掰掰", jacky);
		System.out.println("當非VIP會員想送訊息給每個人");
		neil.sendToAll("阿阿阿!!!");
	}
}
```  
測試結果
```  
============中介者模式測試============
---VIP會員直接送訊息給每個人---
jacky->huant:hi, 你好
jacky->neil:hi, 你好
---私底下送訊息---
jacky->huant:單挑阿!PK阿!
neil->jacky:收假了!!掰掰
---當非VIP會員想送訊息給每個人---
非VIP用戶不能使用廣播
```  

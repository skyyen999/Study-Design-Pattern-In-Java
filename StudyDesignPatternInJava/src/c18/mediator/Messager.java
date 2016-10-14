package c18.mediator;
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
	void sendToAll(String message){
		mediator.sendToAll(name,message);
	}
	
	// 發訊息給某人
	void send(String message, Messager to){
		mediator.send(message, this.name , to);
	};


	public String getName() {
		return this.name;
	};
}

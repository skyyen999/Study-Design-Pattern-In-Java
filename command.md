# 命令模式 Command Pattern
  
####目的：將各種請求(命令Command)封裝成一個物件
* 客戶端(Client)不直接發送請求給命令執行者(Receiver)，而是將請求統一交給命令接收者(Invoker)轉交給命令執行者，
接收者可將請求排成工作序列，也可以進行尚未執行的請求

  
###解完任務後到咖啡廳來一杯吧
對冒險者們來說，解完任務後冒險者飲料店來喝一杯飲料，吃個甜點是很重要的，現在我們來看看這家飲料店的配置，
首先外場有可愛的女服務生(Invoker)接受冒險者(Client)填的點餐訂單(Command)，目前飲料店販賣的有飲料跟點心兩種產品，
因此訂單也就分成了飲料訂單跟點心訂單，全部的訂單都由廚房人員(Receiver)來負責處理，飲料訂單由搖飲料的小弟負責，
點心廚師負責做出美味的點心，因此廚房人員不必直接與冒險者接觸可以專心工作。另外在點餐時，服務生小妹會先詢問目前材料是否
足夠，不夠的話就不需要麻煩廚房人員了，多才多藝的服務生小妹在訂單還沒送出前，也可以接受冒險者的要求取消訂單。

###類別圖  
![Command Class Diagram](image/command.gif)  
   
###程式碼
Receiver介面與實作類別
```
/**
 * 廚房人員(Receiver)
 */
public interface KitchenWoker {
	/**
	 * 完成訂單
	 */
	void finishOrder();
}

/**
 * 搖飲料小弟(ConcreteReceiver)
 */
public class Barkeep implements KitchenWoker{

	@Override
	public void finishOrder() {
		System.out.print("拿出杯子->加滿冰塊->把飲料倒進杯子->飲料完成");
		System.out.println();
	}

}

/**
 * 點心廚師(ConcreteReceiver)
 */
public class Chef implements KitchenWoker {

	@Override
	public void finishOrder() {
		System.out.print("取出麵包->美乃滋塗上滿滿的麵包->丟進烤箱->灑上可以吃的裝飾->點心完成");
		System.out.println();
	}

}
```
Command介面與實作類別

```
/**
 * 訂單(Command)
 */
public abstract class Order {
	// 廚房工作者(receiver)
	protected KitchenWoker receiver;
	protected String name;
	
	public Order(KitchenWoker receiver){
		this.receiver = receiver;
	}
	
	// 將訂單送給廚房人員
	public void sendOrder(){
		receiver.finishOrder();
	}
	
	// 讓其他程式知道這是什麼訂單
	public String getName(){
		return this.name;
	}
	
}

/**
 * 飲料訂單(ConcreteCommand)
 */
public class DrinkOrder extends Order {
	public DrinkOrder(KitchenWoker receiver) {
		super(receiver);
		super.name = "drinkOrder";
	}
}

/**
 * 點心訂單(ConcreteCommand)
 */
public class SnackOrder extends Order {
	public SnackOrder(KitchenWoker receiver) {
		super(receiver);
		super.name = "snackOrder";
	}
}
```
Invoker類別
```
/**
 * 服務生(Invoker)
 */
public class Waitress {
	private int snackQty = 2; // 製作點心的原料
	private int drinkQty = 4; // 飲料剩餘的杯數 
	private List<Order> orderList = new ArrayList<>();
	
	/**
	 * 服務生接收訂單
	 * @param order
	 */
	public void setOrder(Order order) {
		 
		if(order.name.equals("snackOrder")){
			if(snackQty <= 0){
				System.out.println("點心賣完了");
			} else {
				System.out.println("增加點心訂單");
				snackQty--;
				orderList.add(order);				
			}
		} 
		
		if(order.name.equals("drinkOrder")){
			if(drinkQty <= 0){
				System.out.println("飲料賣完了");
			} else {
				System.out.println("增加飲料訂單");
				drinkQty--;
				orderList.add(order);				
			}
		} 
	}

	/**
	 * 取消訂單
	 * @param order
	 */
	public void cancelOrder(Order order) {
		
		if(order.name.equals("drinkOrder")){
			drinkQty++;
			System.out.println("取消一杯飲料");
		}
		
		if(order.name.equals("snackOrder")){
			snackQty++;
			System.out.println("取消一個點心");
		}
		orderList.remove(order);		
	}
	
	/**
	 * 將訂單送到廚房
	 */
	public void notifyBaker() {
		for(Order order : orderList){
			order.sendOrder();
		}
		orderList.clear();
	}
}
```
測試碼  
```
public class CoffeShopClient {
	public static void main(String[] args) {
		System.out.println("============命令模式測試============");
		//開店前準備
		Chef snackChef = new Chef();
		Barkeep  barkeep = new Barkeep ();
		Order snackOrder = new SnackOrder(snackChef);
		Order drinkOrder = new DrinkOrder(barkeep);
		
		Waitress cuteGirl = new Waitress();
		System.out.println("====客人點餐====");

		//開始營業 客戶點餐
		cuteGirl.setOrder(snackOrder);
		cuteGirl.setOrder(snackOrder);
		cuteGirl.setOrder(drinkOrder);
		cuteGirl.setOrder(drinkOrder);

		// 飲料還沒賣完
		cuteGirl.setOrder(drinkOrder);
		System.out.println("====客人取消點心測試====");
		// 取消一個點心
		cuteGirl.cancelOrder(snackOrder);
		// 點心又可以賣了
		cuteGirl.setOrder(snackOrder);
		System.out.println("===點餐完成，送到後面廚房通知廚師與搖飲料小弟===");
		cuteGirl.notifyBaker();
		System.out.println();
		System.out.println("====點心庫存不足測試====");
		// 點心賣完了
		cuteGirl.setOrder(snackOrder);
	}
}
```  
  
測試結果
```
============命令模式測試============
====客人點餐====
增加點心訂單
增加點心訂單
增加飲料訂單
增加飲料訂單
增加飲料訂單
====客人取消點心測試====
取消一個點心
增加點心訂單
===點餐完成，送到後面廚房通知廚師與搖飲料小弟===
取出麵包->美乃滋塗上滿滿的麵包->丟進烤箱->灑上可以吃的裝飾->點心完成
拿出杯子->加滿冰塊->把飲料倒進杯子->飲料完成
拿出杯子->加滿冰塊->把飲料倒進杯子->飲料完成
拿出杯子->加滿冰塊->把飲料倒進杯子->飲料完成
取出麵包->美乃滋塗上滿滿的麵包->丟進烤箱->灑上可以吃的裝飾->點心完成

====點心庫存不足測試====
點心賣完了

```

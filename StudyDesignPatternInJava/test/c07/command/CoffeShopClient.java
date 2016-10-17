package c07.command;

import c07.command.coffeShop.Barkeep;
import c07.command.coffeShop.Chef;
import c07.command.coffeShop.DrinkOrder;
import c07.command.coffeShop.Order;
import c07.command.coffeShop.SnackOrder;
import c07.command.coffeShop.Waitress;

/**
 * 命令模式-測試
 */
public class CoffeShopClient {
	public static void main(String[] args) {
		System.out.println("============命令模式測試============");
		//開店前準備
		Chef snackChef = new Chef();
		Barkeep  barkeep = new Barkeep ();
		Order snackOrder = new SnackOrder(snackChef);
		Order drinkOrder = new DrinkOrder(barkeep);
		
		Waitress cuteGirl = new Waitress();
		
		//開始營業 客戶點餐
		cuteGirl.setOrder(snackOrder);
		cuteGirl.setOrder(snackOrder);
		cuteGirl.setOrder(drinkOrder);
		cuteGirl.setOrder(drinkOrder);

		// 飲料還沒賣完
		cuteGirl.setOrder(drinkOrder);
		// 取消一個點心
		cuteGirl.cancelOrder(snackOrder);
		// 點心又可以賣了
		cuteGirl.setOrder(snackOrder);
		
		System.out.println();
		// 點餐完成，送到後面廚房通知廚師與搖飲料小弟
		cuteGirl.notifyBaker();
		
		System.out.println("---點心賣完了---");
		// 點心賣完了
		cuteGirl.setOrder(snackOrder);
	}
}

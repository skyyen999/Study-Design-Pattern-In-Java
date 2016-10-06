package c7.command;

import c7.command.CoffeShop.Barkeep;
import c7.command.CoffeShop.Chef;
import c7.command.CoffeShop.DrinkOrder;
import c7.command.CoffeShop.Order;
import c7.command.CoffeShop.SnackOrder;
import c7.command.CoffeShop.Waitress;

/**
 * 冒險者飲料店點餐-測試
 */
public class CoffeShopClient {
	public static void main(String[] args) {
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
		// 點心賣完了
		cuteGirl.setOrder(snackOrder);
		// 飲料還沒賣完
		cuteGirl.setOrder(drinkOrder);
		// 取消一個點心
		cuteGirl.cancelOrder(snackOrder);
		// 點心又可以賣了
		cuteGirl.setOrder(snackOrder);
		
		System.out.println();
		// 點餐完成，送到後面廚房通知廚師與搖飲料小弟
		cuteGirl.notifyBaker();
	}
}

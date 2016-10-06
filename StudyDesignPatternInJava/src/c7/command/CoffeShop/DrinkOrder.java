package c7.command.CoffeShop;

/**
 * 飲料訂單(ConcreteCommand)
 */
public class DrinkOrder extends Order {
	public DrinkOrder(KitchenWoker receiver) {
		super(receiver);
		super.name = "drinkOrder";
	}
}

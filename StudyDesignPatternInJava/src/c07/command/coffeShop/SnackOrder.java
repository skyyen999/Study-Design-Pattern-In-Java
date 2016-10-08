package c07.command.coffeShop;

/**
 * 點心訂單(ConcreteCommand)
 */
public class SnackOrder extends Order {
	public SnackOrder(KitchenWoker receiver) {
		super(receiver);
		super.name = "snackOrder";
	}
}

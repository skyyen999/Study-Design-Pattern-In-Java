package c4.strategy.flight;
/**
 * 使用道具
 */
public class UseItem implements FlightStrategy {
	@Override
	public void execute() {
		System.out.println("使用道具，丟火把");
	}
}

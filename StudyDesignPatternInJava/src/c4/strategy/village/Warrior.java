package c4.strategy.village;
/**
 * 鬥士
 * @author Yan
 *
 */
public class Warrior extends Adventurer {

	@Override
	public String getType() {
		System.out.println("我是鬥士");	
		return  this.getClass().getSimpleName();
	}
	
}

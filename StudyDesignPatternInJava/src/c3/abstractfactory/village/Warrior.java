package c3.abstractfactory.village;

/**
 * 鬥士
 * @author Yan
 *
 */
public class Warrior extends Adventurer {
	@Override public void display() {
		System.out.println("我是弓箭手，裝備:");
		weapon.display();
		System.out.println();
		clothes.display();
		System.out.println();
	}
}

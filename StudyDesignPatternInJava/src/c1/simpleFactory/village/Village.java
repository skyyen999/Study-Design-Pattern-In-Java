package c1.simpleFactory.village;

/**
 * 訓練冒險者的訓練營(簡單工廠)
 * @author Yan
 *
 */
public class Village {
	public static Adventurer trainAdventurer(String type){
		switch(type){
			case "archer" : System.out.println("訓練一個弓箭手");return new Archer(); 
			case "warrior": System.out.println("訓練一個鬥士");return new Warrior();
			default : return null;
		}
	}
	
	public static void main(String[] args) {
		
	}
}

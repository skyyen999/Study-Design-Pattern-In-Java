package c5.decorator.title;

/**
 * 稱號父類別
 */
public class Title extends Adventurer{
	protected Adventurer adventurer;
	/**
	 * 原本的冒險者被傳進來，增加增號
	 * @param adventurer
	 */
	public Title(Adventurer adventurer){
		this.adventurer = adventurer;
	}
	
	@Override
	public void attack(){
		this.adventurer.attack();
	}
}

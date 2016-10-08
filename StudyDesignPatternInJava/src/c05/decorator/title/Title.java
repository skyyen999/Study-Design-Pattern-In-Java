package c05.decorator.title;

/**
 * 稱號介面
 */
public abstract class Title implements Adventurer{
	/**
	 * 被裝飾的冒險者
	 */
	protected Adventurer adventuerer;
	
	public Title(Adventurer adventuerer){
		this.adventuerer = adventuerer;
	}
	
	@Override
	public void attack(){
		adventuerer.attack();
	}
}

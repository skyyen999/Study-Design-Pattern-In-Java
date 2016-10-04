package c5.decorator.title;
/**
 * 稱號-敏捷
 */
public class Agile extends Title{
	public Agile(Adventurer adventurer) {
		super(adventurer);
	}
	
	// 稱號讓攻擊變快
	@Override
	public void attack(){
		System.out.print("快速 ");
		super.attack();
	}
}

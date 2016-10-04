package c5.decorator.title;
/**
 * 稱號-強壯
 */
public class Strong extends Title{	
	public Strong(Adventurer adventurer) {
		super(adventurer);
	}
	
	// 稱號讓攻擊力增加
	@Override
	public void attack(){
		System.out.print("猛力 ");
		super.attack();
	}
}

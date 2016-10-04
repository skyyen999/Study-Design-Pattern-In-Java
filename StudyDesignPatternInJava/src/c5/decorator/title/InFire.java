package c5.decorator.title;
/**
 * 稱號-燃燒
 */
public class InFire extends Title{
	public InFire(Adventurer adventurer) {
		super(adventurer);
	}
	
	// 稱號讓攻擊增加燃燒
	@Override
	public void attack(){
		System.out.print("燃燒 ");
		super.attack();
	}
}

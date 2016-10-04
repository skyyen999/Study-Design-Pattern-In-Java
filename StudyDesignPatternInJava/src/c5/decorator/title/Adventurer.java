package c5.decorator.title;

/**
 * 一般的冒險者
 */
public class Adventurer {
	// 冒險者的姓名
	private String name ;
	
	public Adventurer(){
	}
	// 冒險者被創立的時候要有姓名
	public Adventurer(String name){
		this.name = name;
	}

	/**
	 * 一般攻擊
	 */
	public void attack(){
		System.out.println("攻擊");
	}

	public String getName(){
		return this.name;
	}
}

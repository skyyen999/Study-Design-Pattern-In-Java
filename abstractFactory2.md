# 抽象工廠模式 Abstract Factory Pattern 1
現在已經有各式各樣的工廠可以生產裝備，接下來就看看實際上要怎麼給冒險者裝備。  
首先要先為冒險者增加兩個屬性，武器Weapon，衣服Clothes。  
接下來訓練營內必須要有對應的工廠來生產對應的裝備。   
最後呼叫的客戶端程式與[工廠模式 Factory](factory.md)一樣，不過現在冒險者們在訓練後就會獲得基礎裝備了。
  
程式碼：  
```
//工廠與各種裝備同上頁

/**
 * 冒險者
 */
public abstract class Adventurer {
	protected Weapon weapon;	//武器
	protected Clothes clothes;  //衣服
	/**
	 * 看冒險者的狀態
	 */
	public abstract void display();
	// getter & setter 省略
}

/**
 * 實體工廠-弓箭手訓練營
 */
public class ArcherTrainingCamp implements TrainingCamp {
	private static EquipFactory factory = new ArcherEquipFactory();

	@Override
	public Adventurer trainAdventurer() {
		System.out.println("訓練一個弓箭手");
		Archer archer = new Archer();
		// ...進行基本訓練
		// ...弓箭手訓練課程
		// 訓練完成配發裝備
		archer.setWeapon(factory.productWeapon());
		archer.setClothes(factory.productArmor());
		return archer; 
	}

}

/**
 *  工廠介面-冒險者訓練營(這只是一個概念或規範，要訓練什麼，怎麼訓練留給子類別實作)
 */
public interface TrainingCamp {
	/**
	 * 訓練冒險者的過程，訓練後請給我一個冒險者
	 */
	public Adventurer trainAdventurer();

}



/**
 * 實體工廠-弓箭手訓練營
 */
public class ArcherTrainingCamp implements TrainingCamp {
	private static EquipFactory factory = new ArcherEquipFactory();

	@Override
	public Adventurer trainAdventurer() {
		System.out.println("訓練一個弓箭手");
		Archer archer = new Archer();
		// ...進行基本訓練
		// ...弓箭手訓練課程
		// 訓練完成配發裝備
		archer.setWeapon(factory.productWeapon());
		archer.setClothes(factory.productArmor());
		return archer; 
	}
}

/**
 * 實體工廠-鬥士訓練營
 */
public class WarriorTrainingCamp implements TrainingCamp {
	private static EquipFactory factory = new WarriorEquipFactory();
	
	@Override
	public Adventurer trainAdventurer() {
		System.out.println("訓練一個鬥士");
		Warrior warrior = new Warrior();
		// ...進行基本訓練
		// ...鬥士訓練課程
		
		// 訓練完成配發裝備
		warrior.setWeapon(factory.productWeapon());
		warrior.setClothes(factory.productArmor());
		return warrior; 
	}
}

/**
 * 弓箭手
 */
public class Archer extends Adventurer {
	
	@Override
	public void display() {
		System.out.println("我是弓箭手，裝備:");
		weapon.display();
		System.out.println();
		clothes.display();
		System.out.println();
	}
}

/**
 * 鬥士
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

/**
 * 冒險者訓練營加上裝備工廠測試
 */
public class TrainingCampWithFactoryTest {
	@Test
	public void test(){
		// 弓箭手訓練營
		TrainingCamp camp = new ArcherTrainingCamp();
		// 訓練弓箭手
		Adventurer archer = camp.trainAdventurer();
		
		// 鬥士訓練營
		camp = new WarriorTrainingCamp();
		// 訓練鬥士
		Adventurer warrior = camp.trainAdventurer();
		
		archer.display();
		warrior.display();
	}
}
```
  
工廠模式與抽像工廠模式比較：  
  
*工廠模式：工廠模式注重的是如何產生一個物件，例如弓箭手訓練營只要負責管理產出弓箭手的過程。  
	
*抽像工廠模式：	抽像工廠模式注重在產品的抽象關係，像武器與衣服本來是扯不上關係的兩種物品，不過這兩種物品都是冒險者的裝備，因此他們有這個層抽象關係。
# 策略模式 Strategy Pattern
  
####目的：
* 將各種可以互換的演算法(策略)包裝成一個類別。

####冒險者要來打怪物了
剛才的簡單工廠模式因為只有一個訓練營，要新增冒險者型態時會直接破壞了開放/封閉原則，
在工廠模式中，將訓練營提升為一個抽象的概念(定義什麼是訓練營)，實際上要如何訓練各種冒險者由各種訓練營來實作，
例如說弓箭手訓練營專門訓練弓箭手，鬥士訓練營產生鬥士。如此一來，如果想要增加冒險者的類別，只要新增訓練營實體類別，
而不會改動到抽象的訓練營概念。  
  
類別圖：  
![Training Camp](image/strategy.gif)  
   
程式碼：  
```
/**
 * 冒險者
 */
public class Adventurer {
	Weapon weapon;  //不同的武器有不同的攻擊方式(strategy)
	/**
	 * 用武器攻擊
	 */
	public void attack(){
		if(weapon == null){
			System.out.println("用拳頭毆打");
		}
		weapon.use();
	}
	
	/**
	 * 選擇不同的武器(策略)
	 */
	public void choiceWeapon(Weapon weapon){
		this.weapon = weapon;
	}
}

public interface Weapon {
	/**
	 * 使用武器攻擊
	 */
	void use();
}


/**
 * 武器-弓
 */
public class Bow implements Weapon {

	@Override
	public void use() {
		System.out.println("射弓箭");
	}

}

/**
 * 武器-長劍
 */
public class LongSword implements Weapon {

	@Override
	public void use() {
		System.out.println("拿劍亂砍");
	}

}


public class Touch implements Weapon {

	@Override
	public void use() {
		System.out.println("用火燒");
	}

}


public class FlightTest {
	
	@Test
	public void test(){
		Adventurer ad = new Adventurer();
		
		// 一開始使用長劍
		ad.choiceWeapon(new LongSword());
		System.out.println("出現史萊姆");
		ad.attack();
		
		// 出現會飛的敵人，改用弓箭
		System.out.println("出現會飛的吸血蝙蝠");
		ad.choiceWeapon(new Bow());
		ad.attack();

		// 出現不怕刀槍只怕火的敵人，改用火把
		System.out.println("出現不怕刀槍的殭屍");
		
	}
	
}
```
  
簡單工廠模式與工廠模式比較：  
  
*簡單工廠模式：工廠直接負責管理所有的產品，利用if else 或 switch case判斷式來產生產品。  
	
*工廠模式：	工廠提升為一個概念，實際上產生產品的是實作工廠概念的實體工廠。
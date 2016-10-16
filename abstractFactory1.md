# 抽象工廠模式 Abstract Factory Pattern 1

####目的：用一個工廠介面來產生一系列相關的物件，但實際建立哪些物件由實作工廠的子類別來實現

###出發冒險之前，一定要有裝備
有了冒險者之後，他們還需要各種裝備才能出門探險，假如一個冒險者需要武器、頭盔、上衣、褲子、鞋子5種裝備，
村莊內又有4種不同專業的冒險者，這樣我們就要建立20種工廠類別來生產裝備，而且每增加一種冒險者類別，
就要為他多增長5個工廠類別，如果使用剛才的工廠模式來管理生產裝備，程式碼很容易變雜亂。  
  
因此我們改變一下工廠的功能，一先定義工廠介面(Factory)，這邊規定工廠現在不是生產一種產品，
而是生產一個冒險者類別一系列所有的裝備，也就是說一間工廠要生產武器、頭盔、上衣、褲子、鞋子5種裝備(Product)，
當然有了工廠介面後當然也需要實體工廠(ConcreteFactory)，例如說鬥士裝備生產工廠就會生產一系列的鬥士裝備(ConcreteProduct)
，這就是抽象工廠模式。  
  
以下範例讓偷懶一下，一個冒險者只有武器與上衣兩種裝備就好。  
  
###類別圖  
![AbstractFactory Class Diagram](image/abstractFactory.gif)  
   
###程式碼  
產品介面與實體產品類別  
```
/**
 * 上衣介面(Product)
 */
public abstract class Clothes {
	protected int def;	// 防禦力
	/**
	 * 展示這件衣服
	 */
	public void display(){
		System.out.println(this.getClass().getSimpleName() + " def = " + def);
	}
	// 以下省略getter setter
}

/**
 * 盔甲(ConcreteProduct)-鬥士上衣
 */
public class Armor extends Clothes {

}

/**
 * 皮甲(ConcreteProduct)-弓箭手上衣
 */
public class Leather extends Clothes {

}


/**
 * 武器介面(Product)
 */
public abstract class Weapon {
	protected int atk;		// 攻擊力
	protected int range;   // 攻擊範圍
	
	/**
	 * 展示武器
	 */
	public void display(){
		System.out.println(this.getClass().getSimpleName() + " atk = " + atk + " , range = " + range);
	}
	
	// 以下省略getter setter
}

/**
 * 長劍(ConcreteProduct)-鬥士武器
 */
public class LongSword extends Weapon {

}

/**
 * 弓(ConcreteProduct)-弓箭手武器
 */
public class Bow extends Weapon {

}
```
工廠介面與實體工廠類別  
```
/**
 * 裝備工廠介面(Factory)-定義每一間工廠應該生產哪些東西
 */
public interface EquipFactory {
	/**
	 * 製造武器
	 * @return 
	 */
	Weapon productWeapon();
	/**
	 * 製造衣服
	 */
	Clothes productArmor();
}

/**
 * 專門生產鬥士裝備的工廠(ConcreteFactory)
 */
public class WarriorEquipFactory implements EquipFactory{

	@Override
	public Weapon productWeapon() {
		LongSword product = new LongSword();
		product.setAtk(10);
		product.setRange(1);
		return product;
	}

	@Override
	public Clothes productArmor() {
		Armor product = new Armor();
		product.setDef(10);
		return product;		
	}

}
  
/**
 * 專門生產弓箭手裝備的工廠(ConcreteFactory)
 */
public class ArcherEquipFactory implements EquipFactory{

	@Override
	public Weapon productWeapon() {
		Bow product = new Bow();
		product.setAtk(10);
		product.setRange(10);
		return product;
	}

	@Override
	public Clothes productArmor() {
		Leather product = new Leather();		
		product.setDef(5);
		return product;
	}

}  
```  
測試碼留在[抽象工廠模式 2](abstractFactory2.md)再做測試。  
  
  
工廠模式與抽像工廠模式比較：  
  
* 工廠模式：工廠模式注重的是如何產生一個物件，例如弓箭手訓練營只要負責管理產出弓箭手的過程。  
	
* 抽像工廠模式：抽像工廠模式注重在產品的抽象關係，像武器與衣服本來是扯不上關係的兩種物品，不過這兩種物品都是冒險者的裝備，因此他們有這個層抽象關係。
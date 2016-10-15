# 裝飾模式 Decorator Pattern
  
####目的：動態的將功能附加在物件上

###冒險者各式各樣的稱號
"風暴降生，不焚者，彌林女王，安達爾人，羅伊納人和先民的女王，七國統治者暨全境守護者、多斯拉克大草原的卡麗熙、碎鐐者、龍之母"，
前面這一串封號跟裝飾模式其實沒什麼關係。
  
在遊戲中，冒險者可以透過各種冒險或訓練得到稱號加強本身的能力，例如說"強壯的冒險者"攻擊力比較高，"堅毅的冒險者"生命力比較高，
"炎龍的冒險者"攻擊的時候可以順便用火燒敵人，在前面的設計中，我們可能新增三個繼承冒險者子類別來實現這樣的設計，
不過冒險者是可以取得很多稱號的，例如"強壯堅毅敏捷的冒險者"，"強壯飛翔的冒險者"等等各種交差排列組合，如果可以選的稱號有3種，
那我們就要建立3*2*1=6個子類別，如果可以選的稱號有5種，那要建立的子類別就多達120種。這還沒算上冒險者可以取得重複的稱號的情況，
例如"強壯的強壯的冒險者"，那要建立的子類別就更多了。  

為了避免上面這種很可怕的事情發生，這邊可以使用裝飾模式來處理，首先一樣有一個冒險者介面(Component)來規範冒險者應該會有什麼方法，接著會有繼承
冒險者的實體類別(ConcreteComponent)例如說弓箭手、槍兵、鬥士等等。接下來將之前那些稱號變成裝飾者介面(Decorator)而且繼承冒險者介面，Decorator中
規範如何裝飾原本的ConcreteComponent，接著一些繼承Decorator的實際裝飾類別(ConcreteDecorator)則是真正用來裝飾ConcreteComponent的類別，
也就是使用稱號來增加冒險者的能力。  

實做如下方程式碼所示，我們的冒險者jacky一開始只是一般的小槍兵，只能使用普通的長槍戳人，無法使用任何技能，接下來

  
類別圖：  
![Title Decorator](image/decorator.gif)  
   
程式碼：  
```
/**
 * 冒險者介面，規範冒險者應該有的功能
 */
public interface Adventurer {	
	/**
	 * 攻擊
	 */
	 void attack();
}


/**
 * 冒險者-長槍兵
 */
public class Lancer implements Adventurer{
	// 冒險者的姓名
	private String name ;

	// 冒險者被創立的時候要有姓名
	public Lancer(String name){
		this.name = name;
	}

	// 攻擊
	public void attack(){
		System.out.println("長槍攻擊 by " + name);
	}
}


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

/**
 * 稱號-強壯
 */
public class TitleStrong extends Title{	
	public TitleStrong(Adventurer adventurer) {
		super(adventurer);
	}
	
	// 稱號讓攻擊力增加
	@Override
	public void attack(){
		System.out.print("猛力 ");
		super.attack();
	}
}


/**
 * 稱號-敏捷
 */
public class TitleAgile extends Title{	

	public TitleAgile(Adventurer adventuerer) {
		super(adventuerer);
	}

	// 稱號讓攻擊變快
	@Override
	public void attack(){
		System.out.print("快速 ");
		super.adventuerer.attack();
	}

	// 取得稱號後獲得新的技能
	public void useFlash(){
		System.out.println("使用瞬間移動");
	}

}


/**
 * 稱號-燃燒
 */
public class TitleInFire extends Title{
	public TitleInFire(Adventurer adventurer) {
		super(adventurer);
	}
	
	// 稱號讓攻擊增加燃燒
	@Override
	public void attack(){
		System.out.print("燃燒 ");
		super.attack();
	}
	
	public void fireball(){
		System.out.println("丟火球");
	}
}


/**
 * 冒險者使用不同稱號來強化-測試
 */
public class TitleTest {
	@Test
	public void test(){
		// 一開始沒有任何稱號的冒險者
		Adventurer lancer = new Lancer("Jacky");
		System.out.println("---長槍兵Jacky---");
		lancer.attack();
		
		System.out.println();	
		System.out.println("---取得強壯稱號的jacky---");
		TitleStrong sJacky = new TitleStrong(lancer);
		sJacky.attack();

		
		System.out.println();
		System.out.println("---取得敏捷稱號的jacky---");
		TitleAgile aJacky = new TitleAgile(sJacky);
		aJacky.attack();
		aJacky.useFlash();
		
		System.out.println();
		System.out.println("---取得燃燒稱號的jacky---");
		TitleInFire fJacky = new TitleInFire(sJacky);
		fJacky.attack();
		fJacky.fireball();	
		
		System.out.println("---jacky決定成為一個非常強壯的槍兵---");
		TitleStrong ssJacky = new TitleStrong(fJacky);
		ssJacky.attack();
	}
}

```


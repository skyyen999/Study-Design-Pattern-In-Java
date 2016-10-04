# 裝飾模式 Decorator Pattern
  
####目的：
* 動態的將功能附加在物件上。

####冒險者各式各樣的稱號
"風暴降生，不焚者，彌林女王，安達爾人，羅伊納人和先民的女王，七國統治者暨全境守護者、多斯拉克大草原的卡麗熙、碎鐐者、龍之母"，
前面這一串封號跟裝飾模式其實沒什麼關係。
  
在遊戲中，冒險者可以透過各種冒險或訓練得到稱號加強本身的能力，例如說"強壯的冒險者"攻擊力比較高，"堅毅的冒險者"生命力比較高，
"炎龍的冒險者"攻擊的時候可以順便用火燒敵人，在前面的設計中，我們可能新增三個繼承冒險者子類別來實現這樣的設計，
不過冒險者是可以取得很多稱號的，例如"強壯堅毅敏捷的冒險者"，"強壯飛翔的冒險者"等等各種交差排列組合，如果可以選的稱號有3種，
那我們就要建立3*2*1=6個子類別，如果可以選的稱號有5種，那要建立的子類別就多達120種。這還沒算上冒險者可以取得重複的稱號的情況，
例如"強壯的強壯的冒險者"，那要建立的子類別就更多了。  

為了避免上面這種很可怕的事情發生，使用裝飾模式讓各種稱號都變成冒險者的子類別，將冒險者傳入各種稱號子類別中(也就是進行裝飾)使用裝飾模式讓各種稱號都變成冒險者的子類別，將冒險者傳入各種稱號子類別中
使冒險者的能力隨稱號增加，實現的方法如下面的Code。
  
類別圖：  
![Title Decorator](image/decorator.gif)  
   
程式碼：  
```
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


/**
 * 稱號-燃燒
 */
public class Agile extends Title{
	public Agile(Adventurer adventurer) {
		super(adventurer);
	}
	
	// 稱號讓攻擊增加燃燒
	@Override
	public void attack(){
		System.out.print("快速 ");
		super.attack();
	}
}

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



public class TitleTest {
	@Test
	public void test(){
		// 一開始沒有任何稱號的冒險者
		Adventurer adventurer = new Adventurer("Jacky");
		System.out.println("冒險者Jacky");
		adventurer.attack();
		
		//jacky 變成強壯的冒險者
		Title sJacky = new Strong(adventurer);
		sJacky.attack();
	
		//jacky 變成燃燒的強壯的冒險者
		Title fJacky = new InFire(sJacky);
		fJacky.attack();
		
		// jacky 增加敏捷稱號
		Title aJacky = new Agile(fJacky);
		aJacky.attack();
		
	}
}
```


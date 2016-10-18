# 建造者模式 Builder Pattern

####目的：將一個由各種組件組合的複雜產品建造過程封裝
  
###照順序組裝機器人   
建造者模式其實就像再隔了一層Director的抽像工廠類別，像我們以下的範例，一個機器人(Product)由外型(Form)、動力(Power)、武器(Weapon)
所組成，GundamBuilder就像抽象工廠的實體一樣，可以生產出一個機器人所有的組件，與抽像工廠類別不同的是，在建造者模式中我們
會用一個Director來控制小物件如何組裝成一個大物件的順序。  
  

###類別圖  
![Builder Class Diagram](image/builder.gif)  

###程式碼
Product介面與實作(含組成Product的零件類別)
```
/**
 * 機器人介面(Product)
 */
public abstract class IRobot {
	Form  form; // 外型
	Power power; // 動力
	Weapon weapon; //武器
	
	public void setForm (Form  form){
		this.form = form;
	};
	
	public void setPower(Power power){
		this.power = power;
	};
	
	public void setWeapon(Weapon weapon){
		this.weapon = weapon;
	};
	
	public void display(){
		System.out.println("機器人外型：" + form);
		System.out.println("機器人動力：" + power);
		System.out.println("機器人武器：" + weapon);

	}; // 展示機器人
}

/**
 * 鋼彈-實體機器人(ConcreteProduct)
 */
public class Gundam extends IRobot{

}

/**
 * 機器人組件-外型(Product Part)
 */
public class Form {
	private String formName; 
	
	public Form(String formName){
		this.formName = formName;
	}
	
	public String toString(){
		return this.formName;
	}
	
}

/**
 * 機器人組件-武器(Product Part)
 */
public class Weapon {
	List<String> list = new ArrayList<>();
	public Weapon(String[] weaponList){
		list.addAll(Arrays.asList(weaponList));
	}
	@Override
	public String toString(){
		return list.toString();		
	}
}

/**
 * 機器人組件-動力(Product Part)
 */
public class Power {
	private String mainPower; // 主動力
	private String subPower; // 副動力
	private String battery; // 電池

	public Power(String mainPower, String subPower, String battery) {
		this.mainPower = mainPower;
		this.subPower = subPower;
		this.battery = battery;
	}

	@Override
	public String toString() {
		return "{主動力：" + mainPower + " , 副動力:" + subPower + " ,電池：" + battery + "}";
	}
}
```  
建造者介面與實作類別
```  
/**
 * 機器人建造器抽像類別(AbstractBuilder)
 *
 */
public abstract class RobotBuilder {
	
	/**
	 * 建造機器人外型
	 */
	public abstract Form buildForm();
	/**
	 * 建造機器人動力系統
	 */
	public abstract Power buildPower();
	/**
	 * 建造機器人武器系統
	 */
	public abstract Weapon buildWeapon();

}


/**
 * 鋼彈建造者類別(ConcreteBuilder)
 */
public class GundamBuilder extends RobotBuilder{
	
	/**
	 * 建造機器人外型
	 */
	public Form buildForm(){
		// 這邊可以想像成用工廠類別可以創造很多種不同的外型
		return new Form("鋼彈");
	};
	/**
	 * 建造機器人動力系統
	 */
	public Power buildPower(){
		// 這邊可以想像成用工廠類別可以創造不同的動力系統
		return new Power("亞哈反應爐","Beta發電機","氫電池");
	};
	/**
	 * 建造機器人武器系統
	 */
	public Weapon buildWeapon(){
		// 這邊可以想像成用工廠類別可以創造不同的武器
		return new Weapon(new String[]{"60mm火神砲","突擊長矛","薩克機槍","光束劍"});

	};

}
```  

Director類別
```  
/**
 * 指揮如何組裝機器人(Director)
 */
public class Director {
	private RobotBuilder builder;
	public Director(RobotBuilder builder){
		this.builder = builder;
	}
	
	/**
	 * Builder Pattern的特色就是會在Director內規範建造的順序
	 * @return
	 */
	public IRobot builderRobot(){
		IRobot robot = new Gundam();
		// 依照順序建造機器人
		robot.setForm(builder.buildForm());
		robot.setPower(builder.buildPower());
		robot.setWeapon(builder.buildWeapon());		
		return robot;
	}
}
```  
測試碼
```  
/**
 * 建造者模式-測試
 */
public class RobotBuilderTest {
	
	@Test
	public void test() {
		Director director = new Director(new GundamBuilder());
		IRobot robot = director.builderRobot();
		robot.display();
	}
	
}

```  

測試結果
```  
============建造者模式測試============
機器人外型：鋼彈
機器人動力：{主動力：亞哈反應爐 , 副動力:Beta發電機 ,電池：氫電池}
機器人武器：[60mm火神砲, 突擊長矛, 薩克機槍, 光束劍]
```  

# 簡單工廠模式 SimpleFactor
  
####目的：又稱為靜態工廠模式，定義一個簡單工廠，傳入不同的參數返回不同的類別物件，一般來說這些類別會有一個共同的父類別(介面)。  
  
####優點：使用上簡單方便  

####缺點：
  
####新手村的故事  
現在要設計一個訓練冒險者(Adventurer)的新手村(Village)，裡面可以訓練弓箭手(Archer)、鬥士(Warrior)。  

###類別圖：  
![Training Camp](image/simpleFactory.gif)  

###程式碼：  
```
/**
 * 簡單工廠-冒險者訓練營
 */
public class TrainingCamp {
	public static Adventurer trainAdventurer(String type){
		switch(type){
			case "archer" : System.out.println("訓練一個弓箭手");return new Archer(); 
			case "warrior": System.out.println("訓練一個鬥士");return new Warrior();
			default : return null;
		}
	}
}

// 冒險者
public interface Adventurer {
	//告訴別人你是哪種冒險者
	String getType();}
}

// 弓箭手
public class Archer implements Adventurer {
	@Override
	public String getType() {
		System.out.println("我是弓箭手");
		return  this.getClass().getSimpleName();
	}
}

// 鬥士
public class Warrior implements Adventurer {
	@Override
	public String getType() {
		System.out.println("我是鬥士");	
		return  this.getClass().getSimpleName();
	}
}	

// 冒險者訓練營測試
public class VillageTest {
	@Test
	public void test(){
		//新手村訓練冒險者
		Adventurer memberA = Village.trainAdventurer("archer");
		Adventurer memberB = Village.trainAdventurer("warrior");
				//看看是不是真的訓練出我們想要的冒險者
		Assert.assertEquals(memberA.getType(), "Archer");

		Assert.assertEquals(memberB.getType(), "Warrior");
		
	}
}

```
  
以上就是一個簡單工廠模式，如果有人要來招募冒險者組隊，只要跟訓練營說請幫我訓練一個冒險者就可以，不用去理解訓練過程。
但如果新手村要多訓練一種冒險者:牧師(Priest)，只要在Villiage trainAdventurer()方法裡面增加一個switch case分支就好。  
問題來了，這樣直接修改Villiage class的內容，違反了開放/封閉原則，因此一般來說我們會用工廠模式來修正這種情況。

 
  

# 簡單工廠模式 SimpleFactor

####目的：定義一個簡單工廠，傳入不同的參數取得不同的類別物件 
* 簡單工廠又稱為靜態工廠模式，一般來說同一工廠內所產生的類別會有一個共同的父類別(介面)
   
###首先，先從新手村開始  
簡單工廠模式是一種管理物件創建的模式，隨著輸入的參數不同，簡單工廠會回傳不同的物件，使用者取得物件的時候只要傳入正確的參數，不需要去理解這個物件。  
  
現在要設計一個訓練冒險者Adventurer的訓練營Training Camp，裡面可以訓練的冒險者種類有弓箭手Archer、鬥士Warrior。套到簡單工廠模式中，訓練營就是我們的簡單工廠(SimpleFactory)，冒險者則是產品的父類別(Product)，弓箭手與鬥士為實體產品(ConcreteProduct)。如果有人要來招募冒險者組隊，只要跟訓練營說請幫我訓練一個冒險者就可以，不用去理解訓練過程。  
  
現在新手村要多訓練一種冒險者，牧師Priest，只要在trainAdventurer方法內增加一個switch case分支就好。不過這樣直接修改TrainingCamp類別的程式碼，違反了開放/封閉原則，因此簡單工廠不能算是一個健全的設計模式，不過如果簡單工廠在小型的軟體架構中很好用，因此一般設計模式的教學都會從簡單工廠模式開始，實務上也常常會用到這個簡單的模式。    
  
###類別圖    
![SimpleFactor Class Diagram](image/simpleFactory.gif)  

###程式碼
產品介面與實作類別  
```
// 冒險者(Product)
public interface Adventurer {
	//告訴別人你是哪種冒險者
	String getType();
}

// 弓箭手(ConcreteProduct)
public class Archer implements Adventurer {
	@Override
	public String getType() {
		System.out.println("我是弓箭手");
		return  this.getClass().getSimpleName();
	}
}

// 鬥士(ConcreteProduct)
public class Warrior implements Adventurer {
	@Override
	public String getType() {
		System.out.println("我是鬥士");	
		return  this.getClass().getSimpleName();
	}
}	

```  

簡單工廠類別  
```  
/**
 * 冒險者訓練營(SimpleFactor)
 */
public class TrainingCamp {
	public static Adventurer trainAdventurer(String type){
		switch(type){
			case "archer" : {
				System.out.println("訓練一個弓箭手");
				return new Archer(); 			
			}
			case "warrior": {
				System.out.println("訓練一個鬥士");
				return new Warrior();				
			}
			// 假如冒險者種類新增，增加case就可以
			default : return null;
		}
	}
}  
```  
  
測試碼  
```
// 冒險者訓練營測試
public class VillageTest {
	@Test
	public void test(){
		//新手村訓練冒險者
		Adventurer memberA = Village.trainAdventurer("archer");
		Adventurer memberB = Village.trainAdventurer("warrior");
		//這邊用Junit來幫我們判斷訓練出來的冒險者是不是我們想要的
		Assert.assertEquals(memberA.getType(), "Archer");
		Assert.assertEquals(memberB.getType(), "Warrior");
	}
}

```

測試結果
```
==========簡單工廠模式測試==========
訓練一個弓箭手
訓練一個鬥士
我是弓箭手
我是鬥士
```

 
  

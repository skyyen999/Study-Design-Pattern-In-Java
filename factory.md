# 工廠模式 Factory Pattern
  
####目的：提供一個工廠介面，將產生實體的程式碼交由子類別各自實現

###進化的新手村    
剛才的簡單工廠模式因為只有一個工廠，要新增產品種類要直接修改工廠類別裡面的程式碼，直接破壞了開放/封閉原則，在工廠模式中，我們將工廠(Factory)提升為一種抽象的概念，也就是說現在工廠是一個介面(Interface)，工廠介面只會規範實體工廠類別(Concrete Factory)應該返回哪種產品，實際上要如何製作產品則交給實體工廠來實作。  
  
現在訓練營已經被提升為一種概念，訓練各種冒險者的過程應該是不一樣的，不能像以前這樣一個訓練營訓練出所有種類的冒險者，例如培訓近身格鬥的鬥士與躲遠遠放冷箭的弓箭手應該是不同的培訓過程。  
  
新手村現在建立了兩座訓練營，弓箭手訓練營、鬥士訓練營，相信看名子就知道這兩種訓練訓練營的功能是什麼。如此一來，如果想要修改弓箭手的訓練流程，就修改弓箭手訓練營裡面的程式碼即可，不用擔心是否會影響鬥士訓練營的運作，而且如要增加冒險者的類別，例如說劍士，只要新增一座劍士訓練營，完全不會改動到抽象訓練營與本來的實體訓練營。  
   
  
###類別圖 
![Factory Class Diagram](image/factory.gif)  
   
###程式碼  
產品介面與產品實作類別  
```
// 冒險者(Product)
public interface Adventurer {
	//告訴別人你是哪種冒險者
	String getType();}
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

工廠介面與工廠實作類別  
```
/**
 *  冒險者訓練營介面(Factory)-這邊只是一個概念或規範，要訓練什麼，怎麼訓練留給子類別實作
 */
public interface TrainingCamp {
	//訓練冒險者的過程，訓練後請給我一個冒險者
	public Adventurer trainAdventurer();
}

/**
 * 弓箭手訓練營(ConcreteFactory)
 */
public class ArcherTrainingCamp implements TrainingCamp {

	@Override
	public Adventurer trainAdventurer() {
		System.out.println("訓練一個弓箭手");
		return new Archer(); 
	}

}

/**
 * 鬥士訓練營(ConcreteFactory)
 */
public class WarriorTrainingCamp implements TrainingCamp {
	@Override
	public Adventurer trainAdventurer() {
		System.out.println("訓練一個鬥士");
		return new Warrior(); 
	}
}
```
測試碼：  
```
/**
 * 冒險者訓練營測試
 */
public class TrainingCampTest {
	@Test
	public void test(){
		System.out.println("==========工廠模式測試==========");
		
		//訓練營訓練冒險者
		//先用弓箭手訓練營訓練弓箭手
		TrainingCamp trainingCamp = new ArcherTrainingCamp();
		Adventurer memberA = trainingCamp.trainAdventurer();
		
		//用鬥士訓練營訓練鬥士
		trainingCamp = new WarriorTrainingCamp();
		Adventurer memberB = trainingCamp.trainAdventurer();
		
		//看看是不是真的訓練出我們想要的冒險者
		Assert.assertEquals(memberA.getType(), "Archer");
		Assert.assertEquals(memberB.getType(), "Warrior");

		// memberB應該是Warrior不是Knight，因此下面這行會報錯
		Assert.assertEquals(memberB.getType(), "Knight");
	}
}

```
測試結果：  
```
==========工廠模式測試==========
訓練一個弓箭手
訓練一個鬥士
我是弓箭手
我是鬥士
```
  
簡單工廠模式與工廠模式比較：  
  
* 簡單工廠模式：工廠直接負責管理所有的產品，利用if else 或 switch case判斷式來產生產品。  
	
* 工廠模式：工廠提升為一個概念，實際上產生產品的是實作工廠概念的實體工廠。
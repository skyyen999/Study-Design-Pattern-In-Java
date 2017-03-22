# 樣版模式 Template Pattern 

####目的定義一套演算法的架構，但是細節可延遲到子類別再決定

###迷宮的冒險
迷宮探險對冒險者來說是一件很重要的活動，這邊我們就來寫一套迷宮探險的系統，冒險者進入迷宮探險到完成探險經過的步驟如下：  
1. 確認冒險者等級是否達到迷宮門檻
2. 冒險者達到門檻才開始產生迷宮(每一個迷宮都長的不一樣)
3. 冒險者進行探險(我們這邊不管冒險者如何完成探險)
4. 計算探險結果  
   
以上可以看的出來從冒險者來到迷宮門口到離開結算成果，過程大致上是一樣的，對於這些同樣的過程，我們就用一個樣版(Template)來規範一趟冒險應該有什麼過程。  

這邊看類別圖，首先可以看到我們的迷宮樣版(Maze Template)是一個抽象類別，產生迷宮的程式createMaze()在迷宮樣版中並沒有被實作出來，實際上要如何產生不同的迷宮留給子類別來實作。可以先看一下簡單迷宮(EazyMaze)這個類別，需要實作的部份總共有兩個部分，第一個是在建構子中必須先設定迷宮難易度，再來是要實作createMaze()方法來產生迷宮。  

  
####掛勾Hook  
之前在裝飾者模式中，裝飾者對被裝飾的對象進行裝飾，讓被裝飾者動態的增加一些功能，在樣版模式中，我們可以使用掛勾(hook)的方式來增加功能，力如說有些迷宮結束後還會出現隱藏迷宮，因此進入隱藏迷宮hiddenMaze()這個方法一般來說並不會被調用，這時候可以設計一個掛勾來決定一個迷宮會不會調用hiddenMaze()。  
這邊的實現可以先看MazeTemplate中有一個isDoubleMaze參數，這個參數就是掛勾，初始設為false， 代表hiddenMaze()預設不會被調用，再看困難的迷宮MazeTemplate類別，類別中建構子將isDoubleMaze設為true， 因此冒險者們進行冒險的時候就會觸發隱藏迷宮。  

  
###類別圖 
![Template Class Diagram](image/template.gif)  
   
###程式碼
樣版Template與實作類別
```
/**
 * 迷宮樣版(Template)-規範迷宮冒險的演算法
 */
public abstract class MazeTemplate {
	protected int difficulty ; // 迷宮難度
	protected Adventurer adventurer; // 進入迷宮的冒險者
	protected boolean isDoubleMaze = false ; // hook，決定是否有隱藏的迷宮
	
	/** 
	 * @param adventurer 進入迷宮的冒險者
	 * @return 
	 */
	public Adventurer adventure(Adventurer adventurer){
		this.adventurer = adventurer;
		
		// 確認冒險者等級
		if(!checkLevel(adventurer.getLevel())) {
			System.out.println("冒險者等級不足，請提升等級至 " + difficulty  + " 後開放迷宮");
		} else {
			System.out.println("---" + adventurer.getType()  + "開始進行困難度 " + difficulty + " 的迷宮");
			createMaze();		// 產生迷宮
			start();  			// 冒險者闖迷宮
			
			if(isDoubleMaze){
				hiddenMaze(); 		// 由掛勾hook決定是否有隱藏迷宮，有的話可以進入隱藏關卡
			}
			showResult();		// 結算冒險結果
		}
		return this.adventurer; 
	}
	
	/**
	 * 冒險者等級是否足夠
	 * @param level
	 * @return
	 */
	private boolean checkLevel(int level){
		if(level < difficulty){
			return false;
		} 
		return true;
	}
	
	/**
	 * 產生迷宮內容(由子類別實作)
	 * @return
	 */
	abstract void createMaze();
	
	/**
	 * 冒險者進入迷宮(由子類別實作)
	 * @return
	 */
	abstract void start();
	
	/**
	 * 進入隱藏迷宮(隱藏迷宮，由hook觸發)
	 * @return
	 */
	void hiddenMaze(){
		System.out.println("進入隱藏迷宮");
	};
	
	/**
	 * 顯示冒險結果
	 */
	Adventurer showResult(){
		this.adventurer.setLevel(adventurer.getLevel() + 50*difficulty);  // 完成迷宮後冒險者等級增加
		System.out.println("---" + adventurer.getType() + "完成困難度 " + difficulty + " 迷宮!!!");
		return this.adventurer;
	};
}

/**
 * 簡單的迷宮(ConcreteTemplate)
 */
public class EazyMaze extends MazeTemplate{
	public EazyMaze() {
		super.difficulty = 1; // 沒限制等級
	}

	@Override
	void createMaze() {
		System.out.println("準備100*100的迷宮");
		System.out.println("安排10隻小怪物");
		System.out.println("安排等級10的BOSS");
		System.out.println("拔草整理場地");
		System.out.println("簡易迷宮準備完成!!!");
	}
}

/**
 * 困難的迷宮(ConcreteTemplate)
 */
public class DifficultMaze extends MazeTemplate{

	public DifficultMaze() {
		super.isDoubleMaze = true; // 困難模式有隱藏關卡
		super.difficulty = 50; // 50級以上才能進入困難迷宮
	}

	@Override
	void createMaze() {
		System.out.println("準備1000*1000的迷宮(包刮隱藏迷宮)");
		System.out.println("安排打不完的小怪物");
		System.out.println("安排等級50的中BOSS，100隻");
		System.out.println("安排等級120的超級BOSS，放隱藏迷宮的保物");
		System.out.println("拔草整理場地，重新油漆牆壁，擺放各種陷阱，擺放假屍體");
		System.out.println("困難迷宮準備完成!!!");
	}

	@Override
	void start() {
		System.out.println("冒險者開始進行困難迷宮的冒險");
	}

	}
	@Override
	void start() {
		System.out.println("冒險者開始進行簡單迷宮的冒險");
	}
}
```
以下程式碼只是測試需要，跟樣版模式沒有關係  
```
	/**
	 * 進入迷宮的冒險者介面
	 */
	public abstract class Adventurer {
		protected int level ; //冒險者等級
		protected String type ; // 冒險者類別
		
		public String getType(){
			return this.type;
		};
		
		public int getLevel(){
			return this.level;
		};
		
		public void setLevel(int level){
			this.level = level;
		};
	}

	/**
	 * 冒險者-鋼彈Justice
	 */
	public class GundamJustice extends Adventurer {
		public GundamJustice(){
			super.type = "Gundam-Justice";
			super.level = 100;	//鋼彈等級很高的
		}
	}

	/**
	 * 冒險者-劍士
	 */
	public class Sabar extends Adventurer {
		public Sabar(){
			super.type = "Sabar";
			super.level = 10;	//劍士等級就普普
		}
	}
```
測試碼
```
/**
 * 樣版模式-測試
 */
public class MazeTest {
	Adventurer sabar = new Sabar(); // 等級10的劍士
	Adventurer justice = new GundamJustice(); // 等級100的正義鋼彈
	
	MazeTemplate easyMaze = new EazyMaze();		 // 簡單迷宮
	MazeTemplate hardMaze = new DifficultMaze(); // 困難迷宮
	
	@Test
	public void test(){
		System.out.println("============樣版模式測試============");

		System.out.println(" =====困難迷宮======");
		sabar = hardMaze.adventure(sabar);	
		System.out.println(" =====簡單迷宮練功======");
		sabar = easyMaze.adventure(sabar);
		
		// 練功後劍士可以進行困難迷宮
		System.out.println(" =====困難迷宮測試======");
		sabar = hardMaze.adventure(sabar);
		justice = hardMaze.adventure(justice); 

	}

}

```
測試結果
```
============樣版模式測試============
 =====困難迷宮======
冒險者等級不足，請提升等級至 50 後開放迷宮
 =====簡單迷宮練功======
---Sabar開始進行困難度 1 的迷宮
準備100*100的迷宮
安排10隻小怪物
安排等級10的BOSS
拔草整理場地
簡易迷宮準備完成!!!
冒險者開始進行簡單迷宮的冒險
---Sabar完成困難度 1 迷宮!!!
 =====困難迷宮測試======
---Sabar開始進行困難度 50 的迷宮
準備1000*1000的迷宮(包刮隱藏迷宮)
安排打不完的小怪物
安排等級50的中BOSS，100隻
安排等級120的超級BOSS，放隱藏迷宮的保物
拔草整理場地，重新油漆牆壁，擺放各種陷阱，擺放假屍體
困難迷宮準備完成!!!
冒險者開始進行困難迷宮的冒險
進入隱藏迷宮
---Sabar完成困難度 50 迷宮!!!
---Gundam-Justice開始進行困難度 50 的迷宮
準備1000*1000的迷宮(包刮隱藏迷宮)
安排打不完的小怪物
安排等級50的中BOSS，100隻
安排等級120的超級BOSS，放隱藏迷宮的保物
拔草整理場地，重新油漆牆壁，擺放各種陷阱，擺放假屍體
困難迷宮準備完成!!!
冒險者開始進行困難迷宮的冒險
進入隱藏迷宮
---Gundam-Justice完成困難度 50 迷宮!!!
```
  
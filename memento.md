# 備忘錄模式 Memento Pattern

####目的：將一個物件的內部狀態儲存在另外一個備忘錄物件中，備忘錄物件可用來還原物件狀態
* 備忘錄模式其實就是備份的概念。

###打不好!重來
下面我們用打魔王小遊戲來模擬，在戰鬥前有個複雜神秘的密技可以降低魔王的攻擊力，不過因為太複雜了，
所以使用後我們就先使用備忘錄物件(Memento)將魔王的狀態儲存，當戰鬥不順利需要重來的時候我們可以使用Memento將
魔王的狀態恢復到開打之前。  

類別圖：  
![Memento Class Diagram](image/memento.gif)  
  
程式碼：
```
/**
 * 要備份的物件
 */
public class GameRole {
	private int hp = 100;
	private int atk = 100;
	private int def = 100;
	private String name = "第六天魔王";

	public RoleStateMemo save(){
		return new RoleStateMemo(hp,atk,def);
	}
	

	/**
	 * 
	 */
	public void fight(){
		hp = 30;
		System.out.println(name + "剩下30%血量，出大招把隊伍打的半死");
	}
	
	public void stateDisplay(){
		System.out.println(name+"的狀態：");
		System.out.print("hp=" + hp);
		System.out.print(", atk=" + atk);
		System.out.println(", def=" + def);
		System.out.println();
	}
	
	
	
	public void load(RoleStateMemo memo){
		this.hp = memo.getHp();
		this.atk = memo.getAtk();
		this.def = memo.getDef();
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getAtk() {
		return atk;
	}
	public void setAtk(int atk) {
		this.atk = atk;
	}
	public int getDef() {
		return def;
	}
	public void setDef(int def) {
		this.def = def;
	}
}


/**
 * 備忘錄物件
 */
public class RoleStateMemo {
	private int hp;
	private int atk;
	private int def;

	public RoleStateMemo(int hp, int atk, int def) {
		this.hp = hp;
		this.atk = atk;
		this.def = def;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getAtk() {
		return atk;
	}
	public void setAtk(int atk) {
		this.atk = atk;
	}
	public int getDef() {
		return def;
	}
	public void setDef(int def) {
		this.def = def;
	}
}



/**
 * 將物件備份
 */
public class RoleStateCareTaker {
	public List<RoleStateMemo> saves = new ArrayList<>();
	
	public RoleStateMemo getSave(){
		return saves.get(0);
	}
	
	public void setSave(RoleStateMemo memo){
		saves.add(0, memo);
	}
}


/**
 * 備忘錄模式-測試
 *
 */
public class GameRoleTest {
	@Test
	public void test() {
		// boss一開始的狀態
		GameRole boss = new GameRole();
		boss.stateDisplay();

		
		// 使用複雜的神秘小技巧，可以降低boss攻擊力
		System.out.println("使用複雜的神秘小技巧");
		boss.setAtk(60);
		
		// 趕快存檔
		RoleStateCareTaker rsc = new RoleStateCareTaker();
		rsc.setSave(boss.save());
		boss.stateDisplay();

		
		// 開打了
		boss.fight();
		boss.stateDisplay();
		
		// 隊伍血沒先回滿，倒了一半，快讀取剛才的存檔
		boss.load(rsc.getSave());
		System.out.println("不行不行，那個時間點先該先回滿血，讀檔重打");
		boss.stateDisplay();
	}
	
}


```  

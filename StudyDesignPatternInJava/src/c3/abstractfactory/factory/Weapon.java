package c3.abstractfactory.factory;

/**
 * 武器類別
 */
public abstract class Weapon {
	protected int atk;		// 攻擊力
	protected int range;   // 攻擊範圍
	
	public int getAtk() {
		return atk;
	}
	public void setAtk(int atk) {
		this.atk = atk;
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	/**
	 * 展示武器
	 */
	public void display(){
		System.out.println(this.getClass().getSimpleName() + " atk = " + atk + " , range = " + range);
	}
}

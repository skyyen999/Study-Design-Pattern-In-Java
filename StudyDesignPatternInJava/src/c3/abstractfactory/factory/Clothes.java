package c3.abstractfactory.factory;

/**
 * 衣服類別
 */
public abstract class Clothes {
	protected int def;	// 防禦力
	/**
	 * 展示這件衣服
	 */
	public void display(){
		System.out.print("  "+this.getClass().getSimpleName() + " def:" + def);
	}
	
	public int getDef() {
		return def;
	}
	public void setDef(int def) {
		this.def = def;
	}
}

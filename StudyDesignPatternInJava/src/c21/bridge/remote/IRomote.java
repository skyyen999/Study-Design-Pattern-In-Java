package c21.bridge.remote;
/**
 * 搖控器介面
 */
public abstract class IRomote {
	protected ITelevision tv;
	
	public IRomote(ITelevision tv){
		this.tv = tv;
	}
	
	/**
	 * 開關電源
	 */
	public void powerOn(){
		System.out.println("打開電視機");
		tv.powerOn();
	} ;
	public void powerOff(){
		System.out.println("關閉電視機");
		tv.powerOff();
	} ;
	
	/**
	 * 下一個頻道
	 */
	public void nextChannel(){
		tv.nextChannel();
	}
}

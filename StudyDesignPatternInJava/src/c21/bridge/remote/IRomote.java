package c21.bridge.remote;
/**
 * 控制器介面
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
		tv.powerOn();
	} ;
	public void powerOff(){
		tv.powerOff();
	} ;
	
	/**
	 * 下一個頻道
	 */
	public void nextChannel(){
		tv.nextChannel();
	}
}

package c21.bridge.remote;
/**
 * 電視機介面
 */
public abstract class  ITelevision{

	private boolean isPowerOn; // 電視機電源
	protected int channel = 1;   // 現在頻道
	/**
	 * 開關電源
	 */
	public void powerOn(){
		this.isPowerOn = true;
	} ;
	public void powerOff(){
		this.isPowerOn = false;
	} ;
	
	/**
	 * 切換到下一個頻道
	 */
	public void nextChannel(){
		if(channel > 10){
			this.channel = 1;
		} else {
			channel++;
		}
	} ;
	
	public void dispaly(){
		// 電源開啟才有辦法顯示頻道
		if(isPowerOn){
			System.out.println("目前頻道 = " + channel);
		}
	}
}

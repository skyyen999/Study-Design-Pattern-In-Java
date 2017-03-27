package c21.bridge.remote;
/**
 * 新版搖控器，增加了直接選台的功能
 */
public class SonyRemote2015 extends IRomote {
	public SonyRemote2015(ITelevision tv) {
		super(tv);
	}

	/**
	 * 新型搖控器可以直接選頻道
	 */
	public void selectChannel(int i){
		if(i > 10 || i < 1){
			return;
		}
		
		// ITelevision沒有 setChannel，跑迴圈找頻道
		while( i != tv.channel){
			super.tv.nextChannel();
		}
	}
}

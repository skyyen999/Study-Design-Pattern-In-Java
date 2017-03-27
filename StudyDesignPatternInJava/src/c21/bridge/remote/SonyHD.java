package c21.bridge.remote;
/**
 * 高畫質電視
 */
public class SonyHD extends ITelevision {
	@Override
	public void powerOn(){
		super.powerOn();
		System.out.println("展示高畫值影片");
	};
}

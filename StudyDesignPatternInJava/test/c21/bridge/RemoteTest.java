package c21.bridge;

import org.junit.Test;

import c21.bridge.remote.SonyHD;
import c21.bridge.remote.SonyRemote2000;
import c21.bridge.remote.SonyRemote2015;
import c21.bridge.remote.SonyTV;

/**
 * 橋接模式-測試
 */
public class RemoteTest {

	@Test
	public void test(){
		SonyTV tv = new SonyTV();
		SonyRemote2000 remote2000 = new SonyRemote2000(tv);
		System.out.println("測試電視->");
		tv.powerOn();
		tv.nextChannel();
		tv.dispaly();
		System.out.println("測試搖控器->");
		remote2000.nextChannel();
		remote2000.nextChannel();
		tv.dispaly();

		System.out.println("<----------新型電視--------------->");
		SonyHD hdTv = new SonyHD();
		hdTv.hdModel();
		hdTv.dispaly();
		System.out.println("<----------新型搖控器--------------->");
		SonyRemote2015 remote2015 = new SonyRemote2015(hdTv);
		remote2015.powerOn();
		remote2015.selectChannel(10);
		hdTv.dispaly();
	}
}

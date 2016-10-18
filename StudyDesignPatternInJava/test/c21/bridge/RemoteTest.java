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
		System.out.println("============橋接模式測試============");

		SonyTV tv = new SonyTV();
		SonyRemote2000 remote2000 = new SonyRemote2000(tv);
		System.out.println("------測試電視------");
		tv.powerOn();
		System.out.println("---電視打開---");
		tv.dispaly();
		System.out.println("---下一個頻道---");
		tv.nextChannel();
		tv.dispaly();
		System.out.println("------測試搖控器------");
		remote2000.nextChannel();
		remote2000.nextChannel();
		System.out.println("---按兩下下一個頻道---");
		tv.dispaly();

		System.out.println("----------新型電視---------------");
		SonyHD hdTv = new SonyHD();
		hdTv.hdModel();
		System.out.println("---使用HD模式---");
		hdTv.dispaly();
		System.out.println("----------新型搖控器---------------");
		SonyRemote2015 remote2015 = new SonyRemote2015(hdTv);
		remote2015.powerOn();
		System.out.println("---直接切到頻道10---");
		remote2015.selectChannel(10);
		hdTv.dispaly();
	}
}

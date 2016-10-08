package c9.facade;

import org.junit.Test;

/**
 * 影音設備Client端
 */
public class VedioRoomFacadeClient {
	VedioRoomFacade superRemote = new VedioRoomFacade();
	
	@Test 
	public void test(){
		// 看電影
		superRemote.readyPlayMovie("Life of Pi");
		superRemote.playMovie();
		superRemote.showAllStatus();
		System.out.println();
		// 關閉機器
		superRemote.turnOffAll();
		superRemote.showAllStatus();
		System.out.println();
		// 看電視
		superRemote.watchTv();
		superRemote.showTv();
		superRemote.switchChannel(20); //換頻道
		superRemote.showTv();
		superRemote.turnOffAll();
		System.out.println();
		
		// 唱ktv
		superRemote.readyKTV();
		superRemote.selectSong("Moon");
		superRemote.playSong();
		superRemote.showAllStatus();

	} 
}

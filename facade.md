# 外觀模式 Facade Pattern
  
####目的：
* 用一個介面包裝各個子系統，由介面與客戶端做溝通。


####這房間的影像設備好複雜阿!!!我只是想看個電影
先暫時離開冒險者村一下，這是我個人親身經歷的故事，我有個親戚家弄了一間很高級豪華的視聽遊樂室，
裡面包括液晶電視，電視盒，環繞音響，重低音放大器，DVD播放器，KTV點歌系統，X-BOX，PS3等等。如果想用PS3看個高清藍光電影，
必須依照以下步驟一個一個執行，其中一個動作錯了，可能會出現沒影像有聲音，有聲音沒影像等等奇奇怪怪的事：
1. 打開總電源
2. 打開電視盒電源
3. 開起重音音放大器並且等三秒
4. 打開液晶電視
5. 打開DVD播放器
6. 打開PS3，並且將液晶電視顯示來選為第二大項中的第三小項
7. 這時候先試一下音響有沒有正確發出聲音順便將液晶電視音量調到13，環繞音響，重低音低量轉低避免吵到鄰居
8. 放入藍光光碟片，使用PS3手把選擇播放電影  
  
以上真的不誇張，全部步驟大概就是這麼長，當然我是不可能記住當時到底正確的步驟是怎樣，如果這套流程可以由一個搖控器一鍵完成
不知道該有多好，外觀模式就像一個超級搖控器，把看電影所需要啟動的裝置操到整合到這個搖控器裡面，方便使用者操作。 

從下面的代碼可以看出經過Facade的統一包裝管理後，使用者(Client)要使用這些影音設備就變的簡單許多。 

類別圖：  
![Facade Pattern](image/facade.gif)  
   
程式碼：  
```
/**
 * 電子產品介面，全部的電子產品都可以開關電源
 */
public abstract class Electronics {
	private boolean power = false;   // 電源
	
	// 啟動電源
	public void powerOn() {
		this.power = true;
	}
	// 關閉電源
	public void powerOff() {
		this.power = false;
	}
	// 電源是否有開
	public boolean isPowerOn() {
		return power;
	}
	// 顯示機器狀態
	protected void showStatus(){
		if(power){
			System.out.println(this.getClass().getSimpleName() + "運作中");
		} else {
			System.out.println(this.getClass().getSimpleName() + "電源未開啟");
		}
	}
}


/**
 * KTV點歌機
 */
public class KTVsystem extends Electronics {
	private String song; // 歌曲
	
	//選歌
	public void selectSong(String song){
		this.song = song;
	}
	//播放
	public void playSong(){
		System.out.println(this.getClass().getSimpleName() + "播放 " + song );
	}
}


/**
 * PS3 
 */
public class PlayStation3 extends Electronics {
	private String cd ; // 目前播放的CD

	// 放入CD片
	public void putCd(String cd) {
		this.cd = cd;
	}

	public String getCd() {
		return cd;
	}

	// 播放CD
	public void play(){
		System.out.println(this.getClass().getSimpleName()  + "開始播放 " + cd);
	}
	
	@Override
	public void showStatus(){
		super.showStatus();
		if(isPowerOn()){
			System.out.println(this.getClass().getSimpleName()  + "目前放入cd: " + cd);
		}
	}
}

/**
 * 環繞音響
 */
public class Stereo extends Electronics {
	private int sound = 50 ;         // 音量 (0為靜音，100為最大)
	
	// 調整音量
	public void setSound(int sound) {
		this.sound = sound;
	}

	public int getSound() {
		return sound;
	}
	
	@Override
	public void showStatus(){
		super.showStatus();
		if(isPowerOn()){
			System.out.println(this.getClass().getSimpleName()  + "音量為: " + sound);
		}
	}
}

/**
 * 液晶電視
 */
public class Television  extends Electronics {
	private int sound = 50 ;         // 音量 (0為靜音，100為最大)
	private String source = "tvBox"; // 訊號源
	private int channel = 9;         // 電視頻道
	
	// 選擇訊號源
	public void switchSource(String source) {
		this.source = source;
	}
	
	// 調整音量
	public void setSound(int sound) {
		this.sound = sound;
	}
	
	// 選電視頻道
	public void switchChannel(int channel) {
		this.channel = channel;
	}
	
	// 看目前觀看電視頻道
	public void showTV() {
		System.out.println("目前觀看的是頻道: " + channel);
	}

	@Override
	public void showStatus(){
		super.showStatus();
		if(isPowerOn()){
			System.out.print(this.getClass().getSimpleName()  + "音量為: " + sound);
			if(source.equals("tvBox")){
				System.out.println(", 頻道: " +  channel);
			}
			
			if(source.equals("ktv")){
				System.out.println(", ktv播放中");
			}
			
			if(source.equals("ps")){
				System.out.println(", ps畫面顯示中");
			}
		}
	}
	
	public int getSound() {
		return sound;
	}

	public String getSource() {
		return source;
	}

	public int getChannel() {
		return channel;
	}
}


/**
 * 管理影音設備的外觀介面-測試
 */
public class VedioRoomFacade {
	// 房間內總共有這些影音設備
	Television tv = new Television();
	Stereo stereo = new Stereo();
	PlayStation3 ps = new PlayStation3();
	KTVsystem ktv = new KTVsystem();
	
	/**
	 * 準備用ps3看電影
	 */
	public void readyPlayMovie(String cd){
		stereo.powerOn(); // 音響要先開
		tv.powerOn();     // 接著開電視
		setSound(50);     // 設定音量
		tv.switchSource("ps"); //電視切到ps訊號源
		ps.powerOn();     // 開ps3
		ps.putCd(cd);     // 放入cd
	}
	
	/**
	 * 用ps3放電影
	 */
	public void playMovie(){
		if(ps.isPowerOn()){
			ps.play();
		}
	}
	// 看目前觀看電視頻道
	public void showTv(){
		tv.showTV();
	}
	
	/**
	 * 關閉全部設備
	 */
	public void turnOffAll(){
		stereo.powerOff(); // 音響要先關
		ktv.powerOff();    // KTV有開的話第二個關
		ps.powerOff();     // 電視如果先關你就看不到ps的畫面了
		tv.powerOff();     // 電視最後關
	}
	
	/**
	 * 看電視
	 */
	public void watchTv(){
		tv.powerOn();     // 開電視
		tv.switchSource("tvBox"); //電視切到電視訊號源
	}
	
	// 選電視頻道
	public void switchChannel(int channel) {
		tv.switchChannel(channel);
	}
	
	/**
	 * 準備唱KTV
	 */
	public void readyKTV(){
		stereo.powerOn(); // 音響要先開
		ktv.powerOn();    // 開啟ktv點唱機
		tv.powerOn();     // 開電視
		setSound(50);     // 設定音量
		tv.switchSource("ktv"); //電視切到ps訊號源
	}
	/**
	 * ktv點歌
	 * @param song
	 */
	public void selectSong(String song){
		if(ktv.isPowerOn()){
			ktv.selectSong(song);
		}
	}
	/**
	 * ktv播放歌曲
	 */
	public void playSong(){
		if(ktv.isPowerOn()){
			ktv.playSong();
		}
	}
	/**
	 * 設定音量
	 * @param soundLevel
	 */
	public void setSound(int soundLevel){
		if(tv.isPowerOn()){
			tv.setSound(soundLevel);
		}
		if(stereo.isPowerOn()){
			stereo.setSound(soundLevel);
		}
	}
	
	/**
	 * 顯示所有機器的狀態
	 */
	public void showAllStatus(){
		tv.showStatus();
		stereo.showStatus();
		ps.showStatus();
		ktv.showStatus();
	}
}

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
```

其實我覺的翻譯成門面模式會比外觀模式或表象模式會更適合一點，Facade是最常見的設計模式之一。  
在現今最流行的MVC架構中，一般我們會用Controller讓前端瀏覽器呼叫，一個Controller會對應一個Service，
這個Service其實就是一個Facade的設計，Service會將Controller需要的功能包裝整理，讓Controller不用直接呼叫
與資料庫溝通的Dao或是使用複雜的程式，因此可以維持Controller只負責與瀏覽器溝通的單一職責。
